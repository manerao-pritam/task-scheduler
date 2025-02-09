package org.example.service;

import org.example.entity.Task;

public class EmailNotifier implements ITaskObserver {
    public void onTaskDue(final Task task) {
        System.out.println("Email has been sent to remind about the task: " + task);
    }
}
