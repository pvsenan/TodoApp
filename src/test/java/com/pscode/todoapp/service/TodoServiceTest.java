package com.pscode.todoapp.service;

import com.pscode.todoapp.model.TaskModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TodoServiceTest {

    private final TodoService todoService;

    @Autowired
    public TodoServiceTest(TodoService todoService) {
        this.todoService = todoService;
    }

    @Test
    public void shouldBeAbleToCreateAnewTodoTask() {

        TaskModel todoTask = createTodo();
        Optional<TaskModel> result = todoService.createTodo(todoTask);

        Assertions.assertEquals("Send reminder email", result.get().getTodoName());
        Assertions.assertEquals(false, result.get().getCompleted());
    }

    @Test
    public void shouldBeAbleToEditAnExistingTodoTask() {
        TaskModel todoTask = createTodo();

        TaskModel result = todoService.createTodo(todoTask).get();
        result.setTodoName("Do not send reminder");

        TaskModel updatedTodo = todoService.editExistingTodo(result, result.getTodoId()).get();
        Assertions.assertEquals("Do not send reminder", updatedTodo.getTodoName());
        Assertions.assertEquals(false, result.getCompleted());
    }

    @Test
    public void shouldFailWhenEditANonExistingTodoTask() {
        TaskModel todoTask = new TaskModel();

        Optional<TaskModel> result = todoService.editExistingTodo(todoTask, 1L);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldBeAbleToListAllTodoTasks() {

        createTodo();
        createTodo();
        createTodo();

        Optional<List<TaskModel>> result = todoService.getAll();

        Assertions.assertEquals(3, result.get().size());
    }

    @Test
    public void shouldBeAbleToGetTodoById() {

        TaskModel todo = createTodo();

        Optional<TaskModel> result = todoService.getOne(todo.getTodoId());

        Assertions.assertEquals("Send reminder email", result.get().getTodoName());
    }

    @Test
    public void shouldBeAbleToDeleteAnExistingTodo(){
        TaskModel todo = createTodo();
        todoService.delete(todo.getTodoId());
        Optional<TaskModel> result = todoService.getOne(todo.getTodoId());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyWhenAttemptingToDeleteANonExistingTodo(){
        Optional<TaskModel> result = todoService.delete(1L);
        Assertions.assertTrue(result.isEmpty());
    }

    private TaskModel createTodo() {
        TaskModel model = new TaskModel();
        model.setTodoName("Send reminder email");
        model.setCompleted(false);
        return todoService.createTodo(model).get();
    }
}