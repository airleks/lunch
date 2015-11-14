package com.test.lunch.exception;

/**
 * Custom exception to represent entity wrong-path issue on front-end.
 */
public class ResourcePathException extends RuntimeException
{
    public ResourcePathException()
    {
    }

    public ResourcePathException(String message)
    {
        super(message);
    }

    public ResourcePathException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
