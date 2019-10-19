package com.qf.examonline.service;

import com.qf.examonline.entity.QuestionType;
import com.qf.examonline.entity.QuestionVo;

import java.util.List;

public interface QuestionTypeService {

    List<QuestionType> selectAll();

    //试题管理
    List<QuestionVo> findAllQuertions();

}
