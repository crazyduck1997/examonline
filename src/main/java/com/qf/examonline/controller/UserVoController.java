package com.qf.examonline.controller;


import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.UserVo;
import com.qf.examonline.service.UserVoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "分数管理")
@RestController
@RequestMapping("/uservo")
public class UserVoController {

    @Autowired
    private UserVoService userVoService;

    @ApiOperation("学生查询分数信息")
    @GetMapping("/selectByUid.do")
    public JsonBean selectByUid(Integer uid){
        List<UserVo> userVos = userVoService.selectByUid(uid);
        System.out.println(uid);
        System.out.println(userVos);
        return new JsonBean(0,userVos);
    }

    @ApiOperation("查询所有的学生分数信息")
    @GetMapping("/selectAll.do")
    public JsonBean selectAll(){
        List<UserVo> userVos = userVoService.selectAll();
        return new JsonBean(0,userVos);
    }



    @ApiOperation("老师查询的学生分数信息")
    @GetMapping("/selectByParentId.do")
    public JsonBean selectByParentId(Integer parentId){
        List<UserVo> userVos = userVoService.selectByParentId(parentId);
        return new JsonBean(0,userVos);
    }

}
