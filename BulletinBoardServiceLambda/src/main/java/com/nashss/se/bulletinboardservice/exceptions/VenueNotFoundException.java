package com.nashss.se.bulletinboardservice.exceptions;

/**
 * Exception to throw when a given venue ID is not found in the database.
 */

public class VenueNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6647140539124259682L;

    /**
     * Exception with no message or cause.
     */
    public VenueNotFoundException() {
        super();
    }

    /**
     * Exception with a message but no cause.
     * @param message A descriptive message for this exception.
     */
    public VenueNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public VenueNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with a message and a cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public VenueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
