package com.qf.examonline.dao;

import com.qf.examonline.entity.Teacher;
import java.util.List;

public interface TeacherDao {
    int deleteByPrimaryKey(Integer teaId);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer teaId);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);
}