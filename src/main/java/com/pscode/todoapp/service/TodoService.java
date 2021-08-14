package com.pscode.todoapp.service;

import com.pscode.todoapp.repository.TodoRepository;
import com.pscode.todoapp.model.TaskModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Optional<TaskModel> createTodo(TaskModel newTodoTask) {
        return Optional.of(todoRepository.save(newTodoTask));
    }

    public Optional<TaskModel> editExistingTodo(TaskModel updatedTodoTask, Long todoId){
        Optional<TaskModel> todoTask = todoRepository.findById(todoId);
        if(todoTask.isPresent()){
            TaskModel model = todoTask.get();
            model.setTodoName(updatedTodoTask.getTodoName());
            model.setCompleted(updatedTodoTask.getCompleted());
            model.setCreatedAt(LocalDateTime.now());
            todoRepository.save(model);
            return Optional.of(model);
        }else{
            // Add logging
            return Optional.empty();
        }
    }
}
