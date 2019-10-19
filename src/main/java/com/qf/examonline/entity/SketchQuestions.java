package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SketchQuestions implements Serializable {
    private Integer skeId;

    private String skeAnswer;

    private String skeDesc;

    private Integer questionType;

    private Integer paperType;

    private Integer skeScore;


}