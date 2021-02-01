package com.example.taskmanager.service;

import com.example.taskmanager.comparator.TaskComparator;
import com.example.taskmanager.entity.*;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    TaskService taskService;

    @BeforeEach
    public void initTaskService() {
        taskService = new TaskService(taskRepository, userRepository);
    }

    @Test
    public void serviceFindAllShouldInvokeRepositoryFindAll() {

        List<Task> expected = new ArrayList<>();
        expected.add(getTask());

        when(taskRepository.findAll()).thenReturn(expected);

        List<Task> actual = taskService.findAll();

        Assertions.assertEquals(expected, actual);
        verify(taskRepository, only()).findAll();
    }

    @Test
    public void findByIdShouldReturnValueIfValueIsPresentInDB() {
        Task expected = getTask();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(expected));

        Task actual = taskService.findById(1L);

        verify(taskRepository, only()).findById(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByUnitIdShouldReturnProperResults() {
        List<Task> expected = new ArrayList<>();
        expected.add(getTask());

        when(taskRepository.findAllByUnitId(1)).thenReturn(expected);

        List<Task> actual = taskService.findByUnitId(1L, TaskComparator.BY_CREATION_TIME_ASC);

        verify(taskRepository, only()).findAllByUnitId(anyLong());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void saveTaskShouldSaveValueInDB() {
        Task expected = getTask();

        when(taskRepository.save(any())).thenReturn(expected);
        when(userRepository.findById(1L)).thenReturn(Optional.of(getAuthor()));
        when(userRepository.findById(2L)).thenReturn(Optional.of(getExecutor()));

        Task actual = taskService.saveTask(expected);

        verify(taskRepository, only()).save(any());
        verify(userRepository, times(2)).findById(anyLong());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateTaskShouldSaveValueInDB() {
        Task expected = getTask();

        when(taskRepository.save(any())).thenReturn(expected);

        Task actual = taskService.updateTask(1L, expected);

        verify(taskRepository, only()).save(any());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByUserIdShouldReturnProperResults() {
        List<Task> expected = new ArrayList<>();
        expected.add(getTask());

        when(taskRepository.findAllByUserId(1)).thenReturn(expected);

        List<Task> actual = taskService.findByUserId(1L, TaskComparator.BY_CREATION_TIME_ASC);

        verify(taskRepository, only()).findAllByUserId(anyLong());
        Assertions.assertEquals(expected, actual);
    }


    private Task getTask() {
        return Task.builder()
                .id(1)
                .creationTime(getTime())
                .theme("Theme")
                .description("Description")
                .author(getAuthor())
                .executor(getExecutor())
                .status(Status.BLOCKED)
                .comments(getComments())
                .attachments(getAttachments())
                .build();
    }

    private User getAuthor() {
        return User.builder()
                .id(1)
                .name("Author")
                .rating(3)
                .build();
    }

    private User getExecutor() {
        return User.builder()
                .id(2)
                .name("Executor")
                .rating(1)
                .build();
    }

    private LocalDateTime getTime() {
        return LocalDateTime.of(2021, 1, 31, 12, 0);
    }

    private ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();

        comments.add(new Comment(new UserTaskPK(1, 1), "Comment 1", getAuthor()));
        comments.add(new Comment(new UserTaskPK(2, 1), "Comment 2", getExecutor()));

        return comments;
    }

    private ArrayList<Attachment> getAttachments() {
        ArrayList<Attachment> attachments = new ArrayList<>();

        attachments.add(new Attachment(1, "Reference/1"));
        attachments.add(new Attachment(2, "Reference/1"));

        return attachments;
    }
}
