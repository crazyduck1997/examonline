package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Permission;
import com.qf.examonline.service.LoginService;
import com.qf.examonline.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CodeMsg codeMsg;

    @Autowired(required = false)
    private PermissionService permissionService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonBean login(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        List<String> roles = loginService.findRolesByName(username);
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonBean(ErrorCode.LOGIN_FAILE, codeMsg.getLoginFaile());
        }
        return new JsonBean(ErrorCode.LOGIN_SUCCESS,codeMsg.getLoginSuccess());
    }
    @RequestMapping("/register")
    @ResponseBody
    public JsonBean register(String username,String password){
        loginService.register(username, password);
        return new JsonBean<>(1,"注册成功");
    }
    @RequestMapping("/checkUser")
    @ResponseBody
    public JsonBean checkUser(String username){
        boolean flag = loginService.checkUser(username);
        if (!flag){
            return new JsonBean(ErrorCode.REPEAT_USERNAME,codeMsg.getRepeatUsername());
        }
        return new JsonBean(ErrorCode.ENABLE_USERNAME,codeMsg.getEnableUsername());
    }
    @RequestMapping("/getUsername")
    @ResponseBody
    public JsonBean getUsername(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return new JsonBean(1,username);
    }
    @RequestMapping("/listMenu")
    @ResponseBody
    public JsonBean listMenu(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Permission> menu = permissionService.findMenuByName(username);
        return new JsonBean(1,menu);
    }
}
