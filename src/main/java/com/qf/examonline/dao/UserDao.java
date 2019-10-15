package com.qf.examonline.dao;

import com.qf.examonline.entity.User;
import java.util.List;

public interface UserDao {

    User selectByPrimaryKey(Integer uid);

    User selectByUsername(String username);

    List<User> selectAll(String username);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer uid);

    User findById(Integer uid);

    //通过名字查询
    User findUserByName(String userName);

    //查询总数
    Integer findCount(String username);
}