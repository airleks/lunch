package com.test.lunch.controller;

import com.test.lunch.data.RestaurantMenuData;
import com.test.lunch.data.VoteResultsData;
import com.test.lunch.model.VoteModel;
import com.test.lunch.repository.RestaurantRepository;
import com.test.lunch.service.MenuService;
import com.test.lunch.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;

/**
 * Customer actions rest controller
 */
@RestController
@RequestMapping("/api/restaurants")
public class CustomerApiController
{
    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(method = RequestMethod.GET)
    public VoteResultsData list()
    {
        return voteService.voteStats(new Date());
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
    public RestaurantMenuData get(@PathVariable("restaurantId") Long restaurantId)
    {
        return menuService.getRestaurantMenu(restaurantId, new Date());
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT)
    public VoteModel create(@PathVariable("restaurantId") Long restaurantId, Principal principal)
    {
        return voteService.vote(principal.getName(), restaurantId);
    }
}
