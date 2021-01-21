package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.entity.UserTaskPK;
import com.example.taskmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAll() {
        return commentService.findAll();
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @DeleteMapping("/{userId}/{taskId}")
    public void deleteComment(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId) {
        commentService.deleteComment(commentService.findById(new UserTaskPK(userId, taskId)));
    }
}
