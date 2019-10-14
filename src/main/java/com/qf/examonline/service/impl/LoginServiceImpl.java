package com.qf.examonline.service.impl;

import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.LoginService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired(required = false)
    UserDao userDao;

    @Override
    public User login(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public int register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String hex = new SimpleHash("md5", password,"haha", 1).toHex();
        user.setPassword(hex);
        return userDao.insert(user);
    }

    @Override
    public boolean checkUser(String username) {
        User user = userDao.selectByUsername(username);
        if (user == null){
            return true;
        }
        return false;
    }

}
