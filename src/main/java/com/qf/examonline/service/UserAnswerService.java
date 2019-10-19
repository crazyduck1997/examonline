package com.qf.examonline.service;

import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.UserAnswers;

import java.util.List;


public interface UserAnswerService {

    void commitPaper(List<UserAnswers> list);

    void commitSelect(Integer questionId,String answer,Integer questionType);


}

