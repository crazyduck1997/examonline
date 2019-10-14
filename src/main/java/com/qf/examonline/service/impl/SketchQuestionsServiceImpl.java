package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.dao.SketchQuestionsDao;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.service.SketchQuestionsService;
import com.qf.examonline.utils.MultioartFileUp;
import com.qf.examonline.utils.MultipartFileToFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class SketchQuestionsServiceImpl implements SketchQuestionsService {




   @Autowired
    SketchQuestionsDao sketchQuestionsDao;

   @Autowired
    CodeMsg codeMsg;

    @Override
    public int insertSketchQuestions(MultipartFile file) {
        File dest = MultioartFileUp.upLoad(file, codeMsg.getTempUrl());
        List<SketchQuestions> list = MultipartFileToFileUtil.change(dest, SketchQuestions.class);
        int insert = sketchQuestionsDao.insert(list);
        if(insert==0) {
            return ErrorCode.ERROR;
        }
        return ErrorCode.SUCCESS;
    }
}
