package com.faforerof.rectangles.exceptions;

public class RectanglesApplicationException extends RuntimeException {

    public RectanglesApplicationException() {
        super();
    }

    public RectanglesApplicationException(String message) {
        super(message);
    }

    public RectanglesApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RectanglesApplicationException(Throwable cause) {
        super(cause);
    }

    protected RectanglesApplicationException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
