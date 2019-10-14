package com.qf.examonline.dao;

import com.qf.examonline.entity.BooleanQuestions;
import java.util.List;

public interface BooleanQuestionsDao {
    int deleteByPrimaryKey(Integer booId);

    int insert(BooleanQuestions record);

    BooleanQuestions selectByPrimaryKey(Integer booId);

    List<BooleanQuestions> selectAll();

    int updateByPrimaryKey(BooleanQuestions record);
}