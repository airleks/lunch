package com.test.lunch.controller;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * Default crud-controller implementation (more simpler way - to use @RepositoryRestResource)
 */
public abstract class AbstractCrudController<T extends Persistable, ID extends Serializable>
{
    /**
     * Find all items of type T (w\o paging and ordering)
     *
     * @return
     */
    public Collection<T> list()
    {
        return Lists.newArrayList(getRepository().findAll());
    }

    public T get(ID id)
    {
        return getRepository().findOne(id);
    }

    public ResponseEntity<T> create(T model)
    {
        HttpStatus status = model.isNew() ? HttpStatus.CREATED : HttpStatus.OK;

        T saved = getRepository().save(model);

        return new ResponseEntity<>(saved, status);
    }

    public ResponseEntity<?> update(ID id, T model)
    {
        if (!getRepository().exists(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        T saved = getRepository().save(model);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    public ResponseEntity<?> delete(ID id)
    {
        HttpStatus status = getRepository().exists(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        getRepository().delete(id);

        return new ResponseEntity<>(status);
    }

    public abstract <S extends CrudRepository<T,ID>> S getRepository();
}
