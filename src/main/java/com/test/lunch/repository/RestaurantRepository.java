package com.test.lunch.repository;

import com.test.lunch.model.RestaurantModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * RestaurantModel entities repository
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends CrudRepository<RestaurantModel, Long>
{
}
