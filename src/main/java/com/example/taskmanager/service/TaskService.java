package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findByUnitId(Long id, Comparator<Task> comparator) {
        List<Task> tasks = taskRepository.findAllByUnitId(id);
        tasks.sort(comparator);
        return tasks;
    }

    public Task saveTask(Task task) {
        task.setCreationTime(LocalDateTime.now());
        task.setAuthor(userRepository.findById(task.getAuthor().getId()).orElse(null));
        task.setExecutor(userRepository.findById(task.getExecutor().getId()).orElse(null));
        return taskRepository.save(task);
    }

    public Task updateTask(long id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public List<Task> findByUserId(Long id, Comparator<Task> comparator) {
        List<Task> tasks = taskRepository.findAllByUserId(id);
        tasks.sort(comparator);
        return tasks;
    }
}
