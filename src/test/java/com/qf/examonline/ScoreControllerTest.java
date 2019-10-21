package com.qf.examonline;


import com.qf.examonline.dao.ScoreDao;
import com.qf.examonline.entity.Score;
import com.qf.examonline.entity.User;
import com.qf.examonline.entity.UserVo;
import com.qf.examonline.service.ScoreService;
import com.qf.examonline.service.UserVoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreControllerTest {


    @Autowired(required = false)
   private ScoreDao scoreDao;

    @Autowired
    private UserVoService userVoService;

    @Autowired
    private ScoreService scoreService;

    //添加学生分数测试
    @Test
    public void insertTest(){
        Score score = new Score();
        score.setScore(9);
        score.setStuId(3);
        score.setPaperId(2);
        scoreService.insert(score);
        System.out.println(score);


    }

    //删除学生分数测试
    @Test
    public void deleteBySidTest(){
        int i = scoreDao.deleteBySid(67);
        System.out.println(i);
    }


    //修改学生成绩信息
    @Test
    public void updateTest(){
        Score score = new Score();
        score.setSid(14);
        score.setScore(11);
         scoreService.update(score);
        System.out.println(score);
    }

  //学生查看自己的分数
    @Test
    public void selectByUidTest(){
        List<UserVo> userVos = userVoService.selectByUid(1);
        System.out.println(userVos);
    }


    //展示所有学生的分数信息
    @Test
    public void selectAllTest(){
        List<UserVo> userVos = userVoService.selectAll();
        System.out.println(userVos);

    }

    //老师查看自己学生的分数
    @Test
    public void selectByParentId(){
        List<UserVo> userVos = userVoService.selectByParentId(4);
        System.out.println(userVos);
    }

}
