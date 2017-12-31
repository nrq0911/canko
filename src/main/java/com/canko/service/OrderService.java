package com.canko.service;

import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;

import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
public interface OrderService {

    void addOrder(GoodsOrder order);

    void confirmOrder(GoodsOrder order);

    GoodsOrder getOrderById(int id);

    GoodsOrder getOrderByDisplayId(String displayId);

    GoodsOrder getOrderBy(String searchString);

    Member getMemberById(int id);

    void updateOrder(GoodsOrder order);

    void updateOrderState(int id,int status);

    void updateOrderInformation(int id ,String information);

    int countGoodsOrderBy(String memberName,String tel,String displayId,String startTime,String endTime);

    List<GoodsOrder> getGoodsOrderList(String memberName,String tel,String displayId,String startTime,String endTime,int page, int rows);

}
