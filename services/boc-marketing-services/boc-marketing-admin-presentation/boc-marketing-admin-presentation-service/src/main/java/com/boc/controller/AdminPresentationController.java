package com.boc.controller;

import com.backbase.adminPresentation.rest.spec.v1.admin.UnauthorizedException;
import com.backbase.adminPresentation.rest.spec.v1.admin.*;
import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.backend.internalrequest.DefaultInternalRequestContext;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequestContext;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.*;
import com.backbase.persistence.listener.client.v1.saveaudit.PersistenceSaveAuditClient;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.boc.constant.Constants;
import com.boc.service.AdminPresentationService;
import com.boc.transformer.AdminPresentationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * The AdminPresentationController class publish the end points
 */
@RequestMapping("/v1/admin")
@RestController
public class AdminPresentationController implements AdminPresentationServicesApi {

    private final Logger logger = LoggerFactory.getLogger(AdminPresentationController.class);

    @Autowired
    AdminPresentationService adminPresentationService;
    @Autowired
    PersistenceSaveAuditClient persistenceSaveAuditClient;
    @Autowired
    AdminPresentationTransformer adminPresentationTransformer;
    @Autowired
    IDUtils idUtils;

    /**
     * @param csvFile
     * @param creditUnionId
     * @param httpServletRequest
     * @param httpServletResponse
     */

    @Override
    public UploadCSVFilePostResponseBody postUploadCSVFile(@RequestParam("file") MultipartFile csvFile, @PathVariable("creditUnionId")
            String creditUnionId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        final String txnId = UUID.randomUUID().toString();
        SaveAuditPostRequestBody saveAuditPostRequestBody;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);
        UploadCSVFilePostResponseBody uploadCSVFilePostResponseBody = null;

        try {
            uploadCSVFilePostResponseBody = adminPresentationService.saveCsvFileData(creditUnionId, csvFile, httpServletRequest, dataFormatter);
        } catch (Exception e) {
            logger.info("***** Exception inside AdminPresentationController.postUploadCSVFile() *** : " + e.getMessage());
            saveAuditPostRequestBody = adminPresentationTransformer.mapAsSaveAuditPostRequestBody(null, creditUnionId, Constants.AUDIT_CHANNEL_ADMIN,
                    Constants.OPERATION_DATABASE, adminPresentationTransformer.getMemberNumber(dataFormatter), e.getMessage(), txnId);
            persistenceSaveAuditClient.postSaveAudit(createInternalRequest(saveAuditPostRequestBody, httpServletRequest));
            throw getCustomExceptionInstance();
        }
        return uploadCSVFilePostResponseBody;
    }

    @Override
    public void postDownloadCSVFile(@RequestBody DownloadCSVFilePostRequestBody downloadCSVFilePostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String filePath = adminPresentationService.getSavedCsvFilePath(downloadCSVFilePostRequestBody.getCreditUnionId(),
                downloadCSVFilePostRequestBody.getDownloadAll(), httpServletRequest);
        saveAndCopy(httpServletResponse, filePath);
    }

    @Override
    public void postDownloadAuditFile(@RequestBody DownloadAuditFilePostRequestBody downloadAuditFilePostRequestBody, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {

        String filePath = adminPresentationService.getSavedAuditFilePath(downloadAuditFilePostRequestBody, httpServletRequest);
        saveAndCopy(httpServletResponse, filePath);
    }

    private void saveAndCopy(HttpServletResponse httpServletResponse, String filePath) {
        try (
                InputStream is = new FileInputStream(filePath);
        ) {
            httpServletResponse.setContentType("text/csv");
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            org.apache.commons.io.IOUtils.copy(is, httpServletResponse.getOutputStream());
            httpServletResponse.flushBuffer();
        } catch (Exception e) {
            throw getCustomExceptionInstance();
        }
    }

    @Override
    public GetCreditUnionConfigDataGetResponseBody getGetCreditUnionConfigData(@PathVariable("creditUnionId")
                                                                                                 String creditUnionId, HttpServletRequest httpServletRequest,
                                                                                     HttpServletResponse httpServletResponse) {

        final String txnId = UUID.randomUUID().toString();
        SaveAuditPostRequestBody saveAuditPostRequestBody;
        GetCreditUnionConfigDataGetResponseBody getCreditUnionConfigDataGetResponseBody = null;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            getCreditUnionConfigDataGetResponseBody = adminPresentationService.getGetCreditUnionConfigData(creditUnionId, httpServletRequest);
        } catch (Exception e) {
            saveAuditPostRequestBody = adminPresentationTransformer.mapAsSaveAuditPostRequestBody(null, null, Constants.AUDIT_CHANNEL_ADMIN,
                    Constants.OPERATION_DATABASE, adminPresentationTransformer.getMemberNumber(dataFormatter), e.getMessage(), txnId);
            persistenceSaveAuditClient.postSaveAudit(createInternalRequest(saveAuditPostRequestBody, httpServletRequest));
            throw getCustomExceptionInstance();
        }
        return getCreditUnionConfigDataGetResponseBody;
    }

    @Override
    public SaveCreditUnionConfigDataPostResponseBody postSaveCreditUnionConfigData(@RequestBody SaveCreditUnionConfigDataPostRequestBody saveCreditUnionConfigDataPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        final String txnId = UUID.randomUUID().toString();
        SaveAuditPostRequestBody saveAuditPostRequestBody;
        SaveCreditUnionConfigDataPostResponseBody saveCreditUnionConfigDataPostResponseBody = null;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            saveCreditUnionConfigDataPostRequestBody.getCreditUnionId();
            saveCreditUnionConfigDataPostResponseBody = adminPresentationService.postSaveCreditUnionConfigData(saveCreditUnionConfigDataPostRequestBody,
                    httpServletRequest);
        } catch (Exception e) {
            saveAuditPostRequestBody = adminPresentationTransformer.mapAsSaveAuditPostRequestBody(null, null, Constants.AUDIT_CHANNEL_ADMIN, Constants.OPERATION_DATABASE,
                    adminPresentationTransformer.getMemberNumber(dataFormatter), e.getMessage(), txnId);
            persistenceSaveAuditClient.postSaveAudit(createInternalRequest(saveAuditPostRequestBody, httpServletRequest));
            throw getCustomExceptionInstance();
        }
        return saveCreditUnionConfigDataPostResponseBody;
    }

    @Override
    public UpdateDataFormatterPostResponseBody postUpdateDataFormatter(@RequestBody UpdateDataFormatterPostRequestBody updateDataFormatterPostRequestBody,
                                                                       HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws BocCustomErrorException, UnauthorizedException, BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException,
            NotFoundException, UnsupportedMediaTypeException {

        UpdateDataFormatterPostResponseBody updateDataFormatterPostResponseBody = null;
        final String txnId = UUID.randomUUID().toString();
        SaveAuditPostRequestBody saveAuditPostRequestBody;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            updateDataFormatterPostResponseBody = adminPresentationService.updateDataFormatter(updateDataFormatterPostRequestBody, httpServletRequest);
        } catch (Exception e) {
            saveAuditPostRequestBody = adminPresentationTransformer.mapAsSaveAuditPostRequestBody(null, updateDataFormatterPostRequestBody.getCreditUnionId(),
                    Constants.AUDIT_CHANNEL_ADMIN, Constants.OPERATION_DATABASE,
                    adminPresentationTransformer.getMemberNumber(dataFormatter), e.getMessage(), txnId);
            persistenceSaveAuditClient.postSaveAudit(createInternalRequest(saveAuditPostRequestBody, httpServletRequest));
            throw getCustomExceptionInstance();
        }

        return updateDataFormatterPostResponseBody;
    }

    @Override
    public UploadJKSFilePostResponseBody postUploadJKSFile(@RequestParam("file") MultipartFile file, @PathVariable("creditUnionId") String creditUnionId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BocCustomErrorException, UnauthorizedException, BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException {
        return adminPresentationService.postUploadJKSFile(file, creditUnionId, httpServletRequest);
    }


    private <T> InternalRequest<T> createInternalRequest(T data, HttpServletRequest httpServletRequest) {
        InternalRequestContext internalRequestContext = DefaultInternalRequestContext.contextFrom(httpServletRequest, idUtils.generateRandomID());
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setInternalRequestContext(internalRequestContext);
        internalRequest.setData(data);
        return internalRequest;
    }

    private BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
