package com.pscode.todoapp.controller;

import com.pscode.todoapp.model.TaskModel;
import com.pscode.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> crateNewTodo(@Validated @RequestBody TaskModel newTodoTask){
        Optional<TaskModel> result = todoService.createTodo(newTodoTask);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> editTodo(@Validated @RequestBody TaskModel editedTodoTask, @PathVariable Long id){
        Optional<TaskModel> result = todoService.editExistingTodo(editedTodoTask, id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

