package com.example.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskPK implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "task_id")
    private long taskId;
}
