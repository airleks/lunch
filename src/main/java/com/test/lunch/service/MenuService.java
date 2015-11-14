package com.test.lunch.service;

import com.test.lunch.data.RestaurantMenuData;
import com.test.lunch.exception.ResourceNotFoundException;
import com.test.lunch.exception.ResourcePathException;
import com.test.lunch.model.DishModel;
import com.test.lunch.model.MenuModel;
import com.test.lunch.model.RestaurantModel;
import com.test.lunch.repository.DishRepository;
import com.test.lunch.repository.MenuRepository;
import com.test.lunch.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Restaurant menu view service.
 */
@Service
@Transactional(readOnly = true)
public class MenuService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Return menu data of specified restaurant at specified date
     *
     * @param restaurantId restaurant id
     * @param date date
     *
     * @return restaurant menu data
     *
     * @throws ResourceNotFoundException whe restaurant id is <tt>null</tt> or restaurant wasn't found.
     */
    public RestaurantMenuData getRestaurantMenu(Long restaurantId, Date date) throws ResourceNotFoundException
    {
        RestaurantModel restaurant;

        if ((restaurantId == null) || ((restaurant = restaurantRepository.findOne(restaurantId)) == null))
        {
            throw new ResourceNotFoundException(String.format("Restaurant [id=%s] not found", restaurantId));
        }

        List<MenuModel> menuOptions = menuRepository.findByRestaurantIdAndDate(restaurantId, date);

        RestaurantMenuData menu = new RestaurantMenuData();
        menu.setDate(date);
        menu.setRestaurantId(restaurantId);
        menu.setRestaurantName(restaurant.getName());
        menu.setOptions(menuOptions);

        return menu;
    }

    /**
     * Add dish to restaurant menu
     *
     * @param restaurantId restaurant id
     * @param date menu date
     * @param dishId dish id
     * @param price dish price
     *
     * @return <tt>true</tt> if menu entry was created, <tt>false</tt> otherwise
     *
     * @throws ResourcePathException when restaurant or dish weren't found
     */
    public boolean addDishToMenu(Long restaurantId, Date date, Long dishId, double price) throws ResourcePathException
    {
        RestaurantModel restaurant;
        DishModel dish;

        if ((restaurantId == null) || (restaurant = restaurantRepository.findOne(restaurantId)) == null)
        {
            throw new ResourcePathException(String.format("Restaurant [id=%s] not found", restaurantId));
        }

        if ((dishId == null) || (dish = dishRepository.findOne(dishId)) == null)
        {
            throw new ResourcePathException(String.format("Dish [id=%s] not found", dishId));
        }

        MenuModel menu = menuRepository.findByRestaurantAndDishAndDate(restaurant, dish, date);

        if (menu != null)
        {
            return false;
        }

        menu = new MenuModel(restaurant, date, dish, price);
        menuRepository.save(menu);

        return true;
    }

    /**
     * Remove dish from restaurant menu
     *
     * @param restaurantId restaurant id
     * @param date menu date
     * @param dishId dish is
     *
     * @return <tt>true</tt> if dish was removed. <tt>false</tt> otherwise
     */
    public boolean removeDishFromMenu(Long restaurantId, Date date, Long dishId)
    {
        return menuRepository.deleteByRestaurantIdAndDishIdAndDate(restaurantId, dishId, date) > 0;
    }

}
