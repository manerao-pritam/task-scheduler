package org.example;

import java.time.LocalDateTime;

public class TaskUtil {
    public static void validate(Task task) throws TaskValidationException {
        if (task == null) {
            throw new TaskValidationException("Task can't be null.");
        }

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new TaskValidationException("Task title can't be empty.");
        }

        if (task.getDueDate() == null) {
            throw new TaskValidationException("Due date can't be null.");
        }

        if (task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new TaskValidationException("Due date can't be in the past.");
        }
    }
}
