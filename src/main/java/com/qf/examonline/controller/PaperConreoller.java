package com.qf.examonline.controller;


import com.github.pagehelper.PageInfo;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.QuestionVo;
import com.qf.examonline.service.PaperService;
import com.qf.examonline.service.TypeService;
import com.qf.examonline.service.UserAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "试卷管理")
@RestController
@CrossOrigin
@RequestMapping("/paper")
public class PaperConreoller {
    @Autowired
    private TypeService typeService;
    @Autowired
    private CodeMsg codeMsg;
    @Autowired
    private PaperService paperService;

    @Autowired
    UserAnswerService userAnswerService;

    @ApiOperation(value = "查看所有考试试卷")
    @PostMapping("/selectAll.do")
    public JsonBean selectAll(Integer pageNum,Integer pageSize) {
        JsonBean<Object> jsonBean = new JsonBean<>();
        try {
            PageInfo<Paper> paperPageInfo = paperService.selectAll(pageNum, pageSize);
            jsonBean.setCode(0);
            jsonBean.setData(paperPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonBean.setCode(1);
            jsonBean.setData("查看失败");
        }
        return jsonBean;
    }

    @ApiOperation(value = "增加一个试卷")
    @PostMapping("/addPaper.do")
    public JsonBean addPaper(Paper record) {

        try {
            paperService.insert(record);
        } catch (Exception e) {
            return new JsonBean(1, "添加失败");
        }
        return new JsonBean(0, "添加成功");
    }

    @ApiOperation(value = "修改试卷信息")
    @PostMapping("/updatePaper.do")
    @ResponseBody
    public JsonBean updatePaper(Paper paper){
        paperService.updateByPrimaryKey(paper);
        return new JsonBean(1,null);
    }

    @ApiOperation(value = "查找试卷")
    @PostMapping("/findPaperById.do")
    public JsonBean findPaperById(Integer paperId){
        Paper paper = paperService.selectByPrimaryKey(paperId);
        return new JsonBean(1,paper);
    }

    @ApiOperation(value = "所有测试卷子")
    @GetMapping("/allPaper.do")
    public JsonBean allPaper(String paperName,Integer typeId, Integer page, Integer limit){
        List<QuestionVo> list = paperService.selectTests(paperName,typeId,page,limit);
        return new JsonBean(0,list);
    }

    @ApiOperation(value = "所有考试")
    @GetMapping("/paperVo.do")
    public JsonBean paperVo(){
        List<QuestionVo> list = paperService.selectVo();
        return new JsonBean(0,list);
    }


    @ApiOperation(value ="开始考试" )
    @PostMapping("/getPaper.do")
    public JsonBean getPaper(Integer paperId){
        Map map = paperService.examStatus(paperId);
        return new JsonBean(0,map);
    }

    @ApiOperation(value ="开始测试" )
    @PostMapping("/bigenTest.do")
    public JsonBean bigenTest(Integer paperId){
        Map map = paperService.selectPaper(paperId);
        return new JsonBean(0,map);
    }


    @ApiOperation(value = "查看考试剩余时间")
    @PostMapping("checkTime.do")
    public JsonBean checkTime(Integer paperId){
        Map map = paperService.checkTime(paperId);
        if(map.get("status")!=null){
            userAnswerService.commitPaper(paperId);
            return new JsonBean(ErrorCode.ERROR,codeMsg.getExamOver());
        }
        return new JsonBean(ErrorCode.SUCCESS,map);
    }


    @GetMapping("/lookMyPapers.do")
    public JsonBean lookMyPapers(String paperName,Integer typeId, Integer page, Integer limit){
        List<QuestionVo> list = paperService.selectMyPapers(paperName,typeId,page,limit);
        return new JsonBean(ErrorCode.SUCCESS,list);
    }



}
