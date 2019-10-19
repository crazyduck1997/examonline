package com.qf.examonline.dao;

import com.qf.examonline.entity.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll(String username);

    int updateByPrimaryKey(User record);

    User findById(Integer uid);
    //通过名字查询用户
    User findUserByName(String userName);
    //通过名字查询角色
    List<String> findRolesByName(String userName);
    //通过名字查询权限
    List<String> findPermsByName(String userName);

    //查询总数量
    Integer findCount(String username);
    // 管理员最高权限重置密码
    void resetPassword(User user);

    //修改密码
    public String updatePassword(String password);
}