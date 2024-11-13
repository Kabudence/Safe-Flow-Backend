package com.safeflow.exception;


/**

 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new ValidationException with no detail message.
     */
    public ValidationException() {
        super();
    }

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ValidationException(String message) {
        super(message);
    }
}
