package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.TaskUtil.isValid;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        if (!isValid(task)) {
            System.out.println("Error adding task " + task.getTitle() + ". Task due date can't be in the past.");
            return;
        }
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    public Task getTaskById(final UUID taskId) {
        for (Task task: this.tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }

        System.out.println("Task with id " + taskId + " not found.");
        return null;
    }

    public Task updateTask(final UUID taskId, final Task taskToUpdate) {
        // validate task
        if (!isValid(taskToUpdate)) {
            System.out.println("Update failed. Task due date can't be in the past.");
            return null;
        }

        Task savedTask = this.getTaskById(taskId);
        if (savedTask == null) {
            return null;
        }

        savedTask.setTitle(taskToUpdate.getTitle());
        savedTask.setDueDate(taskToUpdate.getDueDate());
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
