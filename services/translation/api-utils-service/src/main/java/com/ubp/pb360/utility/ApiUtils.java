package com.ubp.pb360.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/** Class containing all the constant fields */
@Component
public class ApiUtils {
  public final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  public static final String ERROR_ON_CALLING_ENDPOINT = "Error on calling the endpoint";

  @Value("${pms.url}")
  public String pmsUrl;

  @Autowired
  ObjectMapper mapper = new ObjectMapper();

  public RestTemplate template() {
    return new RestTemplate();
  }

  public ObjectMapper getJsonObjectMapper() {
    mapper.setDateFormat(df);
    return mapper;
  }
}