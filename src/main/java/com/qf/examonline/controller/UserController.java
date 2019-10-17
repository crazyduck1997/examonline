package com.qf.examonline.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户管理")
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户")
    @GetMapping("/selectUser.do")
    @ResponseBody
    //@RequiresPermissions("score:list")
    @RequiresRoles("管理员")
    public Map selectUser(String username, Integer page, Integer limit){
        List<User> userPageInfo = userService.selectAll(username, page, limit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("count",((Page)userPageInfo).getTotal());
        map.put("data",userPageInfo);
        return map;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/insertUser.do")
    @ResponseBody
    public JsonBean insertUser(User user){
        try {
            userService.insert(user);
            return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.REPEAT_USERNAME,codeMsg.getNameRepeat());
        }
    }
    @ApiOperation(value = "根据id查询")
    @PostMapping("/findUserById.do")
    @ResponseBody
    public JsonBean findUserById(Integer uid){
        User user = userService.findById(uid);
        return new JsonBean(ErrorCode.SUCCESS,user);
    }

    @ApiOperation("管理员最高权限重置用户密码")
    @PostMapping("/resetPassword.do")
    @ResponseBody
    public JsonBean resetPassword(User user){
        userService.resetPassword(user);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/updateUser.do")
    @ResponseBody
    public JsonBean updateUser(User user){
        try {
            userService.updateByPrimaryKey(user);
            return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
        } catch (Exception e) {
            return new JsonBean(ErrorCode.REPEAT_USERNAME,codeMsg.getNameRepeat());
        }
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/deleteUser.do")
    @ResponseBody
    public JsonBean deleteUser(Integer uid){
        userService.deleteByPrimaryKey(uid);
        return new JsonBean(ErrorCode.SUCCESS,codeMsg.getExecteSuccess());
    }

}
