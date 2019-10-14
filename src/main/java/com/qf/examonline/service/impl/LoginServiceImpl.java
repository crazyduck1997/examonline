package com.qf.examonline.service.impl;

import com.qf.examonline.dao.AdminDao;
import com.qf.examonline.dao.StudentDao;
import com.qf.examonline.dao.TeacherDao;
import com.qf.examonline.entity.Admin;
import com.qf.examonline.entity.Student;
import com.qf.examonline.entity.Teacher;
import com.qf.examonline.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired(required = false)
    AdminDao adminDao;

    @Autowired(required = false)
    StudentDao studentDao;

    @Autowired(required = false)
    TeacherDao teacherDao;

    public Object login(String username,String password,Integer uid){
        if (uid == 1){
            Admin admin = adminDao.selectByUsername(username);
            if (admin == null){
                throw new RuntimeException("用戶名不存在");
            }
            if (admin.getAPassword() != password){
                throw new RuntimeException("密码错误");
            }
        }
        if (uid == 2){
            Student student = studentDao.selectByUsername(username);
            if (student == null){
                throw new RuntimeException("用戶名不存在");
            }
            if (student.getStuPassword() != password){
                throw new RuntimeException("密码错误");
            }
        }
        if (uid == 3){
            Teacher teacher = teacherDao.selectByUsername(username);
            if (teacher == null){
                throw new RuntimeException("用戶名不存在");
            }
            if (teacher.getTeaPwd() != password){
                throw new RuntimeException("密码错误");
            }
        }
        throw new RuntimeException("你要创建的身份不存在");
    }
}
