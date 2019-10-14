package com.qf.examonline.dao;

import com.qf.examonline.entity.Admin;
import java.util.List;

public interface AdminDao {
    int deleteByPrimaryKey(Integer aId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer aId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}