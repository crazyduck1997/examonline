package com.qf.examonline.dao;

import com.qf.examonline.entity.RoleUser;
import java.util.List;

public interface RoleUserDao {
    int deleteByPrimaryKey(Integer ruid);

    int insert(RoleUser record);

    RoleUser selectByPrimaryKey(Integer ruid);

    List<RoleUser> selectAll();

    int updateByPrimaryKey(RoleUser record);
}