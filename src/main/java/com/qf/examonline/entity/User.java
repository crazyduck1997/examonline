package com.qf.examonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uid;

    private String username;

    private String password;

    private Integer parentId;

    private List<Score> scores;
    private List<Paper> papers;
    private List<Type>  types;
    private List<User> users;

}