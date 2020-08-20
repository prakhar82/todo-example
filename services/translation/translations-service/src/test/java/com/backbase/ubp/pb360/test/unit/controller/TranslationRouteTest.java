package com.backbase.ubp.pb360.test.unit.controller;

import com.backbase.ubp.pb360.routes.TranslationsRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.WeakHashMap;

import static com.backbase.ubp.pb360.test.unit.controller.TranslationControllerTest.MOCK_Language_QUERY_PARAM;
import static java.util.stream.Collectors.joining;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =  TranslationsRoute.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class TranslationRouteTest extends TestCase {

    static {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
    }

    @Autowired
    private CamelContext context;

    @Autowired
    private ProducerTemplate template;

    @Value("{{dione-api.protocol}}")
    private String apiProtocol;

    @Value("{{dione-api.host}}")
    private String apiHost;

    @Value("{{dione-api.port}}")
    private String apiPort;

    @Value("{{dione-api.context}}")
    private String apiContext;

    @EndpointInject(uri = "mock:/api/contentservices/content/get")
    private MockEndpoint fakeTranslationControllerRoute;

    @Autowired
    private ObjectMapper mapper;


    public static final String DIRECT_CONTENT_GET_ROUTE_ID = "com.ubp.contentservices.content.get";

    @Test
    public void testGetProfile() throws Exception {


        context.getRouteDefinition("com.ubp.pb360.contentservices.content.get").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(apiProtocol + "://" + apiHost + ":" + apiPort + "/" + apiContext + "/translations")
                        .skipSendToOriginalEndpoint()
                        .to("mock:/api/contentservices/content/get");
            }

        });

        mapper = new ObjectMapper();
        Map<String, String> queryParams = new WeakHashMap<>();

        queryParams.put("Language", MOCK_Language_QUERY_PARAM);


        String queryParamsString = queryParams.entrySet().stream().map(Object::toString).collect(joining("&"));

        context.start();
        fakeTranslationControllerRoute.expectedHeaderReceived(Exchange.HTTP_METHOD, HttpMethod.GET);
        fakeTranslationControllerRoute.expectedHeaderReceived(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        fakeTranslationControllerRoute.expectedHeaderReceived("Accept", MediaType.APPLICATION_JSON_VALUE);
        fakeTranslationControllerRoute.expectedHeaderReceived(Exchange.HTTP_QUERY, queryParamsString);
        //fakeTranslationControllerRoute.assertIsSatisfied();
        context.stop();
    }
}