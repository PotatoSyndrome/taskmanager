package com.example.taskmanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String reference;
}
