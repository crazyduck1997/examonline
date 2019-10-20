package com.qf.examonline.dao;

import com.qf.examonline.entity.Paper;
import com.qf.examonline.entity.QuestionVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PaperDao {
    int deleteByPrimaryKey(Integer paperId);

    int insert(Paper record);

    Paper selectByPrimaryKey(Integer paperId);

    List<Paper> selectAll();

    int updateByPrimaryKey(Paper record);

    List<QuestionVo> selectVo();

    List<QuestionVo> selectAllTest(@Param("paperName")String paperName,@Param("typeId")Integer typeId);


    List<QuestionVo> selectMyPapers(@Param("uid")Integer uid,@Param("paperName")String paperName,@Param("typeId")Integer typeId);


    // 更新生成的静态页面的url
    public void updateUrlById(@Param("typeId") Integer typeId, @Param("url") String url);

    // 查询平均分
    public List<Paper> findAvgScore(Integer typeId);
    //删除一个种类时，修改其相关的试卷的状态
    public void updatePaperStatus(Integer typeId);
}