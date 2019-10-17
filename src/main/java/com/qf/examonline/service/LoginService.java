package com.qf.examonline.service;

import com.qf.examonline.entity.User;

import java.util.List;

public interface LoginService {
    User login(String username);

    int register(String username,String password);

    boolean checkUser(String username);

    List<String> findRolesByName(String userName);

    List<String> findPermsByName(String userName);
}
