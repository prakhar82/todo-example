package com.boc.service.helper;

import com.backbase.adminPresentation.rest.spec.v1.admin.GetCreditUnionConfigDataGetResponseBody;
import com.backbase.adminPresentation.rest.spec.v1.admin.UploadCSVFilePostResponseBody;
import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.backend.internalrequest.DefaultInternalRequestContext;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequestContext;
import com.backbase.persistence.listener.client.v1.authinfo.PersistenceApiAuthInfoClient;
import com.backbase.persistence.listener.client.v1.changeFormatter.PersistenceChangeFormatterClient;
import com.backbase.persistence.listener.client.v1.creditunion.PersistenceCreditUnionClient;
import com.backbase.persistence.listener.client.v1.getaudit.PersistenceGetAuditClient;
import com.backbase.persistence.listener.client.v1.getconfigdata.PersistenceConfigDataClient;
import com.backbase.persistence.listener.client.v1.leadsopportunities.PersistenceLeadsAndOpportunitiesClient;
import com.backbase.persistence.listener.client.v1.saveconfigdata.PersistenceSaveConfigDataClient;
import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostRequestBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostResponseBody;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostResponseBody;
import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesPostRequestBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesPostResponseBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QueueHelper {
    @Autowired
    PersistenceLeadsAndOpportunitiesClient persistenceLeadsAndOpportunitiesClient;
    @Autowired
    PersistenceGetAuditClient persistenceGetAuditClient;
    @Autowired
    PersistenceConfigDataClient persistenceConfigDataClient;
    @Autowired
    PersistenceSaveConfigDataClient persistenceSaveConfigDataClient;
    @Autowired
    PersistenceChangeFormatterClient persistenceChangeFormatterClient;
    @Autowired
    PersistenceApiAuthInfoClient persistenceApiAuthInfoClient;
    @Autowired
    PersistenceCreditUnionClient persistenceCreditUnionClient;
    @Autowired
    IDUtils idUtils;

    public List<LeadsAndOpportunitiesGetResponseBody> getLeadsAndOpportunities(String creditUnionId, String downloadAll, HttpServletRequest httpServletRequest) {
        return persistenceLeadsAndOpportunitiesClient.getLeadsAndOpportunities(createInternalRequest(null, httpServletRequest), creditUnionId, downloadAll).getRequest().getData();
    }

    public LeadsAndOpportunitiesPostResponseBody postLeadsAndOpportunities(LeadsAndOpportunitiesPostRequestBody leadOpportunityList, HttpServletRequest httpServletRequest) {
        InternalRequest<LeadsAndOpportunitiesPostRequestBody>  tInternalRequest= createInternalRequest(leadOpportunityList,httpServletRequest);
         return persistenceLeadsAndOpportunitiesClient.postLeadsAndOpportunities(tInternalRequest).getRequest().getData();
    }

    public List<GetAuditPostResponseBody> postGetAudit(GetAuditPostRequestBody getAuditPostRequestBody, HttpServletRequest httpServletRequest) {
        return persistenceGetAuditClient.postGetAudit(createInternalRequest(getAuditPostRequestBody, httpServletRequest)).getRequest().getData();
    }

    public ConfigDataByCreditUnionIdGetResponseBody getGetCreditUnionConfigData(String creditUnionId, HttpServletRequest httpServletRequest) {
        return persistenceConfigDataClient.getConfigDataByCreditUnionId(createInternalRequest(null,
                httpServletRequest), creditUnionId).getRequest().getData();
    }

    public SaveConfigDataPostResponseBody postSaveCreditUnionConfigData(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody, HttpServletRequest httpServletRequest) {
        return persistenceSaveConfigDataClient.postSaveConfigData(createInternalRequest(saveConfigDataPostRequestBody, httpServletRequest)).getRequest().getData();
    }

    public ChangeFormatterPostResponseBody updateDataFormatter(ChangeFormatterPostRequestBody changeFormatterPostRequestBody, HttpServletRequest httpServletRequest) {
        return persistenceChangeFormatterClient.postChangeFormatter(createInternalRequest(changeFormatterPostRequestBody, httpServletRequest)).getRequest().getData();
    }

    public ApiAuthInfoGetResponseBody getCreditUnionAppAuthByCreditUnionId(String creditUnionId, HttpServletRequest httpServletRequest) {
        return persistenceApiAuthInfoClient.getApiAuthInfo(createInternalRequest(null, httpServletRequest), creditUnionId).getRequest().getData();
    }

    public GetCreditUnionByIdGetResponseBody getGetCreditUnionById(String creditUnionId, HttpServletRequest httpServletRequest) {
        return persistenceCreditUnionClient.getGetCreditUnionById(createInternalRequest(null, httpServletRequest),
                creditUnionId).getRequest().getData();
    }

    public <T> InternalRequest<T> createInternalRequest(T data, HttpServletRequest httpServletRequest) {
        InternalRequestContext internalRequestContext = DefaultInternalRequestContext.contextFrom(httpServletRequest, idUtils.generateRandomID());
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setInternalRequestContext(internalRequestContext);
        internalRequest.setData(data);
        return internalRequest;
    }


}
