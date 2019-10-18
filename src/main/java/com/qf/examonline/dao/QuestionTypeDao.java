package com.qf.examonline.dao;

import com.qf.examonline.entity.QuestionType;
import com.qf.examonline.entity.QuestionVo;

import java.util.List;
import java.util.Map;

public interface QuestionTypeDao {
    int deleteByPrimaryKey(Integer qtId);

    int insert(QuestionType record);

    QuestionType selectByPrimaryKey(Integer qtId);

    List<QuestionType> selectAll();

    int updateByPrimaryKey(QuestionType record);

    //试题管理
    List<QuestionVo> findAllQuertions();
}