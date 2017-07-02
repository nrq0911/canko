package com.canko.mapper;

import com.canko.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface GoodsMapper {

    Goods getGoodsById(@Param("id")int id);

    void addGoods(Goods goods);

    void updateGoods(Goods goods);

}
