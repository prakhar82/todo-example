package com.boc.service;

import com.backbase.adminPresentation.rest.spec.v1.admin.*;
import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface AdminPresentationService {

    String getSavedCsvFilePath(String creditUnionId, String downloadAll, HttpServletRequest httpServletRequest);

    UploadCSVFilePostResponseBody saveCsvFileData(String creditUnionId, MultipartFile csvFile, HttpServletRequest httpServletRequest, String dataFormatter);

    GetCreditUnionConfigDataGetResponseBody getGetCreditUnionConfigData(String creditUnionId, HttpServletRequest httpServletRequest);

    SaveCreditUnionConfigDataPostResponseBody postSaveCreditUnionConfigData(SaveCreditUnionConfigDataPostRequestBody saveCreditUnionConfigDataPostRequestBody,
                                                                            HttpServletRequest httpServletRequest);

    String getSavedAuditFilePath(DownloadAuditFilePostRequestBody downloadAuditFilePostRequestBody, HttpServletRequest httpServletRequest);

    UpdateDataFormatterPostResponseBody updateDataFormatter(UpdateDataFormatterPostRequestBody updateDataFormatterPostRequestBody,
                                                            HttpServletRequest httpServletRequest);

    UploadJKSFilePostResponseBody postUploadJKSFile(MultipartFile file, String creditUnionId, HttpServletRequest httpServletRequest);
}
