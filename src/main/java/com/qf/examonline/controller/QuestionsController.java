package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.service.BooleanQuestionService;
import com.qf.examonline.service.QuestionPaperService;
import com.qf.examonline.service.SelectQuestionsService;
import com.qf.examonline.service.SketchQuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

@Api(tags = "题库相关")
@RestController
@CrossOrigin
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    BooleanQuestionService booleanQuestionService;

    @Autowired
    SelectQuestionsService selectQuestionsService;

    @Autowired
    SketchQuestionsService sketchQuestionsService;

    @Autowired
    QuestionPaperService questionPaperService;

    @ApiOperation(value = "导入excel到题库",notes = "0代表选择题，1代表判断题，其他代表简述题")
    @RequestMapping("/importQuestions.do")
    public JsonBean importSelectQuestions(MultipartFile file,String type){
        if(file.isEmpty()){
            new JsonBean(ErrorCode.ERROR,codeMsg.getIsEmpty());
        }
        if(type.equals("0")){
            //codeMsg问题
            selectQuestionsService.insertQuestions(file);
        }else if(type.equals("1")){
            booleanQuestionService.insertBooleanQuestions(file);
        }else {
            sketchQuestionsService.insertSketchQuestions(file);
        }
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加选择题")
    @RequestMapping("/addSelectQuestions.do")
    public JsonBean addSelectQuestions(@RequestBody List<SelectQuestions> list){
        selectQuestionsService.addSelectQuestions(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加判断题")
    @RequestMapping("/addBooleanQuestions.do")
    public JsonBean addBooleanQuestions(@RequestBody List<BooleanQuestions> list){
        booleanQuestionService.addBooleanQuestion(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加简答题")
    @RequestMapping("/addSketchQuestions.do")
    public JsonBean addSketchQuestions(@RequestBody List<SketchQuestions> list){
        sketchQuestionsService.addSketchQuestion(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "选择题目生成试卷")
    @RequestMapping("/addPaper.do")
    public JsonBean addPaper(@RequestBody Map<String,Object> map){
        if(map==null){
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        questionPaperService.addQuestions(map);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }







}
