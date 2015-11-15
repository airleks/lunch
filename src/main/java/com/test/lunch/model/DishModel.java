package com.test.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * RestaurantModel dish model
 */
@Entity
@Table(name = "dishes")
@JsonIgnoreProperties("menus")
public class DishModel extends BasicModel<Long>
{
    private static final long serialVersionUID = -6954829902837954999L;

    @NotEmpty
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dish")
    private Set<MenuModel> menus;

    public DishModel()
    {
    }

    public DishModel(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Set<MenuModel> getMenus()
    {
        return menus;
    }

    public void setMenus(Set<MenuModel> menus)
    {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishModel dish = (DishModel) o;

        return !(getId() != null ? !getId().equals(dish.getId()) : dish.getId() != null);
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }
}
