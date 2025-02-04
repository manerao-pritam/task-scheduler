package org.example.service;

import org.example.entity.Task;

public class TaskPriorityDecorator extends Task {
    private final Task task;
    private final IPriorityStrategy priorityStrategy;

    public TaskPriorityDecorator(final Task task, final IPriorityStrategy priorityStrategy) {
        super(task.getTitle(), task.getDueDate());
        this.task = task;
        this.priorityStrategy = priorityStrategy;
    }

    public Task getTask() {
        return task;
    }

    public IPriorityStrategy getPriorityStrategy() {
        return priorityStrategy;
    }

    @Override
    public String toString() {
        return "TaskPriorityDecorator{" +
                "task=" + this.task.toString() +
                ", priority=" + getPriorityStrategy() +
                '}';
    }
}
