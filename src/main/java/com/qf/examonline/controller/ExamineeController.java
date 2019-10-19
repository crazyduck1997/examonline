package com.qf.examonline.controller;

import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Examinee;
import com.qf.examonline.entity.QuestionVo;
import com.qf.examonline.service.ExamineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("examinee")
public class ExamineeController {

    @Autowired(required = false)
    ExamineeService examineeService;

    @RequestMapping("add")
    @ResponseBody
    public JsonBean add(Examinee examinee){
        System.out.println(examinee.getExamTime());
        examineeService.insert(examinee);
        return new JsonBean(0,"报名成功");
    }
    @RequestMapping("list")
    @ResponseBody
    public JsonBean list(Date start, Date end, String idcard, Integer examTypeId){
        List<QuestionVo> scores = examineeService.selectByVo(start, end, idcard, examTypeId);
        return new JsonBean(0,scores);
    }
}
