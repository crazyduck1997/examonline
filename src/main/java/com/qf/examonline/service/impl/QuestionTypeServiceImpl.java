package com.qf.examonline.service.impl;

import com.qf.examonline.dao.QuestionTypeDao;
import com.qf.examonline.entity.QuestionType;
import com.qf.examonline.entity.QuestionVo;
import com.qf.examonline.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    @Autowired(required = false)
    QuestionTypeDao questionTypeDao;

    @Override
    public List<QuestionType> selectAll() {
        List<QuestionType> list = questionTypeDao.selectAll();
        return list;
    }

    @Override
    public List<QuestionVo> findAllQuertions() {
        List<QuestionVo> allQuertions = questionTypeDao.findAllQuertions();
        return allQuertions;
    }
}
