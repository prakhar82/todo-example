package com.boc.documents;


import com.backbase.bb.rest.spec.v1.documents.Document;
import com.backbase.bb.rest.spec.v1.documents.PersonalDocumentsApi;
import com.backbase.bb.rest.spec.v1.documents.PersonalDocumentsGetResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/v1/documents")
@RestController
public class DocumentListController implements PersonalDocumentsApi {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();


    @Override
    public PersonalDocumentsGetResponseBody getPersonalDocuments(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return parseTodoJson();
    }

    private PersonalDocumentsGetResponseBody parseTodoJson() {
        String json = null;
        PersonalDocumentsGetResponseBody personalDocumentsGetResponseBody  = new PersonalDocumentsGetResponseBody();
        List<Document> documents =null;
        try {
            json = executeRequest();
            // @formatter:off

            documents = new ObjectMapper().readValue(new JSONObject(json).getJSONArray("Documents").toString(), new TypeReference<ArrayList<Document>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        personalDocumentsGetResponseBody.setDocuments(documents);
        personalDocumentsGetResponseBody.setStatus("200");

        return personalDocumentsGetResponseBody;
    }

    private String executeRequest() throws IOException {
        try {

            return new String(Files.readAllBytes(Paths.get("../extras/document.json")));

        } catch (Exception e) {

            return new String(Files.readAllBytes(Paths.get("../extras/document.json")));
        }
    }

}