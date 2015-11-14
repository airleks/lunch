package com.test.lunch.controller;

import com.test.lunch.model.DishModel;
import com.test.lunch.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Dishes data administration rest api controller
 */
@RestController
@RequestMapping("/api/admin/dishes")
public class AdminApiDishController extends AbstractCrudController<DishModel,Long>
{
    @Autowired
    private DishRepository dishRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<DishModel> list()
    {
        return super.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DishModel get(@PathVariable("id") Long id)
    {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DishModel> create(@RequestBody DishModel dish)
    {
        return super.create(dish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DishModel dish)
    {
        return super.update(id, dish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        return super.delete(id);
    }

    @Override
    public DishRepository getRepository()
    {
        return dishRepository;
    }
}
