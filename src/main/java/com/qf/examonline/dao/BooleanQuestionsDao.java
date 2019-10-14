package com.qf.examonline.dao;

import com.qf.examonline.entity.BooleanQuestions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooleanQuestionsDao {
    int deleteByPrimaryKey(Integer booId);

    int insert(@Param("list") List<BooleanQuestions> list);

    BooleanQuestions selectByPrimaryKey(Integer booId);

    List<BooleanQuestions> selectAll();

    int updateByPrimaryKey(BooleanQuestions record);
}