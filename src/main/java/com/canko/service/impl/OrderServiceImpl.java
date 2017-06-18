package com.canko.service.impl;

import com.canko.mapper.OrderMapper;
import com.canko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nrq on 2017/6/17.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

}
