package com.test.lunch.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Common entity fields
 */
@MappedSuperclass
public abstract class BasicModel<PK extends Serializable> extends AbstractPersistable<PK>
{
    @Column(name = "created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    @PrePersist
    protected void onCreate()
    {
        created = updated = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updated = new Date();
    }
}
