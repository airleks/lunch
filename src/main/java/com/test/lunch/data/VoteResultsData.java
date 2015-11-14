package com.test.lunch.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Data object representing vote results summary
 */
public class VoteResultsData implements Serializable
{
    private static final long serialVersionUID = 765716510860830426L;

    private Date date;
    private Collection<VoteResultEntryData> options;

    public VoteResultsData()
    {
    }

    public VoteResultsData(Date date, Collection<VoteResultEntryData> options)
    {
        this.date = date;
        this.options = options;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Collection<VoteResultEntryData> getOptions()
    {
        return options;
    }

    public void setOptions(Collection<VoteResultEntryData> options)
    {
        this.options = options;
    }
}
