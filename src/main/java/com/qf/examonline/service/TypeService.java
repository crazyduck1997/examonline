package com.qf.examonline.service;

import com.github.pagehelper.PageInfo;
import com.qf.examonline.entity.Type;

import java.util.List;

public interface TypeService {
    //查询所有试卷类别
    public List<Type> findAllType(String typeName,Integer page, Integer limit);
    //添加新的试卷类别
    public void addNewPaperType(String typeName);
    //删除一个试卷类别
    public void deleteOnePaperType(Integer typeId);
    //修改试卷类别的名称(根据试卷的id来修改)
    public void updatePaperTypeName(Type type);

    //通过名字查询type信息
    public Type findOneTypeByTypeId(Integer typeId);

    //查询总的信息数量
    public Integer findTypeCount(String typeName);

    //查询所有的试卷类别，不开启分页
    List<Type> selectAllType();
}
