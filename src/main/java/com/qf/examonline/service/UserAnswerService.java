package com.qf.examonline.service;

import com.qf.examonline.entity.UserAnswers;

import java.util.List;


public interface UserAnswerService {

    void commitPaper(List<UserAnswers> list);
}

