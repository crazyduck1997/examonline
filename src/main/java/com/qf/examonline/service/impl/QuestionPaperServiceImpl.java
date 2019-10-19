package com.qf.examonline.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.*;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.service.QuestionPaperService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuestionPaperServiceImpl implements QuestionPaperService {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    QuestionsPaperDao questionsPaperDao;

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    SketchQuestionsDao sketchQuestionsDao;

    @Autowired
    PaperDao paperDao;


    /**
     * 手动生成试卷
     * @param map
     * @return
     */
    @Override
    public int addQuestions(Map<String, Object> map) {
        Object paper1 = map.get("paper");
        ObjectMapper mapper = new ObjectMapper();
        Paper paper = new Paper();
        try {
            String string = mapper.writeValueAsString(paper1);
            //转换成为JSONObject对象
            JSONObject jsonObj = new JSONObject(string);
            String paperName = jsonObj.getString("paperName");
            int typeId = jsonObj.getInt("typeId");
            paper.setPaperName(paperName);
            paper.setTypeId(typeId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> selectList = (List) map.get("selectList");
        List<Integer> booleanList = (List) map.get("booleanList");
        List<Integer> sketchList = (List) map.get("sketchList");
        if (paper == null) {
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        paperDao.insert(paper);
        if (selectList != null) {
            questionsPaperDao.addQuestions(paper.getPaperId(), selectList, 1);
        }
        if (booleanList != null) {
            questionsPaperDao.addQuestions(paper.getPaperId(), booleanList, 2);
        }
        if (sketchList != null) {
            questionsPaperDao.addQuestions(paper.getPaperId(), sketchList, 3);
        }
        return 1;
    }

    /**
     * 通过科目和题目类型获取题目
     * @param typeId
     * @param questionType
     * @return
     */
    @Override
    public List findQuestions(Integer typeId, Integer questionType) {
        List list = null;
        if (questionType == 1) {
            list = selectQuestionsDao.findSelectQuestions(typeId, questionType);
        } else if (questionType == 2) {
            list = booleanQuestionsDao.findBooleanQuestions(typeId, questionType);
        } else if (questionType == 3) {
            list = sketchQuestionsDao.findSketchQuestions(typeId, questionType);
        }
        return list;
    }


    /**
     * 自动生成试卷
     * @param selectNum
     * @param booleanNum
     * @param sketchNum
     * @param typeId
     * @param paperName
     */
    @Override
    public void insertAutoMakePaper(Integer selectNum, Integer booleanNum, Integer sketchNum, Integer typeId, String paperName, Date beginTime, Date endTime) {
        Paper paper = new Paper();
        paper.setPaperName(paperName);
        paper.setTypeId(typeId);
        paper.setBeginTime(beginTime);
        paper.setEndTime(endTime);
        paperDao.insert(paper);
        if(selectNum!=0 && selectNum!=null){
            List<SelectQuestions> selectQuestionsList = selectQuestionsDao.findSelectQuestionsByRandom(typeId, selectNum);
            if(selectQuestionsList.size()<selectNum){
                throw new RuntimeException("题库中选择题数量不足，请添加");
            }
            List<Integer> selectIds = new ArrayList<>();
            for(SelectQuestions s : selectQuestionsList){
                selectIds.add(s.getSqId());
            }
            questionsPaperDao.addQuestions(paper.getPaperId(),selectIds,1);
        }
        if(booleanNum!=0 && booleanNum!=null){
            List<BooleanQuestions> booleanQuestionsList = booleanQuestionsDao.findBooleanQuestionsByRandom(typeId, booleanNum);
            if(booleanQuestionsList.size()<booleanNum){
                throw new RuntimeException("题库中判断题数量不足，请添加");
            }
            List<Integer> booleanIds = new ArrayList<>();
            for(BooleanQuestions s : booleanQuestionsList){
                booleanIds.add(s.getBooId());
            }
            questionsPaperDao.addQuestions(paper.getPaperId(),booleanIds,2);
        }
        if(sketchNum!=0 && sketchNum!=null){
            List<SketchQuestions> sketchQuestionsList = sketchQuestionsDao.findSketchQuestionsByRandom(typeId, sketchNum);
            if(sketchQuestionsList.size()<sketchNum){
                throw new RuntimeException("题库中简答题数量不足，请添加");
            }
            List<Integer> sketchIds = new ArrayList<>();
            for(SketchQuestions s : sketchQuestionsList){
                sketchIds.add(s.getSkeId());
            }
            questionsPaperDao.addQuestions(paper.getPaperId(),sketchIds,3);
        }
    }


}
