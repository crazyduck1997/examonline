package com.qf.examonline.dao;

import com.qf.examonline.entity.Score;
import java.util.List;

public interface ScoreDao {
    int deleteByPrimaryKey(Integer sid);

    int insert(Score record);

    Score selectByPrimaryKey(Integer sid);

    List<Score> selectAll();

    int updateByPrimaryKey(Score record);
}