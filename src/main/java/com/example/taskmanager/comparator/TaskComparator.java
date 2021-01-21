package com.example.taskmanager.comparator;

import com.example.taskmanager.entity.Task;

import java.util.Comparator;

public enum TaskComparator implements Comparator<Task> {
    BY_CREATION_TIME_ASC {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getCreationTime().compareTo(task2.getCreationTime());
        }
    },
    BY_CREATION_TIME_DESC {
        @Override
        public int compare(Task task1, Task task2) {
            return BY_CREATION_TIME_ASC.reversed().compare(task1, task2);
        }
    }
}
