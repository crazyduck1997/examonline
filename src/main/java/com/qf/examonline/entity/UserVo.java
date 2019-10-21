package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer uid;
    private String username;
    private String typeName;
    private String paperName;
    private Integer score;
}
