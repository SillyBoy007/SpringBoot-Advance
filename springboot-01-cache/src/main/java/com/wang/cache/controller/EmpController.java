package com.wang.cache.controller;

import com.wang.cache.bean.Employee;
import com.wang.cache.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping("/emp/{id}")
    @ResponseBody
    public Employee getEmp(@PathVariable Integer id){
        return empService.getEmp(id);
    }
    @GetMapping("/emp")
    @ResponseBody
    public Employee updateEmp(Employee employee){
        return empService.updateEmp(employee);
    }

    @GetMapping("/delEmp")
    @ResponseBody
    public String delEmp(Integer id){
        empService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    @ResponseBody
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return empService.getEmpByLastName(lastName);
    }
}
