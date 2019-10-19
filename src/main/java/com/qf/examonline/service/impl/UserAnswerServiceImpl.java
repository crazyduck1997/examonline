package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.ScoreDao;
import com.qf.examonline.dao.UserAnswersDao;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.*;
import com.qf.examonline.service.UserAnswerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    UserAnswersDao userAnswersDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

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
     * @param list
     */
    @Override
    public void commitPaper(List<UserAnswers> list) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        User user = userDao.selectByUsername(username);
        UserAnswers answers = list.get(0);
        String s = String.valueOf(user.getUid())+answers.getPaperId();
        Score score = scoreDao.selectByCommit(s);
        if(score!=null){
            throw new RuntimeException(codeMsg.getCommitRepeat());
        }
        userAnswersDao.insert(list);
        amqpTemplate.convertAndSend("queue.commit", list);

    }

    @Override
    public void commitSelect(Integer questionId,String answer,Integer questionType) {
        Subject subject = SecurityUtils.getSubject();
        String username =(String) subject.getPrincipal();
        User user = userDao.findUserByName(username);
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
