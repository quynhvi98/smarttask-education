package com.fpt.services.user.impl;

import com.fpt.entity.User;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User createAccount(User user){
        return userDao.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }
}
