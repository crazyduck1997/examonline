package com.qf.examonline.dao;

import com.qf.examonline.entity.UserAnswers;
import java.util.List;

public interface UserAnswersDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(UserAnswers record);

    UserAnswers selectByPrimaryKey(Integer cid);

    List<UserAnswers> selectAll();

    int updateByPrimaryKey(UserAnswers record);
}