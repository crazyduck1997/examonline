package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SketchQuestions {
    private Integer skeId;

    private String skeAnswer;

    private String skeDesc;

}