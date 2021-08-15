package com.pscode.todoapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pscode.todoapp.model.TaskModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnNewlyCreatedTask() throws Exception {
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);

        String requestBody = objectMapper.writeValueAsString(model);

        MvcResult result = mockMvc.perform(
                post("/api/v1/todo/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andExpect(status().isOk())
            .andReturn();

        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Send reminder email"));
    }

    @Test
    public void shouldGetOneTodoTask() throws Exception {
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);

        String requestBody = objectMapper.writeValueAsString(model);

        MvcResult result = mockMvc.perform(
                post("/api/v1/todo/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andReturn();

        TaskModel todoModel = objectMapper.readValue(result.getResponse().getContentAsString(), TaskModel.class);

        MvcResult response = mockMvc.perform(
                get("/api/v1/todo/get/{id}", todoModel.getTodoId()))
            .andReturn();

        Assertions.assertTrue(response.getResponse().getContentAsString().contains("Send reminder email"));
    }

    @Test
    public void shouldDeleteOneTodoTask() throws Exception {
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);

        String requestBody = objectMapper.writeValueAsString(model);

        MvcResult result = mockMvc.perform(
                post("/api/v1/todo/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andReturn();

        TaskModel todoModel = objectMapper.readValue(result.getResponse().getContentAsString(), TaskModel.class);

        mockMvc.perform(delete("/api/v1/todo/delete/{id}", todoModel.getTodoId()));

        mockMvc.perform(get("/api/v1/todo/get/{id}", todoModel.getTodoId()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldEditOneTodoTask() throws Exception {
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);

        String requestBody = objectMapper.writeValueAsString(model);

        MvcResult result = mockMvc.perform(
                post("/api/v1/todo/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andReturn();

        TaskModel todoModel = objectMapper.readValue(result.getResponse().getContentAsString(), TaskModel.class);
        todoModel.setTodoName("This is an edited todo");
        todoModel.setCompleted(true);

        MvcResult response = mockMvc.perform(patch("/api/v1/todo/edit/{id}", todoModel.getTodoId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todoModel)))
            .andExpect(status().isOk())
            .andReturn();

        Assertions.assertTrue(response.getResponse().getContentAsString().contains("This is an edited todo"));
    }

    @Test
    public void shouldComplainWhenDeleteNonExistingTodo() throws Exception {
        mockMvc.perform(delete("/api/v1/todo/delete/{id}", 1001))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldComplainWhenUnableToFindTodo() throws Exception {
        mockMvc.perform(get("/api/v1/todo/get/{id}", 1002))
            .andExpect(status().isNotFound());
    }

}