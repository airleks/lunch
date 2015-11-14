package com.test.lunch.data;

import com.test.lunch.model.MenuModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Restaurant menu data
 */
public class RestaurantMenuData implements Serializable
{
    private static final long serialVersionUID = 8898020514412403031L;

    private Date date;

    private Long restaurantId;
    private String restaurantName;

    private Collection<MenuModel> options;

    public RestaurantMenuData()
    {
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Long getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId)
    {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public Collection<MenuModel> getOptions()
    {
        return options;
    }

    public void setOptions(Collection<MenuModel> options)
    {
        this.options = options;
    }
}
