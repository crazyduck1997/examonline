package com.qf.examonline.dao;

import com.qf.examonline.entity.Examinee;
import com.qf.examonline.entity.QuestionVo;

import java.util.Date;
import java.util.List;

public interface ExamineeDao {
    int deleteByPrimaryKey(Integer examineeId);

    int insert(Examinee record);

    Examinee selectByPrimaryKey(Integer examineeId);

    List<Examinee> selectAll();

    int updateByPrimaryKey(Examinee record);

    List<QuestionVo> selectByVo( Date start,Date end, String idcard, Integer examTypeId);
}