package com.qf.examonline.dao;

import com.qf.examonline.entity.Paper;
import java.util.List;

public interface PaperDao {
    int deleteByPrimaryKey(Integer paperId);

    int insert(Paper record);

    Paper selectByPrimaryKey(Integer paperId);

    List<Paper> selectAll();

    int updateByPrimaryKey(Paper record);

    // 查询平均分
    public List<Paper> findAvgScore(Integer typeId);
}