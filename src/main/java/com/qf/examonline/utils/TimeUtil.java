package com.qf.examonline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {


    public static Date dateFormate(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = dateFormat.parse(time);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("转换异常");
    }

}
