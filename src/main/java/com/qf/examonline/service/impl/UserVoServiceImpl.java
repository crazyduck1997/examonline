package com.qf.examonline.service.impl;

import com.qf.examonline.dao.UserVoDao;
import com.qf.examonline.entity.UserVo;
import com.qf.examonline.service.UserVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVoServiceImpl implements UserVoService {


    @Autowired(required = false)
    private UserVoDao userVoDao;


    //学生查看自己的分数
    @Override
    public List<UserVo> selectByUid(Integer uid) {
        return userVoDao.selectByUid(uid);

    }

//展示所有学生的成绩
    @Override
    public List<UserVo> selectAll() {
        return userVoDao.selectAll();
    }


    //老师查询学生的成绩
    @Override
    public List<UserVo> selectByParentId(Integer parentId) {
        return userVoDao.selectByParentId(parentId);
    }


}
