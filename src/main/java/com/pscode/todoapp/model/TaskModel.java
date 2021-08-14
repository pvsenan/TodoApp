package com.pscode.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

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
    private Long todoId;

    @Column(name="todo_name")
    private String todoName;

    @Column(name="todo_status")
    private Boolean todoStatus;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
