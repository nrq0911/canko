package com.canko.service;

import com.canko.domain.Goods;

import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
public interface GoodsService {

    Goods getGoodsById(int id);

    Goods getGoodsByDisplayId(String displayId);

    int addGoods(Goods goods);

    void updateGoods(Goods goods);

    List<Goods> getGoodsListByName(String goodsName, int page, int rows);

    int countGoodsByName(String goodsName);

}
