package com.canko.mapper;

import com.canko.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface GoodsMapper {

    Goods getGoodsById(@Param("id")int id);

    Goods getGoodsByDisplayId(@Param("displayId") String displayId);

    List<Goods> getGoodsByName(@Param("name") String goodsName, @Param("page") int page, @Param("rows") int rows);

    int countGoodsByName(@Param("name") String goodsName);

    void addGoods(Goods goods);

    void updateGoods(Goods goods);

}
