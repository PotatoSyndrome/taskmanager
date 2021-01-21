package com.example.taskmanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_comments_task")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @EmbeddedId
    UserTaskPK id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
