package com.qf.examonline.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface QuestionPaperService {

    int addQuestions(Map<String, Object> map);

    List findQuestions(Integer typeId, Integer questionType);

    void insertAutoMakePaper(Integer selectNum, Integer booleanNum, Integer sketchNum, Integer typeId, String paperName, Date beginTime,Date endTime);

}
