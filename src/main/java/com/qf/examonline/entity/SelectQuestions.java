package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectQuestions implements Serializable {
    private Integer sqId;

    private String sqDesc;

    private String selectA;

    private String selectB;

    private String selectC;

    private String selectD;

    private String selectAnswer;

    private Integer questionType;

    private Integer paperType;

    private Integer selectScore;



}