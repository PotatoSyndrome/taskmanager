package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.entity.UserTaskPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UserTaskPK> {
}
