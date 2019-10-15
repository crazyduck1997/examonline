package com.qf.examonline.controller;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    CodeMsg codeMsg;

    @RequestMapping("/login")
    @ResponseBody
    public JsonBean login(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
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
    public String register(String username,String password){
        loginService.register(username, password);
        return "success";
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
}
