package com.example.taskmanager.controller;

import com.example.taskmanager.comparator.TaskComparator;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @GetMapping("/{id}")
    public Task getOne(@PathVariable long id) {
        return taskService.findById(id);
    }

    @GetMapping("/unit/{id}")
    public List<Task> getByUnit(@PathVariable("id") Long id) {
        return taskService.findByUnitId(id, TaskComparator.BY_CREATION_TIME_ASC);
    }

    @GetMapping("/user/{id}")
    public List<Task> getByUser(@PathVariable("id") Long id) {
        return taskService.findByUserId(id, TaskComparator.BY_CREATION_TIME_DESC);
    }

    @GetMapping("/user/{id}/{comparator}")
    public List<Task> getByUserSorted(@PathVariable("id") Long id,
                                      @PathVariable("comparator") TaskComparator comparator) {
        return taskService.findByUserId(id, comparator);
    }
}
