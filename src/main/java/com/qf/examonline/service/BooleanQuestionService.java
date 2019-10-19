package com.qf.examonline.service;

import com.qf.examonline.entity.BooleanQuestions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BooleanQuestionService {


    public int insertBooleanQuestions(MultipartFile file);

    int addBooleanQuestion(List<BooleanQuestions> list);

    int insertBoolean(BooleanQuestions booleanQuestions);
}
