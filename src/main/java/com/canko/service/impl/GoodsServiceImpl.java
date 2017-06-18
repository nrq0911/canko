package com.canko.service.impl;

import com.canko.domain.Goods;
import com.canko.mapper.GoodsMapper;
import com.canko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
