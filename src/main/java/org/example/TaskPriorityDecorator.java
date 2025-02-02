package org.example;

public class TaskPriorityDecorator {
    private final Task task;
    private final IPriorityStrategy priorityStrategy;

    public TaskPriorityDecorator(Task task, IPriorityStrategy priorityStrategy) {
        this.task = task;
        this.priorityStrategy = priorityStrategy;
    }

    public Task getTask() {
        return task;
    }

    public IPriorityStrategy getPriorityStrategy() {
        return priorityStrategy;
    }
}
