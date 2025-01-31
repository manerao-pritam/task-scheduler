package org.example;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private final UUID id = UUID.randomUUID();
    private String title;
    private LocalDateTime dueDate;
    private boolean isCompleted = false;

    public Task() {}

    public Task(final String title, final LocalDateTime dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", isCompleted=" + isCompleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted && Objects.equals(title, task.title)
                && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dueDate, isCompleted);
    }
}
