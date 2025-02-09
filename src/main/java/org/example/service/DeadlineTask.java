package org.example.service;

import org.example.entity.Task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(final LocalDateTime deadline) {
        super();
        this.deadline = deadline;
    }

    public DeadlineTask(final String title, final LocalDateTime dueDate, final LocalDateTime deadline) {
        super(title, dueDate);
        this.deadline = deadline;
    }

    public DeadlineTask(final String title, final LocalDateTime dueDate,
                        final String description, final LocalDateTime deadline) {
        super(title, dueDate, description);
        this.deadline = deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
