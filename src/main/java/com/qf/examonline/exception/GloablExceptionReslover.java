package com.qf.examonline.exception;

import com.qf.examonline.common.JsonBean;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常类
 */
@ControllerAdvice
public class GloablExceptionReslover {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonBean exception(Exception e){
        return new JsonBean(1,e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public String exception(AuthorizationException e){
        return "error/noPerms";
    }
}
