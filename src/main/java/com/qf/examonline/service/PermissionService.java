package com.qf.examonline.service;

import com.qf.examonline.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findMenuByName(String username);
}
