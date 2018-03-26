package com.andriiP.carSaver.services;

import com.andriiP.carSaver.dao.User;

public interface UserService {

    User getUser(String userName);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(User user);
}
