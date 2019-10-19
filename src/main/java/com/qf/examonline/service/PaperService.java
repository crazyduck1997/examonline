package com.qf.examonline.service;

import com.github.pagehelper.PageInfo;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.QuestionVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PaperService {
    // 查询平均分
    public List<Paper> findAvgScore(Integer typeId);

    int updatePaper(Integer paperId);

    int insert(Paper record);

    Paper selectByPrimaryKey(Integer paperId);

    PageInfo<Paper> selectAll(Integer pageNum, Integer pageSize);

    int updateByPrimaryKey(Paper record);

    List<Paper> allPapers();

    List<QuestionVo> selectVo();


    //获取状态信息
     Map examStatus(Integer paperId);

     Map selectPaper(Integer paperId);



}
