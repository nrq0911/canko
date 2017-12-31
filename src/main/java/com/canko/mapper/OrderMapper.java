package com.canko.mapper;

import com.canko.domain.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface OrderMapper {

    GoodsOrder getOrderById(@Param("id") int id);

    GoodsOrder getOrderByDisplayId(@Param("displayId") String displayId);

    GoodsOrder getOrderBy(@Param("str") String searchString);

    int countOrderBy(@Param("memberName")String memberName,
                     @Param("tel")String tel,
                     @Param("displayId")String displayId,
                     @Param("startTime")Date startDate,
                     @Param("endTime")Date endDate);

    List<GoodsOrder> getOrderListBy(@Param("memberName")String memberName,
                                    @Param("tel")String tel,
                                    @Param("displayId")String displayId,
                                    @Param("startTime")Date startDate,
                                    @Param("endTime")Date endDate,
                                    @Param("offset")int offset,
                                    @Param("rows")int rows);

    void addOrder(GoodsOrder order);

    void updateOrder(GoodsOrder order);

    void updateOrderStatus(@Param("id") int id,@Param("status") int status);

    void updateOrderInformation(@Param("id") int id,@Param("information") String information);

}
