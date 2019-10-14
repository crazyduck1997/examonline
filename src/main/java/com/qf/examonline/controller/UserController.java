package com.qf.examonline.controller;

import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@Controller
@CrossOrigin
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户")
    @PostMapping("/selectUser.do")
    @ResponseBody
    public JsonBean selectUser(){
        List<User> users = userService.selectAll();
        return new JsonBean(0,users);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/insertUser.do")
    @ResponseBody
    public JsonBean insertUser(User user){
        try {
            userService.insert(user);
            return new JsonBean(0,"添加成功");
        } catch (Exception e) {
            return new JsonBean(1,"姓名重复");
        }

    }

    @ApiOperation(value = "根据id查询")
    @PostMapping("/findUserById.do")
    @ResponseBody
    public JsonBean findUserById(Integer uid){
        User user = userService.findById(uid);
        return new JsonBean(0,user);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/updateUser.do")
    @ResponseBody
    public JsonBean updateUser(@RequestBody User user){
        try {
            userService.updateByPrimaryKey(user);
            return new JsonBean(0,"修改成功");
        } catch (Exception e) {
            return new JsonBean(1,"姓名重复");
        }
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/deleteUser.do")
    @ResponseBody
    public JsonBean deleteUser(Integer uid){
        userService.deleteByPrimaryKey(uid);
        return new JsonBean(0,"删除成功");
    }

}
