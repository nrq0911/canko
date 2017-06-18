package com.canko.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface OrderMapper {

    Order getOrderById(@Param("id") int id);


}
