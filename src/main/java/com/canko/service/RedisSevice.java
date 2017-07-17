package com.canko.service;

/**
 * Created by nrq on 2017/7/17.
 */
public interface RedisSevice {

    <T> boolean set(String key,T value);

    <T> boolean set(String key,T value,long liveTime);

    <T> T get(String key,Class<T> clazz);

}
