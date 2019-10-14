package com.qf.examonline.dao;

import com.qf.examonline.entity.Role;
import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    Role selectByPrimaryKey(Integer rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}