package com.pscode.todoapp.repository;

import com.pscode.todoapp.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TaskModel, Long> {
}
