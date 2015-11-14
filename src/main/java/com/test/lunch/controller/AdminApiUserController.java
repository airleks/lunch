package com.test.lunch.controller;

import com.test.lunch.model.UserModel;
import com.test.lunch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * User administration rest api controller
 */
@RestController
@RequestMapping("/api/admin/users")
public class AdminApiUserController extends AbstractCrudController<UserModel,Long>
{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<UserModel> list()
    {
        return super.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserModel get(@PathVariable Long id)
    {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserModel> create(@RequestBody UserModel user)
    {
        return super.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserModel user)
    {
        return super.update(id, user);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        return super.delete(id);
    }

    @Override
    public UserRepository getRepository()
    {
        return userRepository;
    }
}
