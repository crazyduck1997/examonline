package com.qf.examonline.service.impl;

import com.qf.examonline.dao.ScoreDao;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.Score;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ScoreServiceImpl implements ScoreService {

    @Autowired(required = false)
    private ScoreDao scoreDao;

    @Autowired(required = false)
    UserDao userDao;


    //进行判断 没有id时 执行添加 传入的又id执行修改
    @Override
    public void insert(Score score) {
        if (score.getSid()== null){
            scoreDao.insert(score);
        }else
            scoreDao.update(score);
    }

   //删除学生分数信息
    @Override
    public int deleteBySid(Integer sid) {

        return scoreDao.deleteBySid(sid);
    }

    //进行判断 没有id时 执行添加 传入的又id执行修改
    @Override
    public void update(Score score) {
        if (score.getSid()== null){
            scoreDao.insert(score);
        }else
        scoreDao.update(score);
   }

   //学生查看自己的额分数
    @Override
    public List<User> selectByUid(Integer uid) {
        return scoreDao.selectByUid(uid);

    }

    //老师查看自己学生的分数
    @Override
    public List<User> selectByTeacher(Integer paperId) {
        return scoreDao.selectByTeacher(paperId);
    }


}
