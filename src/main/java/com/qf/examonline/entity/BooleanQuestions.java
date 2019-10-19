package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooleanQuestions implements Serializable {
    private Integer booId;

    private String booDesc;

    private String booAnswer;

    private Integer questionType;

    private Integer paperType;

    private Integer booScore;


}