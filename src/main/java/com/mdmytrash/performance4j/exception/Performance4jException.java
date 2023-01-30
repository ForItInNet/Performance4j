package com.mdmytrash.performance4j.exception;

public class Performance4jException extends RuntimeException {

    public static final String TIMESTAMP_NOT_FOUNT = "Timestamp did not found";
    public static final String ALREADY_STOPPED = "Timer has already stopped";
    public static final String CAN_NOT_CREATE_PROXY = "Can not create a proxy";

    public Performance4jException(String message) {

        super(message);
    }

    public Performance4jException(String message, Throwable cause) {

        super(message, cause);
    }

    public Performance4jException(Throwable cause) {

        super(cause);
    }
}
