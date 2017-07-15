package com.canko.service.impl;

import com.canko.domain.Goods;
import com.canko.mapper.GoodsMapper;
import com.canko.service.GoodsService;
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

    @Override
    public Goods getGoodsById(int id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addGoods(Goods goods) {
        goodsMapper.addGoods(goods);
        return goods.getId();
    }

    @Override
    public List<Goods> getGoodsListByName(String goodsName) {
        return goodsMapper.getGoodsByName(goodsName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }
}
