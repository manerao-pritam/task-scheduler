package org.example.service;

import org.example.entity.Task;
import org.example.exception.TaskValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.example.util.TaskUtil.validate;

public class TaskManager {
    private final Map<UUID, Task> tasks = new HashMap<>();
    private final List<ITaskObserver> observers = new ArrayList<>();

    public void addTask(Task task) {
        validate(task);  // Validate before adding
        tasks.put(task.getId(), task);
    }

    public Map<UUID, Task> getTasks() {
        return this.tasks.values().stream()
                .collect(Collectors.toMap(Task::getId, task -> task));
    }

    public Task getTaskById(final UUID taskId) {
        return this.tasks.get(taskId);
    }

    public Task updateTask(final UUID taskId, final Task taskToUpdate) {
        validate(taskToUpdate);  // Validate before adding

        if (!tasks.containsKey(taskId)) {
            throw new TaskValidationException("Task not found");
        }

        Task savedTask = this.tasks.get(taskId);
        savedTask.setTitle(taskToUpdate.getTitle());
        savedTask.setDueDate(taskToUpdate.getDueDate());
        savedTask.setDescription(taskToUpdate.getDescription());
        savedTask.setCompleted(taskToUpdate.isCompleted());

        System.out.println("Task updated.");
        return savedTask;
    }

    public void deleteTask(final UUID taskId) {
        boolean deleted = this.tasks.values().removeIf(task -> task.getId().equals(taskId));

        if (deleted) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task with id " + taskId + " not found.");
        }
    }

    public List<Task> searchTasks(final String filter, final IPriorityStrategy priority) {
        return tasks.values().stream()
                .filter(task -> filter == null || task.getTitle().toLowerCase().contains(filter.toLowerCase()))
                .filter(task -> priority == null || task.getPriority().equals(priority))
                .collect(Collectors.toList());
    }

    public void addObserver(final ITaskObserver observer) {
        this.observers.add(observer);
    }

    public void checkDeadLines() {
        this.tasks.values().stream()
                .filter(task -> task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)))
                .forEach(task -> this.observers.forEach(observer -> observer.onTaskDue(task)));
    }
}
