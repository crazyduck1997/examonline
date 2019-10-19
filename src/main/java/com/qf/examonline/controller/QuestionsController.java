package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.*;
import com.qf.examonline.service.*;
import com.qf.examonline.utils.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    QuestionTypeService questionTypeService;

    @Autowired
    UserAnswerService userAnswerService;


    @ApiOperation(value = "导入excel到题库", notes = "1代表选择题，2代表判断题，其他代表简述题")
    @PostMapping("/importQuestions.do")
    public JsonBean importSelectQuestions(MultipartFile file) {
        if (file.isEmpty()) {
            new JsonBean(ErrorCode.ERROR, codeMsg.getIsEmpty());
        }
        try {
            XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheetAt = sheets.getSheetAt(0);
            XSSFRow row = sheetAt.getRow(1);
            XSSFCell cell = row.getCell(0);
            if (cell.toString().trim().equals("1.0")) {
                selectQuestionsService.insertQuestions(file);
            } else if (cell.toString().trim().equals("2.0")) {
                booleanQuestionService.insertBooleanQuestions(file);
            } else if (cell.toString().trim().equals("3.0")) {
                sketchQuestionsService.insertSketchQuestions(file);
            } else {
                throw new RuntimeException(codeMsg.getExecteFaile());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加选择题")
    @PostMapping("/addSelectQuestions.do")
    public JsonBean addSelectQuestions(SelectQuestions selectQuestions) {
        selectQuestionsService.insertSelect(selectQuestions);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加判断题")
    @PostMapping("/addBooleanQuestions.do")
    public JsonBean addBooleanQuestions(BooleanQuestions booleanQuestions) {
        booleanQuestionService.insertBoolean(booleanQuestions);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "添加简答题")
    @PostMapping("/addSketchQuestions.do")
    public JsonBean addSketchQuestions(SketchQuestions sketchQuestions) {
        sketchQuestionsService.insertSketch(sketchQuestions);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "手动选择题目生成试卷")
    @RequestMapping("/addPaper.do")
    public JsonBean addPaper(@RequestBody Map<String, Object> map) {
        if (map == null) {
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        questionPaperService.addQuestions(map);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "自动生成试卷")
    @PostMapping("/autoMakePaper.do")
    public JsonBean autoMakePaper(@RequestParam(required = false) Integer selectNum, @RequestParam(required = false) Integer booleanNum, @RequestParam(required = false) Integer sketchNum, String beginTime,String endTime, Integer typeId, String paperName) {
        Date begin = TimeUtil.dateFormate(beginTime);
        Date end = TimeUtil.dateFormate(endTime);
        if (selectNum == 0 && sketchNum == 0 && booleanNum == 0) {
            throw new RuntimeException("未选择题目");
        }
        questionPaperService.insertAutoMakePaper(selectNum, booleanNum, sketchNum, typeId, paperName,begin,end);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "查询对应课程的选择/判断/简答题")
    @PostMapping("/findQuestions.do")
    public JsonBean findQuestions(Integer typeId, Integer questionType) {
        List list = questionPaperService.findQuestions(typeId, questionType);
        return new JsonBean(ErrorCode.SUCCESS, list);
    }

    @ApiOperation(value = "学生提交试卷/自动判卷(选择判断)")
    @PostMapping("/commitPaper.do")
    public JsonBean commitPaper(Integer paperId) {
        userAnswerService.commitPaper(paperId);
        return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
    }

    @PostMapping("/saveQuestion.do")
    public JsonBean saveQuestion(Integer questionId,String answer,Integer questionType){
        userAnswerService.commitSelect(questionId,answer,questionType);
        return new JsonBean(0,"保存成功");
    }


    @ApiOperation(value = "题目类型")
    @GetMapping("/questionTypeList.do")
    public JsonBean questionTypeList() {
        List<QuestionType> list = questionTypeService.selectAll();
        return new JsonBean(ErrorCode.SUCCESS, list);
    }


    @ApiOperation(value = "试题管理")
    @GetMapping("/findAllQuertions.do")
    public JsonBean findAllQuertions(){
        List<QuestionVo> allQuertions = questionTypeService.findAllQuertions();
        return new JsonBean(ErrorCode.SUCCESS,allQuertions);
    }

}
