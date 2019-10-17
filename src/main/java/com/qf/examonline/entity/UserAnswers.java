package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswers implements Serializable {
    private Integer cid;

    private Integer uid;

    private Integer paperId;

    private Integer cQuestionType;

    private String cAnswer;

    private Integer cQuestionId;


}