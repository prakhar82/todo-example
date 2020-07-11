package com.boc.todos;

import com.backbase.bb.rest.spec.v1.todos.TodoItem;
import com.backbase.bb.rest.spec.v1.todos.TodoItemsListApi;
import com.backbase.bb.rest.spec.v1.todos.TodoItemsListGetResponseBody;
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


@RequestMapping("/v1/todos")
@RestController
public class TodoListController implements TodoItemsListApi {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Override
    public TodoItemsListGetResponseBody getTodoItemsList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  {
        return parseTodoJson();
    }


    private TodoItemsListGetResponseBody parseTodoJson() {
        String json = null;
        TodoItemsListGetResponseBody todoItemsListGetResponseBody = new TodoItemsListGetResponseBody();
        List<TodoItem> todoItems =null;
        try {
            json = executeRequest();
            // @formatter:off

            todoItems = new ObjectMapper().readValue(new JSONObject(json).getJSONArray("TodoItems").toString(), new TypeReference<ArrayList<TodoItem>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        todoItemsListGetResponseBody.setTodoItems(todoItems);
        todoItemsListGetResponseBody.setStatus("200");

        return todoItemsListGetResponseBody;
    }

    private String executeRequest() throws IOException {
        try {

            return new String(Files.readAllBytes(Paths.get("../extras/example-response.json")));

        } catch (Exception e) {

            return new String(Files.readAllBytes(Paths.get("../extras/example-response.json")));
        }
    }


}