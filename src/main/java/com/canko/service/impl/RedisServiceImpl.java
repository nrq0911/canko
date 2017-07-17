package com.canko.service.impl;

import com.alibaba.fastjson.JSON;
import com.canko.service.RedisSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by nrq on 2017/7/17.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisSevice {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private transient final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String REDIS_CODE = "UTF-8";

    @Override
    public <T> boolean set(String key, T value) {
        return set(key,value,-1);
    }

    @Override
    public <T> boolean set(final String key, final T value, final long liveTime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try{
                    String json = JSON.toJSONString(value);
                    connection.set(key.getBytes(REDIS_CODE),json.getBytes(REDIS_CODE));
                    if(liveTime > 100){
                        connection.expire(key.getBytes(REDIS_CODE),liveTime);
                    }
                    return true;
                }catch (Exception e){
                    logger.info("Set Redis Key:{} error." + e, key);
                    return false;
                }
            }
        });
    }

    @Override
    public <T> T get(final String key,final Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                try{
                    String value = new String(connection.get(key.getBytes(REDIS_CODE)));
                    return JSON.parseObject(value,clazz);
                }catch (Exception e){
                    logger.info("Redis get Key:{} error." + e,key);
                    return null;
                }
            }
        });
    }
}
