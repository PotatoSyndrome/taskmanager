package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task WHERE executor_id IN (SELECT id FROM user WHERE unit_id = ?1)", nativeQuery = true)
    public List<Task> findAllByUnitId(long unitId);

    @Query(value = "SELECT * FROM task WHERE executor_id = ?1", nativeQuery = true)
    public List<Task> findAllByUserId(long userId);
}
