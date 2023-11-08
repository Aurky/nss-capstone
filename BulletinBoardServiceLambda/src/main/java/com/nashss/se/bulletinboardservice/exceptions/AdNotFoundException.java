package com.nashss.se.bulletinboardservice.exceptions;


/**
 * Exception to throw when a given ad ID is not found in the database.
 */
public class AdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8435082682906708254L;

    /**
     * Exception with no message or cause.
     */
    public AdNotFoundException() {
        super();
    }

    /**
     * Exception with a message but no cause.
     * @param message A descriptive message for this exception.
     */
    public AdNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public AdNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and a cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public AdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
