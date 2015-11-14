package com.test.lunch.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Error report data object
 */
public class ErrorData implements Serializable
{
    private static final long serialVersionUID = -5705480359550160401L;

    private String message;
    private Date created;

    public ErrorData()
    {
    }

    public ErrorData(String message, Date created)
    {
        this.message = message;
        this.created = created;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
