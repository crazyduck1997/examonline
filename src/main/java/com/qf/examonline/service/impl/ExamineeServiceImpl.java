package com.qf.examonline.service.impl;

import com.qf.examonline.dao.ExamineeDao;
import com.qf.examonline.entity.Examinee;
import com.qf.examonline.entity.QuestionVo;
import com.qf.examonline.service.ExamineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamineeServiceImpl implements ExamineeService {

    @Autowired(required = false)
    ExamineeDao examineeDao;

    @Override
    public int deleteByPrimaryKey(Integer examineeId) {
        return 0;
    }

    @Override
    public int insert(Examinee examinee) {
        examinee.setEnrollTime(new Date());
        return examineeDao.insert(examinee);
    }

    @Override
    public Examinee selectByPrimaryKey(Integer examineeId) {
        return null;
    }

    @Override
    public List<Examinee> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Examinee record) {
        return 0;
    }

    @Override
    public List<QuestionVo> selectByVo(Date start, Date end, String idcard, Integer examTypeId) {
        return examineeDao.selectByVo(start,end,idcard,examTypeId);
    }
}
