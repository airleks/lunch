package com.test.lunch.controller;

import com.test.lunch.data.RestaurantMenuData;
import com.test.lunch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Menus data administration rest api controller
 */
@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminApiMenuController
{
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/{restaurantId}/{date}", method = RequestMethod.GET)
    public RestaurantMenuData list(@PathVariable("restaurantId") Long restaurantId,
                          @PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date)
    {
        return menuService.getRestaurantMenu(restaurantId, date); // todo check response status
    }

    @RequestMapping(value = "/{restaurantId}/{date}/{dishId}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,
                                    @PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date,
                                    @PathVariable("dishId") Long dishId,
                                    @RequestParam("price") Double price)
    {
        // we allow only creation of menu items to prevent price modification tricks during voting period
        HttpStatus status = menuService.addDishToMenu(restaurantId, date, dishId, price) ? HttpStatus.CREATED : HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(status);
    }

    @RequestMapping(value = "/{restaurantId}/{date}/{dishId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("restaurantId") Long restaurantId,
                                    @PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date,
                                    @PathVariable("dishId") Long dishId)
    {
        return menuService.removeDishFromMenu(restaurantId, date, dishId) ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
