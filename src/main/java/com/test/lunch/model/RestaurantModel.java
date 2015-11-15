package com.test.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * RestaurantModel model
 */
@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties({"menus","votes"})
public class RestaurantModel extends BasicModel<Long>
{
    private static final long serialVersionUID = -8279775271439516469L;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "restaurant")
    private Set<MenuModel> menus;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "restaurant")
    private Set<VoteModel> votes;

    public RestaurantModel()
    {
    }

    public RestaurantModel(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<MenuModel> getMenus()
    {
        return menus;
    }

    public void setMenus(Set<MenuModel> menus)
    {
        this.menus = menus;
    }

    public Set<VoteModel> getVotes()
    {
        return votes;
    }

    public void setVotes(Set<VoteModel> votes)
    {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantModel that = (RestaurantModel) o;

        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);

    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }
}
