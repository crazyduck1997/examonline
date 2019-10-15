package com.qf.examonline.dao;

import com.qf.examonline.entity.Score;
import com.qf.examonline.entity.User;

import java.util.List;

public interface ScoreDao {

    //删除学生分数信息
    int deleteBySid(Integer sid);

    //添加学生分数信息
    void insert(Score score);

    //修改学生分数信息
    void update(Score score);

    //学生查询学生的分数
    public List<User> selectByUid(Integer uid);

    //老师查询学生的分数
    public List<User> selectByTeacher(Integer paperId);

    Score selectByPrimaryKey(Integer sid);

    List<Score> selectAll();


}