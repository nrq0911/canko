package com.canko.mapper;

import com.canko.domain.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface OrderMapper {

    GoodsOrder getOrderById(@Param("id") int id);

    GoodsOrder getOrderByDisplayId(@Param("displayId") String displayId);

    GoodsOrder getOrderBy(@Param("str") String searchString);

    void addOrder(GoodsOrder order);

    void updateOrder(GoodsOrder order);

    void updateOrderStatus(@Param("id") int id,@Param("status") int status);

    void updateOrderInformation(@Param("id") int id,@Param("information") String information);

}
