package com.test.lunch.service;

import com.test.lunch.data.VoteResultEntryData;
import com.test.lunch.data.VoteResultsData;
import com.test.lunch.exception.ResourceModificationException;
import com.test.lunch.exception.ResourceNotFoundException;
import com.test.lunch.exception.ResourcePathException;
import com.test.lunch.model.RestaurantModel;
import com.test.lunch.model.UserModel;
import com.test.lunch.model.VoteModel;
import com.test.lunch.repository.RestaurantRepository;
import com.test.lunch.repository.UserRepository;
import com.test.lunch.repository.VoteRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * UserModel votes management service
 */
@Service
@Secured("ROLE_USER")
public class VoteService
{
    @Value("#{new java.text.SimpleDateFormat(\"HH:mm:ss\").parse(\"${check.time}\")}")
    private Date checkTime;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;


    /**
     * Returns current vote results at specified date.
     *
     * @param date date
     *
     * @return vote statistics data object
     *
     * @throws ResourceNotFoundException if no vote entries were found for specified date
     */
    @Transactional(readOnly = true)
    public VoteResultsData voteStats(Date date) throws ResourceNotFoundException
    {
        Collection<VoteResultEntryData> options = voteRepository.findVoteStatisticsByDate(date);
        VoteResultsData voteResultsData = new VoteResultsData();
        voteResultsData.setDate(date);
        voteResultsData.setOptions(options);

        return voteResultsData;
    }

    /**
     *
     * @param userName customer name
     * @param restaurantId id of restaurant customer voting for
     * @return vote entry
     *
     * @throws ResourceModificationException when customer is already voted today and change-vote time limit is exceeded
     * @throws ResourcePathException when restaurant id is <tt>null</tt> or restaurant wasn't found
     */
    @Transactional
    public VoteModel vote(String userName, Long restaurantId) throws ResourceModificationException, ResourcePathException
    {
        Date now = new Date();

        Date today = DateUtils.truncate(now, Calendar.DAY_OF_YEAR);
        Date votingTimeThreshold = new Date(today.getTime() + checkTime.getTime());

        VoteModel vote = voteRepository.findByUserLoginAndDate(userName, today);

        if ((vote != null) && now.after(votingTimeThreshold))
        {
            throw new ResourceModificationException(String.format("User '%s' already voted today", userName));
        }

        try
        {
            RestaurantModel restaurant = restaurantRepository.findOne(restaurantId);

            if (vote == null)
            {
                UserModel user = userRepository.findByLogin(userName);

                if (user == null)
                {
                    throw new ResourceModificationException(String.format("User '%s' not found", userName));
                }

                vote = new VoteModel(restaurant, user, today);
            }
            else
            {
                vote.setRestaurant(restaurant);
            }

            return voteRepository.save(vote);
        }
        catch (ResourceNotFoundException e)
        {
            throw new ResourcePathException(String.format("RestaurantModel [id=%s] not found", restaurantId), e);
        }
    }
}
