package com.canko.service.impl;

import com.canko.domain.Goods;
import com.canko.mapper.GoodsMapper;
import com.canko.service.GoodsService;
import com.canko.service.RedisSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisSevice redisSevice;

    @Override
    public Goods getGoodsById(int id) {
        String key = createGoodsKey(id);
        Goods goods = redisSevice.get(key,Goods.class);
        if(goods == null){
            goods = goodsMapper.getGoodsById(id);
            redisSevice.set(key,goods);
        }
        return goods;
    }

    @Override
    public Goods getGoodsByDisplayId(String displayId) {
        String key = "goods." + displayId;
        Goods goods = redisSevice.get(key,Goods.class);
        if(goods == null){
            goods = goodsMapper.getGoodsByDisplayId(displayId);
            redisSevice.set(key,goods);
        }
        return goods;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addGoods(Goods goods) {
        goodsMapper.addGoods(goods);
        return goods.getId();
    }

    @Override
    public List<Goods> getGoodsListByName(String goodsName, int page, int rows) {
        return goodsMapper.getGoodsByName(goodsName, page, rows);
    }

    @Override
    public int countGoodsByName(String goodsName){
        return goodsMapper.countGoodsByName(goodsName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
        String key = createGoodsKey(goods.getId());
        redisSevice.set(key,goods);
    }

    private String createGoodsKey(int id){
        return "online.goods."+ id;
    }

}
