package com.qf.examonline.dao;

import com.qf.examonline.entity.Student;
import java.util.List;

public interface StudentDao {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    Student selectByPrimaryKey(Integer stuId);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    Student selectByUsername(String username);
}