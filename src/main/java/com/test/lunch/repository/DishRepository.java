package com.test.lunch.repository;

import com.test.lunch.model.DishModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DishModel entities repository
 */
@Transactional(readOnly = true)
public interface DishRepository extends CrudRepository<DishModel, Long>
{
}
