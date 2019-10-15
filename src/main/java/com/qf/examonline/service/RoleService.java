package com.qf.examonline.service;

import com.qf.examonline.entity.Role;

import java.util.List;

public interface RoleService {

    int deleteByPrimaryKey(Integer rid);

    int insert(String rname);

    Role selectByPrimaryKey(Integer rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);


}
