package com.qf.examonline.dao;

import com.qf.examonline.entity.PermRole;
import java.util.List;

public interface PermRoleDao {
    int deleteByPrimaryKey(Integer rpid);

    int insert(PermRole record);

    PermRole selectByPrimaryKey(Integer rpid);

    List<PermRole> selectAll();

    int updateByPrimaryKey(PermRole record);
}