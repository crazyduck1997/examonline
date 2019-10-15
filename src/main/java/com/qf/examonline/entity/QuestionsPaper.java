package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsPaper {
    private Integer qpId;

    private Integer paperId;

    private Integer questionId;

    private String questionType;

}