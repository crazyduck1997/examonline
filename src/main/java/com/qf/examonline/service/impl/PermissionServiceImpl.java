package com.qf.examonline.service.impl;

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
    public List<Permission> findMenuByName(String username) {
        return permissionDao.findMenuByName(username);
    }
}
