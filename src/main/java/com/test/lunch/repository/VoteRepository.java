package com.test.lunch.repository;

import com.test.lunch.data.VoteResultEntryData;
import com.test.lunch.model.VoteModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * VoteModel entities repository
 */
@Transactional(readOnly = true)
public interface VoteRepository extends CrudRepository<VoteModel, Long>
{
    VoteModel findByUserLoginAndDate(String login, Date date);

    @Query("select new com.test.lunch.data.VoteResultEntryData(v.restaurant, count(v)) from com.test.lunch.model.VoteModel v where v.date = :date group by v.restaurant")
    Collection<VoteResultEntryData> findVoteStatisticsByDate(@Param("date") Date date);
}
