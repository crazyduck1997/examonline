package com.qf.examonline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import com.qf.examonline.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;

    @Autowired
    CodeMsg codeMsg;


    @Override
    public User login(String username) {
        User user = userDao.selectByUsername(username);
        return user;
    }

    //业务层：查用户
    @Override
    public List<User> selectAll(String username,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<User> userList = userDao.selectAll(username);
        return userList;
    }

    //业务层：添加用户
    @Override
    public void insert(User record) {
        User user = userDao.findUserByName(record.getUsername());
        if (user != null){
            throw new RuntimeException(codeMsg.getNameRepeat());
        }
        userDao.insert(record);
    }

    //业务层：根据id查询
    @Override
    public User findById(Integer uid) {
        User user = userDao.findById(uid);
        return user;
    }

    //业务层：修改用户
    @Override
    public void updateByPrimaryKey(User record) {
        User user = userDao.findUserByName(record.getUsername());
        if (user != null){
            throw new RuntimeException(codeMsg.getNameRepeat());
        }

        String s = updatePassword(record.getPassword());
        System.out.println("修改后加密的密码"+s);
        record.setPassword(s);
        userDao.updateByPrimaryKey(record);
    }

    //业务层：删除用户
    @Override
    public void deleteByPrimaryKey(Integer uid) {
        userDao.deleteByPrimaryKey(uid);
    }

    @Override
    public Integer fingCount(String username) {
        Integer count = userDao.findCount(username);
        return count;
    }

    @Override
    public String updatePassword(String password) {
        String up = new SimpleHash("md5", password,"haha", 1).toHex();
        return up;
    }
}
