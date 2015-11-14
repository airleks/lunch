package com.test.lunch.controller;

import com.test.lunch.model.RestaurantModel;
import com.test.lunch.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Restaurants data administration rest api controller
 */
@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminApiRestaurantController extends AbstractCrudController<RestaurantModel,Long>
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<RestaurantModel> list()
    {
        return super.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestaurantModel get(@PathVariable Long id)
    {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestaurantModel> create(@RequestBody RestaurantModel restaurant)
    {
        return super.create(restaurant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RestaurantModel restaurant)
    {
        return super.update(id, restaurant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        return super.delete(id);
    }

    @Override
    public RestaurantRepository getRepository()
    {
        return restaurantRepository;
    }
}
