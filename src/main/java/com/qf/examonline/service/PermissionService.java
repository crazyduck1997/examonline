package com.qf.examonline.service;

import com.qf.examonline.entity.Permission;

import java.util.List;

public interface PermissionService {
    int deleteByPrimaryKey(Integer pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> findMenuByName(String username);

    List<Permission> list(String panme, Integer page, Integer limit);
}
