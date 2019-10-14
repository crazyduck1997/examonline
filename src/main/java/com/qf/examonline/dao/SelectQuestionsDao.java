package com.qf.examonline.dao;

import com.qf.examonline.entity.SelectQuestions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectQuestionsDao {
    int deleteByPrimaryKey(Integer sqId);

    int insert(@Param("list") List<SelectQuestions> record);

    SelectQuestions selectByPrimaryKey(Integer sqId);

    List<SelectQuestions> selectAll();

    int updateByPrimaryKey(SelectQuestions record);
}