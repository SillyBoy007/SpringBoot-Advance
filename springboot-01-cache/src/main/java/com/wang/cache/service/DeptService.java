package com.wang.cache.service;

import com.wang.cache.bean.Department;
import com.wang.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("myCacheManager")
    @Autowired
    RedisCacheManager myCacheManager;

 /*   @Cacheable(cacheNames = "dept",key = "#id")
    public Department getDept(Integer id){
        return departmentMapper.getDepartmentById(id);
    }*/
    public Department getDept(Integer id){
        Department department = departmentMapper.getDepartmentById(id);
        Cache dept = myCacheManager.getCache("dept"); //获取某个缓存
        dept.put("dept:1",department);

        return departmentMapper.getDepartmentById(id);
    }
}
