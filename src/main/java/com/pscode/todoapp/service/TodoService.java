package com.pscode.todoapp.service;

import com.pscode.todoapp.repository.TodoRepository;
import com.pscode.todoapp.model.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    Logger logger = LoggerFactory.getLogger(TodoService.class);

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Optional<TaskModel> createTodo(TaskModel newTodoTask) {
        logger.info("Creating new todo");
        return Optional.of(todoRepository.save(newTodoTask));
    }

    public Optional<TaskModel> editExistingTodo(TaskModel updatedTodoTask, Long todoId) {
        Optional<TaskModel> todoTask = todoRepository.findById(todoId);
        if (todoTask.isPresent()) {
            TaskModel model = todoTask.get();
            model.setTodoName(updatedTodoTask.getTodoName());
            model.setCompleted(updatedTodoTask.getCompleted());
            model.setCreatedAt(LocalDateTime.now());
            todoRepository.save(model);
            return Optional.of(model);
        } else {
            logger.error("Failed to update todo with id {}", todoId);
            return Optional.empty();
        }
    }
}
