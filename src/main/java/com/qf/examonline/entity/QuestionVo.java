package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVo {

    private Integer sqId;
    private String sqDesc;
    private String questionType;


    private Integer paperId;
    private String paperName;
    private Date beginTime;
    private Date endTime;
    private Integer time;
    private String typeName;

    //examinee模块
    private String name;
    private String examType;
   //private String paperName;
    private Date examTime;
    private String score;

}
