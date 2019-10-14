package com.qf.examonline.service.impl;

import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String username) {
        User user = userDao.selectByUsername(username);
        return user;
    }
}
