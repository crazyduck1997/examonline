package com.qf.examonline.controller;


import com.github.pagehelper.PageInfo;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.Type;
import com.qf.examonline.service.PaperService;
import com.qf.examonline.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "科目类型管理")
@RestController
@CrossOrigin
@RequestMapping("/typeController")
public class TypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private CodeMsg codeMsg;
    @Autowired
    private PaperService paperService;
    @ApiOperation("查询所有种类")
    @PostMapping("/faindAllType.do")
    public JsonBean findAllType(String typeName,Integer page,Integer limit){
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<Type> typeList = typeService.findAllType(typeName,page, limit);

        map.put("count",typeService.findTypeCount(typeName));
        map.put("data",typeList);
        return new JsonBean(ErrorCode.SUCCESS,map);
    }

    @ApiOperation("添加新的科目种类")
    @PostMapping("/addNewType.do")
    public JsonBean addNewType(String typeName){
        if (typeName == null || typeName.equals("")){
            return new JsonBean(ErrorCode.ERROR,codeMsg.getTypeNameEmpyy());
        }
        try {
            typeService.addNewPaperType(typeName);
            return new JsonBean(ErrorCode.SUCCESS, codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.ERROR,codeMsg.getNameRepeat());
        }

    }

    @ApiOperation("通过id查找type信息")
    public JsonBean findTypeById(Integer typeId){
        Type type = typeService.findOneTypeByTypeId(typeId);
        return new JsonBean(ErrorCode.SUCCESS,type);
    }


    @ApiOperation("修改种类的名称")
    @PostMapping("/updateOneTypeNameByTypeId.do")
    public JsonBean updateOneTypeByTypeId(@RequestBody Type type){
        String typeName = type.getTypeName();
        if (typeName == null || type.equals("")){
            return new JsonBean(ErrorCode.ERROR,codeMsg.getTypeNameEmpyy());
        }
        try {
            typeService.updatePaperTypeName(type);
            return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.ERROR,codeMsg.getNameRepeat());
        }

    }

    @ApiOperation(value = "删除一个种类",notes = "需要传入对应的种类的typeId")
    @PostMapping("/deleteOneType.do")
    public JsonBean deleteOneType(Integer typeId){
        typeService.deleteOnePaperType(typeId);

        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "查询一个类别中的科目平均成绩",notes = "需传入一个typeId")
    @PostMapping("/findAvgScore.do")
    public JsonBean findAvgScore(Integer typeId){
        List<Paper> avgScoreList = paperService.findAvgScore(typeId);
        if (avgScoreList.size() == 0){
            return new JsonBean(ErrorCode.SUCCESS,"暂无成绩");
        }
        return new JsonBean(ErrorCode.SUCCESS,avgScoreList);
    }

}
