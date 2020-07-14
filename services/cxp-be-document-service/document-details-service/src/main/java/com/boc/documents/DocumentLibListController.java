package com.boc.documents;



import com.backbase.bb.rest.spec.v1.documentslib.Document;
import com.backbase.bb.rest.spec.v1.documentslib.DocumentsLibraryApi;
import com.backbase.bb.rest.spec.v1.documentslib.DocumentsLibraryGetResponseBody;
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


@RequestMapping("/v1/documentslib")
@RestController
public class DocumentLibListController implements DocumentsLibraryApi {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();


    @Override
    public DocumentsLibraryGetResponseBody getDocumentsLibrary(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return parseTodoJson();
    }

    private DocumentsLibraryGetResponseBody parseTodoJson() {
        String json = null;
        DocumentsLibraryGetResponseBody documentsLibraryGetResponseBody   = new DocumentsLibraryGetResponseBody();
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

        documentsLibraryGetResponseBody.setDocuments(documents);
        documentsLibraryGetResponseBody.setStatus("200");

        return documentsLibraryGetResponseBody;
    }

    private String executeRequest() throws IOException {
        try {

            return new String(Files.readAllBytes(Paths.get("../extras/documentslib.json")));

        } catch (Exception e) {

            return new String(Files.readAllBytes(Paths.get("../extras/documentslib.json")));
        }
    }


}