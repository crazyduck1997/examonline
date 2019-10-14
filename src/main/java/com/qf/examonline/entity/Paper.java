package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper {

    private Integer paperId;

    private String paperName;

    private Date beginTime;

    private Date endTime;

    private Integer paperStatus;

    private Integer typeId;

    private Integer avgscore;
}