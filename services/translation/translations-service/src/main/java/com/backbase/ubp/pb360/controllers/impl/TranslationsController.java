package com.backbase.ubp.pb360.controllers.impl;

import com.backbase.ubp.pb360.controllers.TranslationsApi;
import com.backbase.ubp.pb360.model.ContentServicesQueryRequest;
import com.backbase.ubp.pb360.model.ContentServicesQueryResponse;
import com.backbase.ubp.pb360.routes.TranslationsRoute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class TranslationsController implements TranslationsApi {
    private static final String IOEXCEPTION = "IOException";
    @EndpointInject
    private final ProducerTemplate template;

    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(TranslationsController.class);
    @Autowired
    public TranslationsController(ProducerTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }


    /**
     *
     * @param lang
     * @param httpServletRequest
     * @param httpServletResponse
     * @return String
     */
    @Override
    public String getTranslations(@RequestParam(value = "lang", required = true) String lang, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String translations = null;


        ContentServicesQueryRequest request = new ContentServicesQueryRequest().withPath("/i18n/" + lang);

        try {
            String response = template.requestBody(TranslationsRoute.DIRECT_CONTENTSERVICES_CONTENT_QUERY_POST, mapper.writeValueAsString(request), String.class);

            List<ContentServicesQueryResponse> contentServicesResponses = mapper.readValue(response, new TypeReference<List<ContentServicesQueryResponse>>() {});

            if (!contentServicesResponses.isEmpty()) {
                translations = template.requestBodyAndHeader(TranslationsRoute.DIRECT_CONTENTSERVICES_CONTENT_GET, null, "contentHref", contentServicesResponses.get(0).getLinks().getContentByPath().getHref(), String.class);
            } else {
                httpServletResponse.sendError(HttpStatus.NOT_FOUND.value());
            }
        }
         catch (IOException e) {
           logger.error(IOEXCEPTION,e);
        }

        return translations;
    }
}
