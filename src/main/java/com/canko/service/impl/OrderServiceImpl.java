package com.canko.service.impl;

import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;
import com.canko.mapper.MemberMapper;
import com.canko.mapper.OrderMapper;
import com.canko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nrq on 2017/6/17.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public GoodsOrder getOrderByDisplayId(String displayId) {
        return orderMapper.getOrderByDisplayId(displayId);
    }

    @Override
    public List<GoodsOrder> getGoodsOrderList() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addOrder(GoodsOrder order) {
        orderMapper.addOrder(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public void confirmOrder(GoodsOrder order) {
        memberMapper.addMember(order.getMember());
        order.setMemberId(order.getMember().getId());
        orderMapper.updateOrder(order);
    }

    @Override
    public GoodsOrder getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public Member getMemberById(int id) {
        return memberMapper.getMemberById(id);
    }

    @Override
    @Transactional
    public void updateOrder(GoodsOrder order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public GoodsOrder getOrderBy(String searchString) {
        return orderMapper.getOrderBy(searchString);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOrderState(int id,int status) {
        orderMapper.updateOrderStatus(id,status);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOrderInformation(int id ,String information) {
        orderMapper.updateOrderInformation(id,information);
    }
}
