package com.qf.examonline.controller;


import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Role;
import com.qf.examonline.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Api(tags = "角色管理")
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private CodeMsg codeMsg;

    @ApiOperation(value = "查询所有角色")
    @PostMapping("/selectAllRoles.do")
    public JsonBean selectAll(){
        List<Role> roleList = roleService.selectAll();
        return new JsonBean(ErrorCode.SUCCESS,roleList);
    }

    @ApiOperation(value = "添加新的角色",notes = "传入一个rname")
    @PostMapping("/addOneRole.do")
    public JsonBean insertOneRole(String rname){
        if (rname == null){
            return new JsonBean(ErrorCode.ERROR,codeMsg.getTypeNameEmpyy());
        }
        try {
            roleService.insert(rname);
            return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.ERROR,codeMsg.getNameRepeat());
        }
    }

    @ApiOperation(value = "通过id查找role信息",notes = "需传入一个rid")
    @PostMapping("/findByRid.do")
    public JsonBean findById(Integer rid){
        Role role = roleService.selectByPrimaryKey(rid);
        return new JsonBean(ErrorCode.SUCCESS,role);
    }

    @ApiOperation(value = "修改角色",notes = "需传入一个角色对象")
    @PostMapping("/updateRole.do")
    public JsonBean updateOneRole(@RequestBody Role role){
        if (role.getRname() == null || role.getRname().equals("")){
            return new JsonBean(ErrorCode.ERROR,codeMsg.getTypeNameEmpyy());
        }
        try {
            roleService.updateByPrimaryKey(role);
            return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.ERROR,codeMsg.getNameRepeat());
        }
    }

    @ApiOperation(value = "删除role",notes = "需要传入一个rid")
    @PostMapping("/deleteOneRole.do")
    public JsonBean deleteRoleByRid(Integer rid){
         roleService.deleteByPrimaryKey(rid);
         return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

}
