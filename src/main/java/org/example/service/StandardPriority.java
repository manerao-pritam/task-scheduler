package org.example.service;

import org.example.exception.PriorityValidationException;

public class StandardPriority implements IPriorityStrategy {

    private final Priority priority;

    // public so it can be referenced from outside
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }

    /**
     * Constructs a StandardPriority with the specified Priority.
     *
     * @param priority the priority level to set; must not be null.
     * @throws PriorityValidationException if the provided priority is null.
     */
    public StandardPriority(final Priority priority) throws PriorityValidationException {
        this.priority = priority;
    }

    @Override
    public String getPriority() {
        return this.priority.name();
    }
}
