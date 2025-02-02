package org.example;

public class TaskValidationException extends RuntimeException {

    public TaskValidationException(final String message) {
        super(message);
    }

    public TaskValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
