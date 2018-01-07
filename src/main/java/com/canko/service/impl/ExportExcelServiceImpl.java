package com.canko.service.impl;

import com.canko.common.CalendarUtil;
import com.canko.common.ExcelData;
import com.canko.common.ExportExcelUtils;
import com.canko.domain.Goods;
import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;
import com.canko.domain.enumerate.OrderState;
import com.canko.mapper.GoodsMapper;
import com.canko.mapper.MemberMapper;
import com.canko.mapper.OrderMapper;
import com.canko.service.ExportExcelService;
import com.canko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void exportOrderList(HttpServletResponse response, String fileName,  String startTime, String endTime) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("订单列表");
        data.setTitles(getOrderTitle());
        data.setRows(getOrderList(startTime, endTime));
        ExportExcelUtils.exportExcel(response, fileName, data);
    }

    private List<String> getOrderTitle(){
        List<String> titles = new ArrayList();
        titles.add("订单号");
        titles.add("商品ID");
        titles.add("商品名称");
        titles.add("收件人信息");
        titles.add("订单金额");
        titles.add("订单描述");
        titles.add("商品数量");
        titles.add("创建时间");
        titles.add("订单状态");
        titles.add("物流信息");
        return titles;
    }

    private List<List<Object>> getOrderList(String startTime, String endTime){
        List<List<Object>> rows = new ArrayList();
        Date startDate = CalendarUtil.parseDate(startTime);
        Date endDate = CalendarUtil.parseDate(endTime);
        List<GoodsOrder> orderList =  orderMapper.getOrderListBy(null,null,null, startDate, endDate, 0, -1);
        for(GoodsOrder order : orderList){
            List<Object> row = new ArrayList();
            row.add(order.getDisplayId());
            Goods goods = goodsMapper.getGoodsById(order.getGoodsId());
            row.add(order.getGoodsId());
            row.add(goods.getName());
            Member member = memberMapper.getMemberById(order.getMemberId());
            row.add(member);
            row.add(order.getOrderPrice());
            row.add(order.getGoodsSpec());
            row.add(order.getNum());
            row.add(order.getCreateTime());
            row.add(OrderState.getOrderState(order.getOrderState()).getDescription());
            row.add(order.getOrderInformation() ==  null ? "" : order.getOrderInformation());
            rows.add(row);
        }
        return rows;
    }

}
