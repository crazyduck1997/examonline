package com.qf.examonline.dao;

import com.qf.examonline.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeDao {

    //查询所有试卷类别
    public List<Type> findAllType();
    //添加新的试卷类别
    public void addNewPaperType(String typeName);
    //删除一个试卷类别
    public void deleteOnePaperType(Integer typeId);
    //修改试卷类别的名称(根据试卷的id来修改)
    public void updatePaperTypeName(Type type);

}
