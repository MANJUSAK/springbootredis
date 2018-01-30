package com.manjusaka.redis.springbootredis.controller;

import com.manjusaka.redis.springbootredis.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * description:
 * ===>
 *
 * @author manjusaka[manjusakachn@gmail.com] Created on 2018-01-29 14:33
 */
@RestController
public class RedisController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/set")
    public String set(User user) {
        ValueOperations<String, Object> vo = this.redisTemplate.opsForValue();
        vo.set(user.getUserName(), user);
        return "success";
    }

    @RequestMapping("/set/list")
    public String setList(User user) {
        long time = System.currentTimeMillis();
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; ++i) {
            users.add(user);
        }
        //JedisConnectionFactory jcf = (JedisConnectionFactory) this.redisTemplate.getConnectionFactory();
        //jcf.setDatabase(1);
        //this.redisTemplate.setConnectionFactory(jcf);
        SetOperations<String, Object> vo = this.redisTemplate.opsForSet();
        vo.add("list", users);
        long endtime = System.currentTimeMillis() - time;
        return "success!用时" + endtime + "ms";
    }

    @RequestMapping("/get")
    public <T> T get(String uName) {
        ValueOperations<String, Object> vo = this.redisTemplate.opsForValue();
        User data = (User) vo.get(uName);
        if (data != null) {
            return (T) data;
        }
        return (T) "No Find Data";
    }

    @RequestMapping("/get/list")
    public <T> T getList(String uName) {
        long time = System.currentTimeMillis();
        SetOperations<String, Object> vo = this.redisTemplate.opsForSet();
        Set<Object> data = vo.members(uName);
        System.out.println("用时" + (System.currentTimeMillis() - time) + "ms");
        if (data.size() > 0) {
            return (T) data;
        }
        return (T) "No Find Data";
    }
}
