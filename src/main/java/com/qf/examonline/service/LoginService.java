package com.qf.examonline.service;

import com.qf.examonline.entity.User;

public interface LoginService {
    User login(String username);

    int register(String username,String password);

    boolean checkUser(String username);
}
