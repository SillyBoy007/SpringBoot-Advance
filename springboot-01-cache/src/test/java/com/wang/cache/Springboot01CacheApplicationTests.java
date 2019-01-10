package com.wang.cache;

import com.wang.cache.bean.Employee;
import com.wang.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;  //spring专门抽取出来用来简化字符串操作的
    @Autowired
    RedisTemplate redisTemplate;  //key和value都是对象的
    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;
    @Test
    public void contextLoads() {

        Employee employeeById = employeeMapper.getEmployeeById(1);
        System.out.println(employeeById);
    }

    /**
     * Redis常用的数据类型
     * 1.String(字符串)
     *      opsForValue()
     *
     * 2.List(列表)
     *      opsForList()
     *
     * 3.Set(集合)
     *      opsForSet()
     *
     * 4.Hash(散列)
     *      opsForHash()
     *
     * 5.ZSet(有序集合)
     *      opsForZSet
     */
    @Test
    public void testStringRedis() {
        //redis保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");

        //读取数据
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","3");
        String mylist = stringRedisTemplate.opsForList().leftPop("mylist"); //删除并查询最顶层的list数据
        System.out.println(mylist);
    }
    @Test
    public void testRedis() {
        /**
         * 注意若实体类对象没有被序列化(实现Serializable)，则会报错，无法存入redis里
         * 默认保存对象，使用jdk序列化机制,序列化后数据才能保存到redis中
         */


        /**
         * 解决方式
         * 1.将数据以json形式保存，将对象转为json
         * 2.改变默认的序列化规则
         * 默认使用jdk的序列化器，切换使用json的序列化器即可解决序列化问题
         */
        employeeRedisTemplate.opsForValue().set("emp-02",employeeMapper.getEmployeeById(2));
    }

}

