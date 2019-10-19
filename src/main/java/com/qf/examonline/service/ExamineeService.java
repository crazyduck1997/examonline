package com.qf.examonline.service;

import com.qf.examonline.entity.Examinee;
import com.qf.examonline.entity.QuestionVo;

import java.util.Date;
import java.util.List;

public interface ExamineeService {
    int deleteByPrimaryKey(Integer examineeId);

    int insert(Examinee record);

    Examinee selectByPrimaryKey(Integer examineeId);

    List<Examinee> selectAll();

    int updateByPrimaryKey(Examinee record);

    List<QuestionVo> selectByVo(Date start, Date end, String idcard, Integer examTypeId);
}
