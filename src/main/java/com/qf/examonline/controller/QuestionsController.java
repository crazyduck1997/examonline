package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.entity.UserAnswers;
import com.qf.examonline.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    UserAnswerService userAnswerService;


    @ApiOperation(value = "导入excel到题库",notes = "1代表选择题，2代表判断题，其他代表简述题")
    @PostMapping("/importQuestions.do")
    public JsonBean importSelectQuestions(MultipartFile file,String type){
        if(file.isEmpty()){
            new JsonBean(ErrorCode.ERROR,codeMsg.getIsEmpty());
        }
        if(type.equals("1")){
            //codeMsg问题
            selectQuestionsService.insertQuestions(file);
        }else if(type.equals("2")){
            booleanQuestionService.insertBooleanQuestions(file);
        }else {
            sketchQuestionsService.insertSketchQuestions(file);
        }
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加选择题")
    @PostMapping("/addSelectQuestions.do")
    public JsonBean addSelectQuestions(@RequestBody List<SelectQuestions> list){
        selectQuestionsService.addSelectQuestions(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加判断题")
    @PostMapping("/addBooleanQuestions.do")
    public JsonBean addBooleanQuestions(@RequestBody List<BooleanQuestions> list){
        booleanQuestionService.addBooleanQuestion(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加简答题")
    @PostMapping("/addSketchQuestions.do")
    public JsonBean addSketchQuestions(@RequestBody List<SketchQuestions> list){
        sketchQuestionsService.addSketchQuestion(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "手动选择题目生成试卷")
    @RequestMapping("/addPaper.do")
    public JsonBean addPaper(@RequestBody Map<String,Object> map){
        if(map==null){
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        questionPaperService.addQuestions(map);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "自动生成试卷")
    @PostMapping("/autoMakePaper.do")
    public JsonBean autoMakePaper(@RequestParam(required = false) Integer selectNum,@RequestParam(required = false)Integer booleanNum,@RequestParam(required = false)Integer sketchNum,Integer typeId,String paperName){
        if(selectNum==0 && sketchNum == 0 && booleanNum == 0){
            throw new RuntimeException("未选择题目");
        }
        questionPaperService.insertAutoMakePaper(selectNum,booleanNum,sketchNum,typeId,paperName);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "查询对应课程的选择/判断/简答题")
    @PostMapping("/findQuestions.do")
    public JsonBean findQuestions(Integer typeId,Integer questionType){
            List list = questionPaperService.findQuestions(typeId, questionType);
            return new JsonBean(ErrorCode.SUCCESS,list);
    }

    @ApiOperation(value = "学生提交试卷/自动判卷(选择判断)")
    @PostMapping("/commitPaper.do")
    public JsonBean commitPaper(@RequestBody List<UserAnswers> list){
        if(list==null){
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        userAnswerService.commitPaper(list);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }


}
