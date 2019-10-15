package com.qf.examonline;

import com.github.pagehelper.PageInfo;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

    @Resource
    UserDao userDao;

    @Autowired
    UserService userService;


    @Test
    public void test1(){
        PageInfo<User> pageInfo = userService.selectAll("", 1, 5);
        System.out.println(pageInfo);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setUsername("maliu");
        user.setPassword("123");
        user.setParentId(4);
        userDao.insert(user);
        System.out.println(user);
    }

    @Test
    public void test3(){
        User user = new User();
        user.setUid(20);
        user.setUsername("Leon");
        userDao.updateByPrimaryKey(user);
        System.out.println(user);
    }

    @Test
    public void test4(){
        userDao.deleteByPrimaryKey(20);
    }

    @Test
    public void test5(){
        User byId = userDao.findById(1);
        System.out.println(byId);
    }

    @Test
    public void test6(){
        Integer count = userDao.findCount("");
        System.out.println(count);
    }
}
