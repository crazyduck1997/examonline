package com.qf.examonline.service.impl;

import com.qf.examonline.dao.PaperDao;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired(required = false)
    private PaperDao paperDao;
    @Override
    public List<Paper> findAvgScore(Integer typeId) {
        List<Paper> avgScoreList = paperDao.findAvgScore(typeId);
        return avgScoreList;
    }
}
