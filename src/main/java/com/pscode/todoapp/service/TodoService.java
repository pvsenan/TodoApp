package com.pscode.todoapp.service;

import com.pscode.todoapp.repository.TodoRepository;
import com.pscode.todoapp.model.TaskModel;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TaskModel createTodo(TaskModel newTodoTask) {
        return todoRepository.save(newTodoTask);
    }
}
