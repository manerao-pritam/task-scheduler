package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.example.TaskUtil.validate;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        validate(task);  // Validate before adding
        tasks.add(task);
    }

    public Map<UUID, Task> getTasks() {
        return this.tasks.stream()
                .collect(Collectors.toMap(Task::getId, task -> task));
    }

    public Task getTaskById(final UUID taskId) {
        return this.tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElse(null);
    }

    public Task updateTask(final UUID taskId, final Task taskToUpdate) {
        validate(taskToUpdate);  // Validate before adding

        Task savedTask = this.getTaskById(taskId);
        if (savedTask == null) {
            return null;
        }

        savedTask.setTitle(taskToUpdate.getTitle());
        savedTask.setDueDate(taskToUpdate.getDueDate());
        savedTask.setDescription(taskToUpdate.getDescription());
        savedTask.setCompleted(taskToUpdate.isCompleted());

        System.out.println("Task updated.");
        return savedTask;
    }

    public void deleteTask(final UUID taskId) {
        boolean deleted = this.tasks.removeIf(task -> task.getId().equals(taskId));

        if (deleted) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task with id " + taskId + " not found.");
        }
    }
}
