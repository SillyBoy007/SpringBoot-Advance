package com.wang.cache.controller;

import com.wang.cache.bean.Department;
import com.wang.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepartmentController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/dept/{id}")
    @ResponseBody
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDept(id);
    }
}
