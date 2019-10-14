package com.qf.examonline.exception;

import com.qf.examonline.common.JsonBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常类
 */
@RestControllerAdvice
public class GloablExceptionReslover {

    @ExceptionHandler(Exception.class)
    public JsonBean exception(Exception e){
        return new JsonBean(1,e.getMessage());
    }
}
