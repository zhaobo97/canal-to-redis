package com.zhaobo.canal.handler;

import com.zhaobo.canal.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import javax.annotation.Resource;

@Slf4j
@CanalTable("user")
@Component
public class UserHandler  implements EntryHandler<UserModel> {

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void insert(UserModel user) {
        log.info("[新增]"+user.toString());
        redisTemplate.opsForValue().set("user:"+user.getId(), user);
        Object o = redisTemplate.opsForValue().get("user:"+user.getId());
        log.info("新增成功:{}", o);
    }

    /**
     * before中只有 where 条件的字段，其余的全是null
     * @param before
     * @param after
     */
    @Override
    public void update(UserModel before, UserModel after) {
        log.info("[更新]"+after.toString());
        redisTemplate.opsForValue().set("user:"+after.getId(), after);
        Object o = redisTemplate.opsForValue().get("user:"+after.getId());
        log.info("更新成功:{}", o);
    }

    @Override
    public void delete(UserModel user) {
        log.info("[删除]"+user.getId());
        redisTemplate.delete("user:"+user.getId());
        Object o = redisTemplate.opsForValue().get("user:"+user.getId());
        if (o == null) log.info("删除成功,userId:{}", user.getId());
    }
}

