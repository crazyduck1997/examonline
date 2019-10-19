package com.qf.examonline.service;


import com.qf.examonline.entity.SelectQuestions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SelectQuestionsService {

    int insertQuestions(MultipartFile file);

    int addSelectQuestions(List<SelectQuestions> list);

    int insertSelect(SelectQuestions selectQuestions);

    //修改选择题试题
    int updateSelect(SelectQuestions selectQuestions);

    //id查询
    SelectQuestions findById(Integer sqId);

}
