package com.qf.examonline.controller;


import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Type;
import com.qf.examonline.service.TypeService;
import com.qf.examonline.service.impl.TypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "试卷管理")
@RestController
@CrossOrigin
@RequestMapping("/paperController")
public class PaperConreoller {

    @Autowired
    private TypeService typeService;
    @ApiOperation("查询所有种类")
    @PostMapping("/faindAllType.do")
    public JsonBean findAllType(){
        List<Type> typeList = typeService.findAllType();
        return new JsonBean(0,typeList);
    }

    @ApiOperation("添加新的科目种类")
    @PostMapping("/addNewType.do")
    public JsonBean addNewType(String typeName){
        typeService.addNewPaperType(typeName);
        return new JsonBean(0,"添加成功");
    }

    @ApiOperation("修改种类的名称")
    @PostMapping("/updateOneTypeNameByTypeId.do")
    public JsonBean updateOneTypeByTypeId(@RequestBody Type type){
        typeService.updatePaperTypeName(type);
        return new JsonBean(0,"修改成功");
    }


}
