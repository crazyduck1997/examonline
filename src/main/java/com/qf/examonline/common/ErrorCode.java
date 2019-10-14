package com.qf.examonline.common;

public class ErrorCode {

    // 登录密码不能为空
    public static int PASSWORD_EMPTY = 1001;

    // success：对应JsonBean的code 表示成功
    public static int SUCCESS = 0;

    // ERROR:对应JsonBean的code  表示失败
    public static int ERROR = 1;

    //EXECUTE_SUCCESS：对应JsonBean中info，表示：执行成功
    public static int EXECUTE_SUCCESS = 2000;

    //EXECUTE_FAILE：对应JsonBean中info，表示：执行失败
    public static int EXECUTE_FAILE = 2001;

}
