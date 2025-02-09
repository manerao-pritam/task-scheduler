package org.example.entity;

import org.example.service.IPriorityStrategy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private final UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean isCompleted = false;
    private IPriorityStrategy priority;

    public Task() {}

    public Task(final String title, final LocalDateTime dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public Task(final String title, final LocalDateTime dueDate, final String description) {
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description.trim();
    }

    public IPriorityStrategy getPriority() {
        return priority;
    }

    public void setPriority(final IPriorityStrategy priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted && Objects.equals(title, task.title)
                && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate)
                && Objects.equals(priority, task.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, dueDate, isCompleted, priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", isCompleted=" + isCompleted +
                ", priority=" + priority +
                '}';
    }

}
