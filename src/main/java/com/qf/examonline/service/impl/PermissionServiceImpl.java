package com.qf.examonline.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examonline.dao.PermissionDao;
import com.qf.examonline.entity.Permission;
import com.qf.examonline.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    PermissionDao permissionDao;

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return permissionDao.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(Permission record) {
        return permissionDao.insert(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer pid) {
        return permissionDao.selectByPrimaryKey(pid);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Permission> findMenuByName(String username) {
        return permissionDao.findMenuByName(username);
    }

    @Override
    public List<Permission> list(String pname, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return permissionDao.list(pname);
    }
}
