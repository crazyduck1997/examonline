package com.qf.examonline.dao;

import com.qf.examonline.entity.SketchQuestions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SketchQuestionsDao {
    int deleteByPrimaryKey(Integer skeId);

    int insert(@Param("list") List<SketchQuestions> list);

    SketchQuestions selectByPrimaryKey(Integer skeId);

    List<SketchQuestions> selectAll();

    int updateByPrimaryKey(SketchQuestions record);
}