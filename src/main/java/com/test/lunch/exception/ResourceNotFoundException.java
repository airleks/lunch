package com.test.lunch.exception;

/**
 * Custom exception to represent entity not found issue on front-end.
 */
public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException()
    {
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
