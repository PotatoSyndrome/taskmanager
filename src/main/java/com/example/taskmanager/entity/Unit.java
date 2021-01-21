package com.example.taskmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @OneToMany
    @JoinColumn(name = "unit_id")
    private List<User> users;
}
