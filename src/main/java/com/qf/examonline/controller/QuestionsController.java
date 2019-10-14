package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.service.BooleanQuestionService;
import com.qf.examonline.service.SelectQuestionsService;
import com.qf.examonline.service.SketchQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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



    @RequestMapping("/addSelectQuestions.do")
    public JsonBean addSelectQuestions(MultipartFile file){
        if(file.isEmpty()){
            new JsonBean(ErrorCode.ERROR,codeMsg.getIsEmpty());
        }
        return new JsonBean(selectQuestionsService.insertSelectQuestions(file),codeMsg.getExecteSuccess());
    }

    @RequestMapping("/addBooleanQuestions.do")
    public JsonBean addBooleanQuestion(MultipartFile file){
        if(file.isEmpty()){
            new JsonBean(ErrorCode.ERROR,codeMsg.getIsEmpty());
        }
        return new JsonBean(booleanQuestionService.insertBooleanQuestions(file),codeMsg.getExecteSuccess());
    }

    @RequestMapping("/addSketchQuestions.do")
    public JsonBean addSketchQuestions(MultipartFile file){
        if(file.isEmpty()){
            new JsonBean(ErrorCode.ERROR,codeMsg.getIsEmpty());
        }
        return new JsonBean(sketchQuestionsService.insertSketchQuestions(file),codeMsg.getExecteSuccess());
    }



}
