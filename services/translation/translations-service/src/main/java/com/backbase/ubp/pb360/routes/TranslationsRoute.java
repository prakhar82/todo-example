package com.backbase.ubp.pb360.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class TranslationsRoute extends RouteBuilder {

    private static final String LOG_QUERY_PARAMS = "?showHeaders=true&showExchangeId=true&showBody=true&level=DEBUG";
    private static final String CAMEL_HTTP_PATTERN = "CamelHttp*";

    public static final String DIRECT_CONTENTSERVICES_CONTENT_QUERY_POST = "direct:/contentservices/content/query/post";
    public static final String DIRECT_CONTENTSERVICES_CONTENT_GET = "direct:/contentservices/content/get";

    @Override
    public void configure() throws Exception {
        from(DIRECT_CONTENTSERVICES_CONTENT_QUERY_POST)
            .routeId("com.ubp.pb360.contentservices.content.query.post")
            .removeHeaders(CAMEL_HTTP_PATTERN, Exchange.HTTP_METHOD, Exchange.HTTP_QUERY)
            .setHeader(Exchange.HTTP_METHOD, constant(RequestMethod.POST))
            .setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
            .setHeader(HttpHeaders.ACCEPT, constant(MediaType.APPLICATION_JSON_VALUE))
            .to("log:com.ubp.pb360.contentservices.content.query.post"+LOG_QUERY_PARAMS)
            .to("{{gateway.protocol}}://{{gateway.host}}:{{gateway.port}}{{gateway.context}}/contentservices/api/content/query")
            .end();

        from(DIRECT_CONTENTSERVICES_CONTENT_GET)
                .routeId("com.ubp.pb360.contentservices.content.get")
                .removeHeaders(CAMEL_HTTP_PATTERN, Exchange.HTTP_METHOD, Exchange.HTTP_QUERY)
                .setHeader(Exchange.HTTP_METHOD, constant(RequestMethod.GET))
                .setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
                .setHeader(HttpHeaders.ACCEPT, constant(MediaType.APPLICATION_JSON_VALUE))
                .to("log:com.ubp.pb360.contentservices.content.get"+LOG_QUERY_PARAMS)
                .toD("{{gateway.protocol}}://{{gateway.host}}:{{gateway.port}}{{gateway.context}}${header.contentHref}")
                .end();
    }
}
