package com.boc.service;

import com.backbase.adminPresentation.rest.spec.v1.admin.*;
import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostRequestBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostResponseBody;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostResponseBody;
import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadOpportunityList;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesPostRequestBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesPostResponseBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostResponseBody;
import com.boc.constant.Constants;
import com.boc.persistence.model.AuditAllCSVSchema;
import com.boc.persistence.model.AuditErrorCSVSchema;
import com.boc.persistence.model.LeadOpportunity;
import com.boc.service.helper.QueueHelper;
import com.boc.transformer.AdminPresentationTransformer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class AdminPresentationServiceImpl implements AdminPresentationService {

    public static final Logger logger = LoggerFactory.getLogger(AdminPresentationServiceImpl.class);

    @Autowired
    IDUtils idUtils;
    @Autowired
    AdminPresentationTransformer adminPresentationTransformer;
    @Autowired
    QueueHelper queueHelper;

    @Value("${basePath}")
    String basePath;
    @Value("${jksFileBasePath}")
    String jksFileBasePath;
    @Value("${jksFileName}")
    String jksFileName;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getSavedCsvFilePath(String creditUnionId, String downloadAll, HttpServletRequest httpServletRequest) {
        final CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        final CsvSchema schema = mapper.schemaFor(LeadOpportunity.class);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = String.join("", creditUnionId, "_", timeStamp, Constants.FILETYPE);
        String filePath = String.join(Constants.DELIMITER, basePath, creditUnionId, "output/");
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            logger.info("Error in creating directories");
        }

        // get data from the Database
        List<LeadsAndOpportunitiesGetResponseBody> lstLeadsAndOpportunities = queueHelper.getLeadsAndOpportunities(creditUnionId, downloadAll, httpServletRequest);

        if (lstLeadsAndOpportunities != null && !lstLeadsAndOpportunities.isEmpty()) {
            for (LeadsAndOpportunitiesGetResponseBody leadsAndOpportunitiesGetResponseBody : lstLeadsAndOpportunities) {
                if (leadsAndOpportunitiesGetResponseBody != null && leadsAndOpportunitiesGetResponseBody.getBocforgeMemberNoC() != null
                        && !leadsAndOpportunitiesGetResponseBody.getBocforgeMemberNoC().equals("")) {
                    String decryptedMembarNo = adminPresentationTransformer.getDecryptedValue(leadsAndOpportunitiesGetResponseBody.getBocforgeMemberNoC(), dataFormatter);
                    leadsAndOpportunitiesGetResponseBody.setBocforgeMemberNoC(decryptedMembarNo);
                }
            }
        }

        try {
            mapper.writer(schema.withUseHeader(true)).writeValue(new File(filePath + fileName), lstLeadsAndOpportunities);

            logger.info("File created at: " + filePath + fileName);
        } catch (IOException e) {
            logger.info("Error in creating file");
            throw new InternalServerErrorException().withMessage(e.getMessage());
        }
        return filePath.concat(fileName);
    }

    @Override
    public UploadCSVFilePostResponseBody saveCsvFileData(String creditUnionId, MultipartFile csvFile, HttpServletRequest httpServletRequest, String dataFormatter) {
        try {
            byte[] bytes = csvFile.getBytes();
            Path path = Paths.get(basePath + Constants.DELIMITER + creditUnionId + "/input/" + csvFile.getOriginalFilename());
            Files.createDirectories(Paths.get(basePath + Constants.DELIMITER + creditUnionId + "/input"));
            Files.write(path, bytes);
            InputStream csvFileStream = csvFile.getInputStream();
            MappingIterator<LeadOpportunityList> leadOpportunityMappingIterator = null;
            List<LeadOpportunityList> leadOpportunityList;
            final CsvMapper csvMapper = new CsvMapper();
            csvMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            final CsvSchema csvSchema = csvMapper.schemaFor(LeadOpportunity.class).withHeader();
            leadOpportunityMappingIterator = new CsvMapper().readerFor(LeadOpportunityList.class).with(csvSchema).readValues(csvFileStream);
            leadOpportunityList = leadOpportunityMappingIterator.readAll();

            if (leadOpportunityList != null && !leadOpportunityList.isEmpty()) {
                for (LeadOpportunityList leadsAndOpportunitiesList : leadOpportunityList) {
                    if (leadsAndOpportunitiesList != null && leadsAndOpportunitiesList.getBocforgeMemberNoC() != null
                            && !leadsAndOpportunitiesList.getBocforgeMemberNoC().equals("")) {
                        String encryptedMemberNo = adminPresentationTransformer.getEncryptedValue(leadsAndOpportunitiesList.getBocforgeMemberNoC(),
                                dataFormatter);
                        leadsAndOpportunitiesList.setBocforgeMemberNoC(encryptedMemberNo);
                    }
                }
            }
            LeadsAndOpportunitiesPostRequestBody leadsAndOpportunitiesPostRequestBody = new LeadsAndOpportunitiesPostRequestBody();
            leadsAndOpportunitiesPostRequestBody.setLeadOpportunityList(leadOpportunityList);
            leadsAndOpportunitiesPostRequestBody.setCreditUnionId(creditUnionId);
            LeadsAndOpportunitiesPostResponseBody leadsAndOpportunitiesPostResponseBody = queueHelper.postLeadsAndOpportunities(leadsAndOpportunitiesPostRequestBody, httpServletRequest);
            return objectMapper.convertValue(leadsAndOpportunitiesPostResponseBody, UploadCSVFilePostResponseBody.class);
        } catch (IOException e) {
            logger.info("***** Exception inside AdminPresentationServiceImpl.saveCsvFileData() *** : " +e.getMessage());
        }
        return null;
    }

    @Override
    public String getSavedAuditFilePath(DownloadAuditFilePostRequestBody downloadAuditFilePostRequestBody, HttpServletRequest httpServletRequest) {
        final CsvMapper mapper = new CsvMapper();
        String creditUnionId = downloadAuditFilePostRequestBody.getCreditUnionId();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        CsvSchema schema;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = String.join("", "Marketing_Logs_", creditUnionId, "_", timeStamp, Constants.FILETYPE);
        String filePath = String.join(Constants.DELIMITER, basePath, creditUnionId, "audit/");
        if (downloadAuditFilePostRequestBody.getRecordType().equalsIgnoreCase("SUCCESS")) {
            schema = mapper.schemaFor(AuditAllCSVSchema.class).withHeader();
        } else {
            schema = mapper.schemaFor(AuditErrorCSVSchema.class).withHeader();
        }
        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            logger.info("Error in creating directories");
            throw new InternalServerErrorException().withMessage(e.getMessage());
        }
        // get data from the Database
        List<GetAuditPostResponseBody> getAuditPostResponseBodyList = null;

        try {
            GetAuditPostRequestBody getAuditPostRequestBody = objectMapper.convertValue(downloadAuditFilePostRequestBody, GetAuditPostRequestBody.class);
            getAuditPostResponseBodyList = queueHelper.postGetAudit(getAuditPostRequestBody, httpServletRequest);
        } catch (Exception e) {
            logger.info("Error in fetching leads data from the Database");
            throw new InternalServerErrorException().withMessage(e.getMessage());
        }
        // create hash map data from response
        try {
            String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

            if (getAuditPostResponseBodyList != null && !getAuditPostResponseBodyList.isEmpty()) {
                for (GetAuditPostResponseBody getAuditPostRequestBody : getAuditPostResponseBodyList) {
                    if (getAuditPostRequestBody != null && getAuditPostRequestBody.getMemberId() != null && !getAuditPostRequestBody.getMemberId().equals("")) {
                        String decryptedMemberNo = adminPresentationTransformer.getDecryptedValue(getAuditPostRequestBody.getMemberId(), dataFormatter);
                        getAuditPostRequestBody.setMemberId(decryptedMemberNo);
                    }
                }
            }

            mapper.writer(schema).writeValue(new File(filePath + fileName), getAuditPostResponseBodyList);
            logger.info("File created at: " + filePath + fileName);
        } catch (IOException e) {
            logger.info("Error in creating file");
            throw new InternalServerErrorException().withMessage(e.getMessage());
        }
        return filePath.concat(fileName);
    }

    @Override
    public GetCreditUnionConfigDataGetResponseBody getGetCreditUnionConfigData(String creditUnionId, HttpServletRequest httpServletRequest) {

        ConfigDataByCreditUnionIdGetResponseBody getConfigDataGetResponseBody = queueHelper.getGetCreditUnionConfigData(creditUnionId, httpServletRequest);
        return objectMapper.convertValue(getConfigDataGetResponseBody, GetCreditUnionConfigDataGetResponseBody.class);
    }

    @Override
    public SaveCreditUnionConfigDataPostResponseBody postSaveCreditUnionConfigData(SaveCreditUnionConfigDataPostRequestBody saveCreditUnionConfigDataPostRequestBody,
                                                                                   HttpServletRequest httpServletRequest) {
        SaveConfigDataPostRequestBody saveConfigDataPostRequestBody = objectMapper.convertValue(saveCreditUnionConfigDataPostRequestBody,
                SaveConfigDataPostRequestBody.class);
        SaveConfigDataPostResponseBody saveConfigDataPostResponseBody = queueHelper.postSaveCreditUnionConfigData(saveConfigDataPostRequestBody, httpServletRequest);
        return objectMapper.convertValue(saveConfigDataPostResponseBody, SaveCreditUnionConfigDataPostResponseBody.class);
    }

    @Override
    public UpdateDataFormatterPostResponseBody updateDataFormatter(UpdateDataFormatterPostRequestBody updateDataFormatterPostRequestBody,
                                                                   HttpServletRequest httpServletRequest) {

        ChangeFormatterPostRequestBody changeFormatterPostRequestBody = objectMapper.convertValue(updateDataFormatterPostRequestBody,
                ChangeFormatterPostRequestBody.class);
        ChangeFormatterPostResponseBody changeFormatterPostResponseBody = queueHelper.updateDataFormatter(changeFormatterPostRequestBody, httpServletRequest);
        return objectMapper.convertValue(changeFormatterPostResponseBody, UpdateDataFormatterPostResponseBody.class);
    }

    @Override
    public UploadJKSFilePostResponseBody postUploadJKSFile(MultipartFile file, String creditUnionId, HttpServletRequest httpServletRequest) {
        if(file == null || creditUnionId == null)
            throw new BocCustomErrorException().withMessage("file or creditUnionId should not be null");
        // get the credit union type.
        GetCreditUnionByIdGetResponseBody getCreditUnionByIdGetResponseBody = queueHelper.getGetCreditUnionById(creditUnionId, httpServletRequest);
        if(!getCreditUnionByIdGetResponseBody.getSource().equals(Constants.SOURCE_SALESFORCE))
            throw new BocCustomErrorException().withMessage("This credit union will not support this operation");

        UploadJKSFilePostResponseBody uploadJKSFilePostResponseBody = new UploadJKSFilePostResponseBody();
        // upload file to a location.
        String jksFilePath = uploadJksFile(file, creditUnionId);
        if(jksFilePath == null){
            throw new BocCustomErrorException().withMessage("File operation failed");
        }
        return uploadJKSFilePostResponseBody.withMessage("Success");
    }

    public String uploadJksFile(MultipartFile multipartFile, String creditUnionId) {
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(jksFileBasePath, creditUnionId, jksFileName);
            Files.createDirectories(Paths.get(jksFileBasePath + Constants.DELIMITER + creditUnionId));
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
