package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.dao.ScoreDao;
import com.qf.examonline.dao.UserAnswersDao;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.*;
import com.qf.examonline.service.UserAnswerService;
import com.qf.examonline.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    UserAnswersDao userAnswersDao;

    @Autowired
    private AmqpTemplate myRabbitmq;

    /*@Autowired
    private AmqpTemplate amqpTemplate;*/

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate myRedisTemplate;

    /**
     * 自动判卷，选择题/判断题
     * @param paperId
     */
    @Override
    public void commitPaper(Integer paperId) {
        User user = UserUtil.getUser();
        String s = String.valueOf(user.getUid())+paperId;
        Score score = scoreDao.selectByCommit(s);
        if(score!=null){
            throw new RuntimeException(codeMsg.getCommitRepeat());
        }

        Map<String,Object> redisMap =(Map) myRedisTemplate.opsForValue().get(String.valueOf(user.getUid()));
        redisMap.put("uid",user.getUid());
        redisMap.put("paperId",paperId);
        userAnswersDao.insertMap(redisMap);
        myRabbitmq.convertAndSend("queue.commit",user.getUid());

    }

    /**
     * 保存答案
     * @param questionId
     * @param answer
     * @param questionType
     */
    @Override
    public void commitSelect(Integer questionId,String answer,Integer questionType) {
        User user = UserUtil.getUser();
        HashMap map =(HashMap) myRedisTemplate.opsForValue().get(String.valueOf(user.getUid()));
        if(questionType==1){
            List<SelectQuestions> selectList =(List) map.get("selectList");
            for(SelectQuestions s : selectList){
                if(s.getSqId().equals(questionId)){
                    s.setSelectAnswer(answer);
                }
            }
            map.put("selectList",selectList);
            myRedisTemplate.opsForValue().set(String.valueOf(user.getUid()),map);
        }else if(questionType==2){
            List<BooleanQuestions> booleanList =(List) map.get("booleanList");
            for(BooleanQuestions b : booleanList){
                if(b.getBooId().equals(questionId)){
                    b.setBooAnswer(answer);
                }
            }
            map.put("booleanList",booleanList);
            myRedisTemplate.opsForValue().set(String.valueOf(user.getUid()),map);
        }else if(questionType==3){
            List<SketchQuestions> sketchList =(List) map.get("sketchList");
            for(SketchQuestions s : sketchList){
                if(s.getSkeId().equals(questionId)){
                    s.setSkeAnswer(answer);
                }
            }
            map.put("sketchList",sketchList);
            myRedisTemplate.opsForValue().set(String.valueOf(user.getUid()),map);
        }

    }
}
