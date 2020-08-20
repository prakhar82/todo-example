package com.backbase.ubp.pb360.test.unit.controller;

import com.backbase.ubp.pb360.controllers.impl.TranslationsController;
import com.backbase.ubp.pb360.model.ContentServicesLink;
import com.backbase.ubp.pb360.model.ContentServicesQueryRequest;
import com.backbase.ubp.pb360.model.ContentServicesQueryResponse;
import com.backbase.ubp.pb360.model.HrefLink;
import com.backbase.ubp.pb360.routes.TranslationsRoute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import static java.util.stream.Collectors.joining;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TranslationsController.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan
public class TranslationControllerTest {

    static {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
    }

    public static final String MOCK_Language_QUERY_PARAM = "mock:name";
    private static final String MOCK_LANG_QUERY_PARAM = "mock:name";
    private static final String MOCK_PROFILE_GET_RESPONSE_BODY = "mock:profileGetResponseBody";

    public final Logger logger = LoggerFactory.getLogger(TranslationsController.class);

    @Mock
    private ProducerTemplate template;

    @Mock
    private ObjectMapper fakeMapper;

    @InjectMocks
    @Spy
    TranslationsController translationsController;

    @Spy
    private ContentServicesQueryRequest requests;

    @Mock
    HttpServletRequest fakeRequest;

    @Mock
    HttpServletResponse fakeResponse;

    @Test
    public void testTranslations() throws IOException {
        Map<String, String> queryParams = new WeakHashMap<>();
        queryParams.put("Language", MOCK_LANG_QUERY_PARAM);
        String queryParamsString = queryParams.entrySet().stream().map(Object::toString).collect(joining("&"));
        List<ContentServicesQueryResponse> response=new ArrayList<>();
        ContentServicesQueryResponse response1=new ContentServicesQueryResponse();
        response1.setId("12");
        response1.setPath("ss");
        response1.setType("dkkd");
        response1.setRepositoryId("dk");
        HrefLink hrefLink=new HrefLink();
        hrefLink.setHref("Dummy");
        ContentServicesLink contentServicesLink=new ContentServicesLink();
        contentServicesLink.setContentByPath(hrefLink);
        response1.setLinks(contentServicesLink);
        response.add(response1);
        hrefLink.setHref("dkdkkd");
        ContentServicesLink link =new ContentServicesLink();
        link.setContentById(hrefLink);
        link.setContentByPath(hrefLink);
        when(requests.withPath("/i18n/"+"lang")).thenReturn(requests);
        when(template.requestBodyAndHeader(TranslationsRoute.DIRECT_CONTENTSERVICES_CONTENT_GET, null, "contentHref", response.get(0).getLinks().getContentByPath().getHref(), String.class)).thenReturn(MOCK_PROFILE_GET_RESPONSE_BODY);
        when(template.requestBody(TranslationsRoute.DIRECT_CONTENTSERVICES_CONTENT_QUERY_POST, fakeMapper.writeValueAsString(fakeRequest), String.class)).thenReturn(MOCK_PROFILE_GET_RESPONSE_BODY);
        when(fakeMapper.readValue(eq(MOCK_PROFILE_GET_RESPONSE_BODY), any(TypeReference.class))).thenReturn(response);

        translationsController.getTranslations(MOCK_LANG_QUERY_PARAM, fakeRequest, fakeResponse);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response1);
        Assert.assertNotNull(hrefLink);
        Assert.assertNotNull(link);
    }

}