package com.pscode.todoapp.controller;

import com.pscode.todoapp.model.TaskModel;
import com.pscode.todoapp.service.TodoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public TaskModel crateNewTodo(@Validated @RequestBody TaskModel newTodoTask){
        return todoService.createTodo(newTodoTask);
    }
}

