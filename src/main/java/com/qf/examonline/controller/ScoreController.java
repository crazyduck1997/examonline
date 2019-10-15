package com.qf.examonline.controller;

import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Score;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "分数管理")
@CrossOrigin
@RestController
@RequestMapping("/scoreController")
public class ScoreController  {

    @Autowired
    public ScoreService scoreService;

    @ApiOperation("添加新分数信息")
    @PostMapping("/insert.do")
    public JsonBean insert(Score score){
         scoreService.insert(score);
         return new JsonBean(0,"添加成功");
    }

    @ApiOperation("删除分数信息")
    @PostMapping("/deleteBySid.do")
    public JsonBean deleteBySid(Integer sid){
        int i = scoreService.deleteBySid(sid);
        return new JsonBean(0,"删除成功");
    }

    @ApiOperation("修改分数信息")
    @PostMapping("/update.do")
    public JsonBean update(@RequestBody Score score){
        scoreService.update(score);
        return new JsonBean(0,"修改成功");
    }

    @ApiOperation("学生查询分数信息")
    @PostMapping("/selectByUid.do")
    public JsonBean selectByUid(Integer uid){
        List<User> users = scoreService.selectByUid(uid);
        return new JsonBean(0,users);
    }

    @ApiOperation("老师查询学生分数信息")
    @PostMapping("/selectByTeacher.do")
    public JsonBean selectByTeacher(Integer paperId){
        List<User> users = scoreService.selectByTeacher(paperId);
        return  new JsonBean(0,users);
    }
}
