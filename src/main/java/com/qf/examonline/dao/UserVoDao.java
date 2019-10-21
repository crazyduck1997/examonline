package com.qf.examonline.dao;

import com.qf.examonline.entity.UserVo;

import java.util.List;

public interface UserVoDao {

    //学生查询学生的分数
    public List<UserVo> selectByUid(Integer uid);

    //查找所有学生的的成绩信息
    List<UserVo> selectAll();


    //老师查询学生的分数
     List<UserVo> selectByParentId(Integer parentId);

}
