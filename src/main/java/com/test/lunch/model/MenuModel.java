package com.test.lunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * RestaurantModel menu model
 */
@Entity
@Table(name = "menus")
public class MenuModel extends BasicModel<Long>
{
    private static final long serialVersionUID = -2277989616958911225L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "r_id")
    private RestaurantModel restaurant;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "menu_date")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private DishModel dish;

    @NotNull
   // todo @Min(0)
    private Double price;

    public MenuModel()
    {
    }

    public MenuModel(RestaurantModel restaurant, Date date, DishModel dish, Double price)
    {
        this.restaurant = restaurant;
        this.date = date;
        this.dish = dish;
        this.price = price;
    }

    public RestaurantModel getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant)
    {
        this.restaurant = restaurant;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public DishModel getDish()
    {
        return dish;
    }

    public void setDish(DishModel dish)
    {
        this.dish = dish;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuModel menu = (MenuModel) o;

        return !(getId() != null ? !getId().equals(menu.getId()) : menu.getId() != null);

    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }
}
