package org.example;

public class PriorityValidationException extends Exception {

    public PriorityValidationException(String message) {
        super(message);
    }

    public PriorityValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
