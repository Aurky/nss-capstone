package com.nashss.se.bulletinboardservice.exceptions;

/**
 * Exception to throw when a given location ID is not found in the database.
 */

public class LocationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5488297912747679026L;

    /**
     * Exception with no message or cause.
     */
    public LocationNotFoundException() {
        super();
    }

    /**
     * Exception with a message but no cause.
     * @param message A descriptive message for this exception.
     */
    public LocationNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public LocationNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and a cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
