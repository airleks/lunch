package com.test.lunch.exception;

/**
 * Custom exception to represent entity modification issue on front-end.
 */
public class ResourceModificationException extends RuntimeException
{
    public ResourceModificationException()
    {
    }

    public ResourceModificationException(String message)
    {
        super(message);
    }

    public ResourceModificationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
