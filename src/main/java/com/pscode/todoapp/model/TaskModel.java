package com.pscode.todoapp.model;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TODO")
public class TaskModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="todo_id")
    @Hidden
    private Long todoId;

    @Column(name="todo_name")
    private String todoName;

    @Column(name="completed")
    private Boolean completed;

    @Column(name="created_at")
    @Hidden
    private LocalDateTime createdAt = LocalDateTime.now();
}
