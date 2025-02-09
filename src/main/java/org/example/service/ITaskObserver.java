package org.example.service;

import org.example.entity.Task;

public interface ITaskObserver {
    void onTaskDue(final Task task);
}
