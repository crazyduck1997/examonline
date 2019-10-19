package com.qf.examonline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.examonline.dao.*;
import com.qf.examonline.entity.*;
import com.qf.examonline.service.PaperService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired(required = false)
    private TypeDao typeDao;

    @Autowired(required = false)
    private PaperDao paperDao;

    @Autowired
    QuestionsPaperDao questionsPaperDao;

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    SketchQuestionsDao sketchQuestionsDao;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate myRedisTemplate;


    @Override
    public int updatePaper(Integer paperId) {
//        paperDao.updatePaper(paperId)
        return 0;
    }

    @Override
    public int insert(Paper record) {
        return paperDao.insert(record);
    }

    @Override
    public Paper selectByPrimaryKey(Integer paperId) {
        return paperDao.selectByPrimaryKey(paperId);
    }

    @Override
    public PageInfo<Paper> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Paper> allPaper = paperDao.selectAll();
        PageInfo<Paper> paperPageInfo = new PageInfo<>(allPaper);

        return paperPageInfo;
    }

    @Override
    public int updateByPrimaryKey(Paper record) {
        return paperDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Paper> allPapers() {
        List<Paper> all = paperDao.selectAll();
        return all;
    }

    @Override
    public List<QuestionVo> selectVo() {
        List<QuestionVo> list = paperDao.selectVo();
        return list;
    }


    /**
     * 开始考试
     * @param paperId
     * @return
     */
    @Override
    public Map examStatus(Integer paperId) {
        Paper paper = paperDao.selectByPrimaryKey(paperId);
        Date beginTime = paper.getBeginTime();
            Date endTime = paper.getEndTime();
            Date currentTime = new Date();
            long interval = beginTime.getTime() - currentTime.getTime();
            if (interval > 0) { // 考试还没开始
                throw new RuntimeException("考试未开始");
            } else {
                long endInterval = endTime.getTime() - currentTime.getTime();
                if (endInterval < 0) { // 当前时间超过结束时间，考试结束
                    throw new RuntimeException("考试已结束");
                }
        }

            System.out.println("111");


            Subject subject = SecurityUtils.getSubject();
        String username =(String) subject.getPrincipal();
        User user = userDao.findUserByName(username);
        HashMap map = (HashMap)myRedisTemplate.opsForValue().get(String.valueOf(user.getUid()));
        if(map!=null){
            //判断是否正在进行
            Paper paper1 = (Paper)map.get("paper");
            if(!paper1.getPaperId().equals(paperId)){
                throw new RuntimeException("请返回考试");
            }
            return map;
        }
        ArrayList<SelectQuestions> selectList = new ArrayList<>();
        ArrayList<BooleanQuestions> booleanList = new ArrayList<>();
        ArrayList<SketchQuestions> sketchList = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        List<QuestionsPaper> list = questionsPaperDao.selectByPaperId(paperId);

        System.out.println("222");

        for(QuestionsPaper q : list){
            String s = q.getQuestionType();
            if(s.trim().equals("1")){
                SelectQuestions selectQuestions = selectQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                selectQuestions.setSelectAnswer(null);
                selectList.add(selectQuestions);
            }else if(s.trim().equals("2")){
                BooleanQuestions booleanQuestions = booleanQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                booleanQuestions.setBooAnswer(null);
                booleanList.add(booleanQuestions);
            }else {
                SketchQuestions sketchQuestions = sketchQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                sketchQuestions.setSkeAnswer(null);
                sketchList.add(sketchQuestions);
            }
        }
        hashMap.put("paper",paper);
        hashMap.put("selectList",selectList);
        hashMap.put("booleanList",booleanList);
        hashMap.put("sketchList",sketchList);

        System.out.println("--------");

        myRedisTemplate.opsForValue().set(String.valueOf(user.getUid()),hashMap);
        myRedisTemplate.expire(String.valueOf(user.getUid()),125, TimeUnit.MINUTES);

        System.out.println("+++++--------");

        return hashMap;


    }


    /**
     * 考试测试
     * @param paperId
     * @return
     */
    @Override
    public Map selectPaper(Integer paperId) {
        Paper paper = paperDao.selectByPrimaryKey(paperId);
        ArrayList<SelectQuestions> selectList = new ArrayList<>();
        ArrayList<BooleanQuestions> booleanList = new ArrayList<>();
        ArrayList<SketchQuestions> sketchList = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        List<QuestionsPaper> list = questionsPaperDao.selectByPaperId(paperId);
        for(QuestionsPaper q : list){
            String s = q.getQuestionType();
            if(s.trim().equals("1")){
                SelectQuestions selectQuestions = selectQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                selectList.add(selectQuestions);
            }else if(s.trim().equals("2")){
                BooleanQuestions booleanQuestions = booleanQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                booleanList.add(booleanQuestions);
            }else {
                SketchQuestions sketchQuestions = sketchQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                sketchList.add(sketchQuestions);
            }
        }
        hashMap.put("paper",paper);
        hashMap.put("selectList",selectList);
        hashMap.put("booleanList",booleanList);
        hashMap.put("sketchList",sketchList);
        return hashMap;
    }

    @Override
    public List<Paper> findAvgScore(Integer typeId) {
        List<Paper> avgScoreList = paperDao.findAvgScore(typeId);
        return avgScoreList;
    }


    /**
     * 考试的接口
     * @param paperId
     * @return
     */
    /*@Override
    public Map selectPaper(Integer paperId) {
        Subject subject = SecurityUtils.getSubject();
        String username =(String) subject.getPrincipal();
        User user = userDao.findUserByName(username);
        HashMap map = (HashMap)myRedisTemplate.opsForValue().get(String.valueOf(user.getUid()));
        if(map!=null){
            //判断是否正在进行
            Paper paper1 = (Paper)map.get("paper");
            if(!paper1.getPaperId().equals(paperId)){
                throw new RuntimeException("请返回考试");
            }
            return map;
        }
        Paper paper = paperDao.selectByPrimaryKey(paperId);
        ArrayList<SelectQuestions> selectList = new ArrayList<>();
        ArrayList<BooleanQuestions> booleanList = new ArrayList<>();
        ArrayList<SketchQuestions> sketchList = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        List<QuestionsPaper> list = questionsPaperDao.selectByPaperId(paperId);
        for(QuestionsPaper q : list){
            String s = q.getQuestionType();
            if(s.trim().equals("1")){
                SelectQuestions selectQuestions = selectQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                selectQuestions.setSelectAnswer(null);
                selectList.add(selectQuestions);
            }else if(s.trim().equals("2")){
                BooleanQuestions booleanQuestions = booleanQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                booleanQuestions.setBooAnswer(null);
                booleanList.add(booleanQuestions);
            }else {
                SketchQuestions sketchQuestions = sketchQuestionsDao.selectByPrimaryKey(q.getQuestionId());
                sketchQuestions.setSkeAnswer(null);
                sketchList.add(sketchQuestions);
            }
        }
        hashMap.put("paper",paper);
        hashMap.put("selectList",selectList);
        hashMap.put("booleanList",booleanList);
        hashMap.put("sketchList",sketchList);
        myRedisTemplate.opsForValue().set(String.valueOf(user.getUid()),hashMap);
        myRedisTemplate.expire(String.valueOf(user.getUid()),125, TimeUnit.MINUTES);
        return hashMap;
    }*/



}
