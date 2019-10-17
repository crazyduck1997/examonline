package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Integer sid;

    private Integer score;

    private Integer stuId;

    private Integer paperId;

    private String commitRepeat;


}