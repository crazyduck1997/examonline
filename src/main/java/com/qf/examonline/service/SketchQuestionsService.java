package com.qf.examonline.service;

import org.springframework.web.multipart.MultipartFile;

public interface SketchQuestionsService {

    public int insertSketchQuestions(MultipartFile file);
}
