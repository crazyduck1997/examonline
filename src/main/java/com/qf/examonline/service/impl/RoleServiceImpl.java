package com.qf.examonline.service.impl;

import com.qf.examonline.dao.RoleDao;
import com.qf.examonline.entity.Role;
import com.qf.examonline.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    private RoleDao roleDao;
    @Override
    public int deleteByPrimaryKey(Integer rid) {
        int i = roleDao.deleteByPrimaryKey(rid);
        return i;
    }

    @Override
    public int insert(String rname) {

        Role role = roleDao.selectRoleByName(rname);
        if (role != null){
            throw new RuntimeException("名字重复");
        }
        int i = roleDao.insert(rname);
        return i;
    }

    @Override
    public Role selectByPrimaryKey(Integer rid) {
        Role role = roleDao.selectByPrimaryKey(rid);
        return role;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleDao.selectAll();
        return roles;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        Role role = roleDao.selectRoleByName(record.getRname());
        if (role != null){
            throw new RuntimeException("名字重复");
        }
        int i = roleDao.updateByPrimaryKey(record);
        return i;
    }

}
