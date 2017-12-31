package com.canko.service.impl;

import com.canko.common.CalendarUtil;
import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;
import com.canko.mapper.GoodsMapper;
import com.canko.mapper.MemberMapper;
import com.canko.mapper.OrderMapper;
import com.canko.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private GoodsMapper goodsMapper;

    private transient final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public GoodsOrder getOrderByDisplayId(String displayId) {
        return orderMapper.getOrderByDisplayId(displayId);
    }

    @Override
    public int countGoodsOrderBy(String memberName,String tel,String displayId,String startTime,String endTime){
        Date startDate = CalendarUtil.parseDate(startTime);
        Date endDate = CalendarUtil.parseDate(endTime);
        return orderMapper.countOrderBy(memberName,tel,displayId,startDate,endDate);
    }

    @Override
    public List<GoodsOrder> getGoodsOrderList(String memberName,String tel,String displayId,
                                              String startTime,String endTime,int page, int rows) {
        Date startDate = CalendarUtil.parseDate(startTime);
        Date endDate = CalendarUtil.parseDate(endTime);
        List<GoodsOrder> list = null ;
        try{
            int offset = (page -1)*rows;
           list =  orderMapper.getOrderListBy(memberName,tel,displayId,startDate,endDate, offset, rows);
           for(GoodsOrder order : list){
               order.setMember(memberMapper.getMemberById(order.getMemberId()));
               order.setGoods(goodsMapper.getGoodsById(order.getGoodsId()));
           }
        }catch (Exception e){
            logger.info("query order List error" + e);
        }
        return list;
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
