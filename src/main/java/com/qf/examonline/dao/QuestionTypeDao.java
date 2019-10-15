package com.qf.examonline.dao;

import com.qf.examonline.entity.QuestionType;
import java.util.List;

public interface QuestionTypeDao {
    int deleteByPrimaryKey(Integer qtId);

    int insert(QuestionType record);

    QuestionType selectByPrimaryKey(Integer qtId);

    List<QuestionType> selectAll();

    int updateByPrimaryKey(QuestionType record);
}