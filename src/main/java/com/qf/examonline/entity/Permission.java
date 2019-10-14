package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer pid;

    private String pname;

    private String pdesc;

    private Integer type;

    private Integer parentid;

    private String url;


}