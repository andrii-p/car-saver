package com.andriiP.carSaver.services.impl;

import com.andriiP.carSaver.dao.User;
import com.andriiP.carSaver.dao.UserRepo;
import com.andriiP.carSaver.exceptions.ItemNotFoundException;
import com.andriiP.carSaver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUser(String userName) {
        User user = this.userRepo.findByUserName(userName);

        if (user == null) {
            throw new ItemNotFoundException(String.format("Username %s hasn't been found", userName));
        }

        return user;
    }

    @Override
    public User saveUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userRepo.delete(user);
    }
}
