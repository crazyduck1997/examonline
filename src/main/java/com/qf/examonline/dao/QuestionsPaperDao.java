package com.qf.examonline.dao;

import com.qf.examonline.entity.QuestionsPaper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionsPaperDao {
    int deleteByPrimaryKey(Integer qpId);

    int insert(QuestionsPaper record);

    QuestionsPaper selectByPrimaryKey(Integer qpId);

    List<QuestionsPaper> selectAll();

    int updateByPrimaryKey(QuestionsPaper record);

    int addQuestions(@Param("paperId") Integer paperId,@Param("list") List<Integer> list,@Param("questionTypeId") Integer questionTypeId);

    List<QuestionsPaper> selectByPaperId(Integer paperId);
}