package com.qf.examonline.login;

import com.qf.examonline.dao.PermissionDao;
import com.qf.examonline.entity.Permission;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
    @Autowired
    LoginService loginService;
    @Autowired(required = false)
    PermissionDao permissionDao;
    @Test
    public void login(){
        User user = loginService.login("zhangsan");
        System.out.println(user);
    }
    @Test
    public void register(){
//        User user = new User();
//        user.setUsername("wangwu");
//        user.setPassword("123");
        int result = loginService.register("wangwu", "123");
        System.out.println(result);
    }
    @Test
    public void menu(){
//        User user = new User();
//        user.setUsername("wangwu");
//        user.setPassword("123");
        List<Permission> menuByName = permissionDao.findMenuByName("校长");
        System.out.println(menuByName);
    }
}
