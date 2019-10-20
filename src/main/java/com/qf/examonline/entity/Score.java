package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Integer sid;

    private Integer score;

    private Integer stuId;

    private Integer paperId;

    private Date commitTime;

    private Date updateTime;

    private String commitRepeat;


}