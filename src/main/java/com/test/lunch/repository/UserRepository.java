package com.test.lunch.repository;

import com.test.lunch.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserModel entities repository
 */
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<UserModel, Long>
{
    UserModel findByLogin(String login);
}

