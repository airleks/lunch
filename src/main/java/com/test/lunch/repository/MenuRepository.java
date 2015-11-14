package com.test.lunch.repository;

import com.test.lunch.model.DishModel;
import com.test.lunch.model.MenuModel;
import com.test.lunch.model.RestaurantModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * MenuModel entities repository
 */
@Transactional(readOnly = true)
public interface MenuRepository extends CrudRepository<MenuModel, Long>
{
    List<MenuModel> findByRestaurantIdAndDate(Long restaurantId, Date date);

    MenuModel findByRestaurantAndDishAndDate(RestaurantModel restaurant, DishModel dish, Date date);

    Long deleteByRestaurantIdAndDishIdAndDate(Long restaurantId, Long dishId, Date date);
}
