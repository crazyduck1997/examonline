package com.qf.examonline.mq;

import com.qf.examonline.dao.*;
import com.qf.examonline.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RabbitListener(queues = "queue.commit")
public class StockConsumer {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    PaperDao paperDao;

    @Autowired
    QuestionsPaperDao questionsPaperDao;

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    SketchQuestionsDao sketchQuestionsDao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    UserDao userDao;


    @RabbitHandler
    public void receiver(Map map) {

        Integer paperId =(Integer) map.get("paperId");
        Integer uid = (Integer)map.get("uid");
        //初始化分数
        Integer sum = 0;
        List<SelectQuestions> selectList = (List)map.get("selectList");
        List<BooleanQuestions> booleanList = (List)map.get("booleanList");
        //循环获取提交的答案
        for(int i = 0;i<selectList.size();i++){
            SelectQuestions answer = selectList.get(i);
            Integer questionId = answer.getSqId();
            //答案类型进行判断
                SelectQuestions selectQuestions = selectQuestionsDao.selectByPrimaryKey(questionId);
                //对答案进行校验
                if(selectQuestions.getSelectAnswer().trim().equalsIgnoreCase(answer.getSelectAnswer().trim())){
                    sum += selectQuestions.getSelectScore();
                }
        }
        for(int i =0; i<booleanList.size();i++){
            BooleanQuestions answer = booleanList.get(i);
            BooleanQuestions booleanQuestions = booleanQuestionsDao.selectByPrimaryKey(answer.getBooId());
            if(booleanQuestions.getBooAnswer().trim().equals(answer.getBooAnswer().trim())){
                sum += booleanQuestions.getBooScore();
            }

        }
        //添加分数
        Score score = new Score();
        score.setPaperId(paperId);
        score.setScore(sum);
        score.setStuId(uid);
        score.setCommitRepeat(String.valueOf(uid)+paperId);
        scoreDao.insert(score);
    }
}




