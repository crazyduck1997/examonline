package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer stuId;

    private String stuName;

    private String stuPassword;

    private Integer suid;

    private Integer teaId;


}