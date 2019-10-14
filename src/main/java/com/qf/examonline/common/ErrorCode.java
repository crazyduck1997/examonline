package com.qf.examonline.common;

public class ErrorCode {

    // 登陆成功
    public static int LOGIN_SUCCESS = 1001;
    // 登陆失败
    public static int LOGIN_FAILE = 1002;
    //用户名重复
    public static int   REPEAT_USERNAME = 1003;
    //用户名可用
    public static int   ENABLE_USERNAME = 1004;
    // success：对应JsonBean的code 表示成功
    public static int SUCCESS = 0;

    // ERROR:对应JsonBean的code  表示失败
    public static int ERROR = 1;

    //EXECUTE_SUCCESS：对应JsonBean中info，表示：执行成功
    public static int EXECUTE_SUCCESS = 2000;

    //EXECUTE_FAILE：对应JsonBean中info，表示：执行失败
    public static int EXECUTE_FAILE = 2001;


}
