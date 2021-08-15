package com.pscode.todoapp.controller;

import antlr.StringUtils;
import com.pscode.todoapp.model.TaskModel;
import com.pscode.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
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

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodos(){
        Optional<List<TaskModel>> todo = todoService.getAll();
        if(todo.isPresent()){
            return ResponseEntity.ok(todo.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneTodo(@PathVariable Long id){
        Optional<TaskModel> todo = todoService.getOne(id);
        if(todo.isPresent()){
            return ResponseEntity.ok(todo.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        Optional<TaskModel> todo = todoService.delete(id);
        if(todo.isPresent()){
            return ResponseEntity.ok(todo.get());
        }else{
            String error = String.format("Unable to find todo with id %d. Please make sure that the id is correct", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}

