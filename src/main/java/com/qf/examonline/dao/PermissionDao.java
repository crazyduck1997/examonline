package com.qf.examonline.dao;

import com.qf.examonline.entity.Permission;

import java.util.List;

public interface PermissionDao {
    int deleteByPrimaryKey(Integer pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
    //通过名字查询菜单
    List<Permission> findMenuByName(String userName);
}