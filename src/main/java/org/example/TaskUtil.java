package org.example;

import java.time.LocalDateTime;

public class TaskUtil {
    public static boolean isValid(final Task task) {
        LocalDateTime taskDueDate = task.getDueDate();
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(taskDueDate);
    }
}
