package com.wang.cache.service;

import com.wang.cache.bean.Employee;
import com.wang.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 *
 */
@CacheConfig(cacheNames = "emp",cacheManager = "myCacheManager") //抽取缓存的公共配置
@Service
public class EmpService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后要用相同的数据，直接从缓存中读取，不用调用方法了
     * CacheManager管理多个Cache组件的，对缓存的真正操作在Cache组件中，每一个缓存组件有自己唯一一个名字
     * 几个属性:
     *        cacheNames/value:指定缓存的名字
     *        key:缓存数据使用的key,可以用它来指定,默认使用方法参数的值 1-方法的返回值
     *            编写SpEL #id 参数id的值  #a0 #p0 #root.args[0]
     *        keyGenerator:key的生成器,可以自己指定key的生成器的组件id
     *        （key与keyGenerator二选一使用）
     *        cacheManager:指定缓存管理器,或者指定缓存解析器(二选一)
     *        condition:自定符合条件的情况下才缓存
     *        unless:否定缓存,当unless指定的条件为true,方法的返回值不会缓存，可以获取到结果进行判断(#result可以取出结果)
     *        sync:是否使用异步模式
     *
     * 原理:
     *     1、自动配置类:CacheAutoConfiguration
     *     2、缓存的配置类:
     *     "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     *     "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     *      "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     *      3、默认生效的配置类
     *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *      4、给容器中注册了一个CacheManager:ConcurrentMapCacheManager
     *      5、可以获取和创建ConcurrentMapCache类型的缓存组件,他的作用是将数据保存在ConcurrentMap中
     *
     *
     * 系统默认是使用ConcurrentMapCacheManager，然后获取和创建ConcurrentMapCache类型的缓存组件，再将数据保存在ConcurrentMap中
     * 开发中使用缓存中间件:redis,memcached,ehcache
     *
     * 整合redis
     *1.redis环境搭建
     *2.引入redis的starter
     *
     *
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}/*,keyGenerator = "myKeyGenerator"*//*,key = "#id"*//*,condition = "#id>1"*//*,unless = "#a0==2"*/)
    public Employee getEmp(Integer id){
       return employeeMapper.getEmployeeById(id);
    }

    /**
     * @Cacheable调用时机是在方法之前调用，若缓存中有了则调用缓存中的方法，若没有则调用该方法
     * @CachePut
     * 运行时机:
     * 1.先调用目标方法
     * 2.然后将目标方法的结果缓存起来
     *
     * 测试缓存:
     * 1.查询1号员工,查到的结果会放到缓存中
     * 2.查询之前的结果
     * 3.更新1号员工
     * 4.此时查询员工发现查询的是更新前的数据，由于key默认是传入的employee对象，而查询的key是员工的id，1号员工没有更新缓存
     * 解决:
     * 1.key="#employee.id",使用传入的参数的员工id
     * 2.key="#result.id",使用返回后的id(@Cacheable无法用result,因为是在方法运行之前调用的)
     * 这样就能实现同时更新数据以及缓存。
     * @param employee
     * @return
     */
    @CachePut(value = "emp",key="#result.id")
    public Employee updateEmp(Employee employee){
         System.out.println("updateEmp:"+employee);
         employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * @CacheEvict 缓存清除
     * key :制定要清除的数据，默认传入的参数为key
     * allEntries = true,指定这个缓存中所有数据
     * beforeInvocation:缓存的清除默认是否在方法执行之前执行,默认缓存清除操作是在方法运行之后执行
     * beforeInvocation = true 表示清楚在方法执行之前执行，无论方法是否异常,都会执行清除
     */
    @CacheEvict(value = "emp",key="#id"/*,allEntries = true*//*,beforeInvocation = true*/)
    public void deleteEmp(Integer id){
        System.out.println("delEmp"+id);
        //int i = 1/0;
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key="#lastName")
            },
            put = {
                    @CachePut(value = "emp",key="#result.id"), //将返回的员工id也放到缓存中
                    @CachePut(value = "emp",key="#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
