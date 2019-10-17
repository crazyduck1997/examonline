package com.qf.examonline.dao;

import com.qf.examonline.entity.UserAnswers;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserAnswersDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(@Param("list") List<UserAnswers> record);

    UserAnswers selectByPrimaryKey(Integer cid);

    List<UserAnswers> selectAll();

    int updateByPrimaryKey(UserAnswers record);
}