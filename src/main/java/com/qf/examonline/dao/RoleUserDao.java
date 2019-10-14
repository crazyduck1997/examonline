package com.qf.examonline.dao;

import com.qf.examonline.entity.RoleUser;
import java.util.List;

public interface RoleUserDao {
    int deleteByPrimaryKey(Integer urid);

    int insert(RoleUser record);

    RoleUser selectByPrimaryKey(Integer urid);

    List<RoleUser> selectAll();

    int updateByPrimaryKey(RoleUser record);
}