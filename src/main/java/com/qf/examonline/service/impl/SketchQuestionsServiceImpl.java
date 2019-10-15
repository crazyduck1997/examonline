package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.SketchQuestionsDao;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.service.SketchQuestionsService;
import com.qf.examonline.utils.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class SketchQuestionsServiceImpl implements SketchQuestionsService {


    @Autowired
    SketchQuestionsDao sketchQuestionsDao;

    @Autowired
    CodeMsg codeMsg;

    @Override
    public int insertSketchQuestions(MultipartFile file) {
        List<SketchQuestions> list = null;
        try{
            InputStream inputStream = file.getInputStream();
            list = ImportExcelUtil.importExcel(inputStream, SketchQuestions.class);
        }catch (Exception e){

        }
        int insert = sketchQuestionsDao.insert(list);
        if (insert == 0) {
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;
    }

    @Override
    public int addSketchQuestion(List<SketchQuestions> list) {
        if(list==null){
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        int insert = sketchQuestionsDao.insert(list);
        if (insert == 0) {
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;
    }


}
