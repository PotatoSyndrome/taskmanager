package com.example.taskmanager.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "creation_time", nullable = true)
    private LocalDateTime creationTime;

    @Column
    private String theme;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Attachment> attachments;
}
