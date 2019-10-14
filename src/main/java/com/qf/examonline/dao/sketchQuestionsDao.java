package com.qf.examonline.dao;

import com.qf.examonline.entity.sketchQuestions;
import java.util.List;

public interface sketchQuestionsDao {
    int deleteByPrimaryKey(Integer skeId);

    int insert(sketchQuestions record);

    sketchQuestions selectByPrimaryKey(Integer skeId);

    List<sketchQuestions> selectAll();

    int updateByPrimaryKey(sketchQuestions record);
}