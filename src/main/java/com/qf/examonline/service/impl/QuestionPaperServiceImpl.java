package com.qf.examonline.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.PaperDao;
import com.qf.examonline.dao.QuestionsPaperDao;
import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.service.QuestionPaperService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionPaperServiceImpl implements QuestionPaperService {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    QuestionsPaperDao questionsPaperDao;

    @Autowired
    PaperDao paperDao;


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
            System.out.println(paperName + "--" + typeId);
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
            for (Integer s : selectList) {
                System.out.println(s);
            }
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
}
