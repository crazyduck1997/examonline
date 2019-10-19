package com.qf.examonline.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Examinee {
    private Integer examineeId;

    private String name;

    private String idcard;

    private String phone;

    private String address;

    private String description;

    private Date enrollTime;

    private Integer examTypeId;

    private Date examTime;

    private Integer uid;
}