package com.qf.examonline.service;

import com.qf.examonline.entity.Paper;

import java.util.List;

public interface PaperService {
    // 查询平均分
    public List<Paper> findAvgScore(Integer typeId);
}
