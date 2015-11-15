package com.test.lunch.handler;

import com.test.lunch.data.ErrorData;
import com.test.lunch.exception.ResourceModificationException;
import com.test.lunch.exception.ResourceNotFoundException;
import com.test.lunch.exception.ResourcePathException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

/**
 * REST API resource operation exceptions handler
 */
@ControllerAdvice
public class CommonResourceExceptionsHandler
{
    @ExceptionHandler(ResourceModificationException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public @ResponseBody ErrorData handleResourcModificationException(ResourceModificationException e)
    {
        return new ErrorData(e.getMessage(), new Date());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorData handleResourceNotFoundException(ResourceNotFoundException e)
    {
        return new ErrorData(e.getMessage(), new Date());
    }

    @ExceptionHandler(ResourcePathException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorData handleResourcePathException(ResourcePathException e)
    {
        return new ErrorData(e.getMessage(), new Date());
    }

    // todo handle Bad Request
    // todo handle Unauthorized and Forbidden


}
