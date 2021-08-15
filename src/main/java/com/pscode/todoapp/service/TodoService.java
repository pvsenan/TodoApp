package com.pscode.todoapp.service;

import com.pscode.todoapp.model.TaskModel;
import com.pscode.todoapp.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
            return Optional.of(todoRepository.save(model));
        } else {
            logger.error("Failed to update todo with id {}", todoId);
            return Optional.empty();
        }
    }

    public Optional<List<TaskModel>> getAll() {
        return Optional.of(todoRepository.findAll());
    }

    public Optional<TaskModel> getOne(Long id) {
        return todoRepository.findById(id);
    }

    public Optional<TaskModel> delete(Long todoId) {
        Optional<TaskModel> todoTask = todoRepository.findById(todoId);
        if (todoTask.isPresent()) {
            todoRepository.deleteById(todoId);
            return todoTask;
        } else {
            logger.error("Unable to find todo with id {}", todoId);
            return Optional.empty();
        }
    }
}
