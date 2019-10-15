package com.qf.examonline.service;

import com.qf.examonline.entity.SketchQuestions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SketchQuestionsService {

    public int insertSketchQuestions(MultipartFile file);

    int addSketchQuestion(List<SketchQuestions> list);
}
