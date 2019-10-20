package com.qf.examonline.utils;

import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工具类用于获取当前用户
 */
@Component
public class UserUtil {

    private static UserDao userDao;

    @Autowired
    public UserUtil(UserDao dao) {
        userDao =dao;
    }

    public static User getUser(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User user = userDao.findUserByName(username);
        return user;
    }

}
