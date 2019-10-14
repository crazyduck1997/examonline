package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectQuestions {
    private Integer sqId;

    private String sqDesc;

    private String selectA;

    private String selectB;

    private String selectC;

    private String selectD;

    private String selectAnswer;


}