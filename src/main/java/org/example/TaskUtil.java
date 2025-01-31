package org.example;

import java.time.LocalDateTime;

public class TaskUtil {
    public static boolean isValid(final Task task) {
        if (task == null) {
            return false;  // Task is null, invalid
        }

        String taskTitle = task.getTitle();
        LocalDateTime taskDueDate = task.getDueDate();
        LocalDateTime now = LocalDateTime.now();

        // Title should not be null or empty, dueDate should not be null and should be in the future
        return taskTitle != null && !taskTitle.trim().isEmpty() &&
                taskDueDate != null && now.isBefore(taskDueDate);
    }
}
