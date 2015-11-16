package com.test.lunch.controller;

import com.google.common.collect.Lists;
import com.test.lunch.exception.ResourceNotFoundException;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * Default crud-controller implementation (more simple way is to use @RepositoryRestResource)
 */
public abstract class AbstractCrudController<T extends Persistable, ID extends Serializable>
{
    /**
     * Find all items of type T (w\o paging and ordering)
     *
     * @return list of items
     */
    public Collection<T> list()
    {
        return Lists.newArrayList(getRepository().findAll());
    }

    /**
     * Get item by id
     *
     * @param id id
     *
     * @return item
     *
     * @throws ResourceNotFoundException if no item found
     */
    public T get(ID id) throws ResourceNotFoundException
    {
        T entity = getRepository().findOne(id);

        if (entity == null)
        {
            throw new ResourceNotFoundException(String.format("Item [id=%s] not found", id));
        }

        return entity;
    }

    /**
     * Create or update item
     *
     * @param model item
     *
     * @return response entity
     */
    public ResponseEntity<T> create(T model)
    {
        HttpStatus status = model.isNew() ? HttpStatus.CREATED : HttpStatus.OK;

        T saved = getRepository().save(model);

        return new ResponseEntity<>(saved, status);
    }

    /**
     * Create or update item
     *
     * @param id item id
     * @param model item
     *
     * @return response entity
     */
    public ResponseEntity<?> update(ID id, T model)
    {
        if (!getRepository().exists(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        T saved = getRepository().save(model);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Delete item by id
     *
     * @param id item id
     *
     * @return response entity
     */
    public ResponseEntity<?> delete(ID id)
    {
        HttpStatus status = getRepository().exists(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        getRepository().delete(id);

        return new ResponseEntity<>(status);
    }

    /**
     * Get controller data repository
     *
     * @return data repository
     */
    public abstract CrudRepository<T,ID> getRepository();
}
