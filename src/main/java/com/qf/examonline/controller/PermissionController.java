package com.qf.examonline.controller;

import com.github.pagehelper.Page;
import com.qf.examonline.common.JsonBean;
import com.qf.examonline.entity.Permission;
import com.qf.examonline.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/list")
    @ResponseBody
    public Map list(String pname, Integer page, Integer limit){
        List<Permission> permissionList = permissionService.list(pname, page, limit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("count",((Page)permissionList).getTotal());
        map.put("data",permissionList);
        return map;
    }
    @GetMapping("/delete")
    @ResponseBody
    public JsonBean delete(Integer pid){
        permissionService.deleteByPrimaryKey(pid);
        return new JsonBean(0,null);
    }
    @GetMapping("/edit")
    @ResponseBody
    public JsonBean edit(Permission permission){
        permissionService.updateByPrimaryKey(permission);
        return new JsonBean(0,null);
    }
    @GetMapping("/add")
    @ResponseBody
    public JsonBean add(Permission permission){
        permissionService.insert(permission);
        return new JsonBean(0,null);
    }
    @GetMapping("/selectById")
    @ResponseBody
    public JsonBean selectById(Integer pid){
        permissionService.selectByPrimaryKey(pid);
        return new JsonBean(0,null);
    }

}
