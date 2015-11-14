package com.test.lunch.model;

import javax.persistence.*;
import java.util.Date;

/**
 * UserModel vote model
 */
@Entity
@Table(name = "restaurant_votes")
public class VoteModel extends BasicModel<Long>
{
    private static final long serialVersionUID = 627973813224049854L;

    @ManyToOne
    @JoinColumn(name = "r_id")
    private RestaurantModel restaurant;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private UserModel user;

    @Temporal(TemporalType.DATE)
    @Column(name = "vote_date")
    private Date date;

    public VoteModel()
    {
    }

    public VoteModel(RestaurantModel restaurant, UserModel user, Date date)
    {
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
    }

    public RestaurantModel getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant)
    {
        this.restaurant = restaurant;
    }

    public UserModel getUser()
    {
        return user;
    }

    public void setUser(UserModel user)
    {
        this.user = user;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteModel vote = (VoteModel) o;

        return !(getId() != null ? !getId().equals(vote.getId()) : vote.getId() != null);

    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }
}
