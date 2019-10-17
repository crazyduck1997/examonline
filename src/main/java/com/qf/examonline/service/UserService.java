package com.qf.examonline.service;

import com.github.pagehelper.PageInfo;
import com.qf.examonline.entity.User;

import java.util.List;

public interface UserService {
    User login(String username);

    //查询用户
    public List<User> selectAll(String username, Integer page, Integer limit);

    //添加用户
    public void insert(User record);

    //根据id查询
    public User findById(Integer uid);

    //修改用户
    public void updateByPrimaryKey(User record);

    //删除用
    public void deleteByPrimaryKey(Integer uid);

    //查询总数
    public Integer fingCount(String username);
}
