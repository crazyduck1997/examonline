package com.qf.examonline.dao;

import com.qf.examonline.entity.User;
import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    User findById(Integer uid);
    //通过名字查询用户
    User findUserByName(String userName);
    //通过名字查询角色
    List<String> findRolesByName(String userName);
    //通过名字查询权限
    List<String> findPermsByName(String userName);
}