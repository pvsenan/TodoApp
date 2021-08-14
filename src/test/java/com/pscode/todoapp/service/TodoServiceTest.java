package com.pscode.todoapp.service;

import com.pscode.todoapp.model.TaskModel;
import com.pscode.todoapp.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    private final TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private final TodoService todoService = new TodoService(todoRepository);

    @Test
    public void shouldBeAbleToCreateAnewTodoTask(){
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);
        model.setTodoId(1L);

        when(todoRepository.save(model)).thenReturn(model);
        TaskModel result = todoService.createTodo(model);

        Assertions.assertEquals("Send reminder email", result.getTodoName());
        Assertions.assertEquals(false, result.getCompleted());
    }
}