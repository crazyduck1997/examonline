package com.qf.examonline;

import com.qf.examonline.dao.*;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.entity.User;
import com.qf.examonline.utils.ImportExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Resource
    UserDao userDao;


    @Resource
    SelectQuestionsDao selectQuestionsDao;

    @Resource
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    RedisTemplate myRedisTemplate;

    @Resource
    SketchQuestionsDao sketchQuestionsDao;

    @Resource
    UserAnswersDao userAnswersDao;



    @Test
    public void test1(){
        String originUrl = "D:\\XY\\test.xls";

        List<SelectQuestions> linesList;
        try {
            linesList = ImportExcelUtil.importExcel(originUrl,SelectQuestions.class);
            int insert = selectQuestionsDao.insert(linesList);
            System.out.println(insert);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        List<SketchQuestions> list = sketchQuestionsDao.findSketchQuestionsByRandom(1, 3);
        for(SketchQuestions s : list){
            System.out.println(s);
        }
    }


    @Test
    public void test3(){
        Paper paper =(Paper) myRedisTemplate.opsForValue().get("57");
        Date endTime = paper.getEndTime();
        Date currentTime = new Date();
        long interval = endTime.getTime() - currentTime.getTime();
        System.out.println(interval);
        long h = interval / 1000 / 60 / 60;
        long m = (interval / 1000 / 60) - (h * 60);
        long s = (interval / 1000) - (h * 60 * 60)-(m*60);
        HashMap<String, Object> map = new HashMap<>();
        map.put("hours",h);
        map.put("minute",m);
        map.put("second",s);
        System.out.println(h+"---"+m+"--"+s);
    }

    @Test
    public void test4(){
        User userByName = userDao.findUserByName("1");
        System.out.println(userByName);
    }


}
