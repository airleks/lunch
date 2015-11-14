package com.test.lunch.data;

import com.test.lunch.model.RestaurantModel;

import java.io.Serializable;

/**
 * Data object representing vote result for one restaurant
 */
public class VoteResultEntryData implements Serializable
{
    private static final long serialVersionUID = -4715274213265509603L;

    private RestaurantModel restaurant;
    private long votes;

    public VoteResultEntryData()
    {
    }

    public VoteResultEntryData(RestaurantModel restaurant, long votes)
    {
        this.restaurant = restaurant;
        this.votes = votes;
    }

    public RestaurantModel getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant)
    {
        this.restaurant = restaurant;
    }

    public long getVotes()
    {
        return votes;
    }

    public void setVotes(long votes)
    {
        this.votes = votes;
    }
}
