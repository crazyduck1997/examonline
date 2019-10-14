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

    User findUserByName(String userName);
}