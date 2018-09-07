package com.fpt.services.user.impl;

import com.fpt.entity.User;
import com.fpt.repositories.user.UserRepository;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createAccount(User user){
        return userRepository.save(user);
    }
}
