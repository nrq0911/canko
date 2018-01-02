package com.canko.controller;

import com.canko.common.RandomStringUtils;
import com.canko.domain.Goods;
import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;
import com.canko.domain.enumerate.OrderState;
import com.canko.model.OrderModel;
import com.canko.model.OrderVO;
import com.canko.model.ProductVO;
import com.canko.model.SpecModel;
import com.canko.service.GoodsService;
import com.canko.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProductController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    private final static Logger log = Logger.getLogger(ProductController.class);

    /**
     * 查看商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/product/{id}")
    public Map<String, Object> viewProduct(@PathVariable("id") String displayId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Goods goods = goodsService.getGoodsByDisplayId(displayId);
            if (goods == null) {
                response.put("code", "404");
                response.put("msg", "商品不存在！");
            } else {
                response.put("code", "200");
                response.put("msg", "success");
                response.put("result", new ProductVO(goods));
            }
        } catch (Exception e) {
            log.error("Get goods info error displayId= " + displayId + "due to " + e);
            response.put("code", "500");
            response.put("msg", "Server error!");
        }
        return response;
    }

    /**
     * 填写规格
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/choseSpec")
    public Map<String, Object> choseSpec(SpecModel model) {
        Map<String, Object> response = new LinkedHashMap<>();
        if(StringUtils.isAnyBlank(model.getGoodsId(), model.getSpec()) ){
            response.put("code", "400");
            response.put("msg", "商品ID和規格不能為空！");
            return response;
        }
        if(model.getNum() < 1 || model.getMoney() < 100 ){
            response.put("code", "400");
            response.put("msg", "商品數量或金額錯誤！");
            return response;
        }
        try {
            Goods goods = goodsService.getGoodsByDisplayId(model.getGoodsId());
            GoodsOrder order = new GoodsOrder();
            order.setCreateTime(new Date());
            order.setGoods(goods);
            order.setGoodsSpec(model.getSpec());
            String displayId = System.currentTimeMillis()/1000 + RandomStringUtils.getRandomNum(6);
            order.setDisplayId(displayId);
            order.setNum(model.getNum());
            order.setOrderState(OrderState.placeOrder.getCode());
            order.setOrderCurrency(1);
            order.setOrderPrice(model.getNum());
            orderService.addOrder(order);
            response.put("code", "200");
            response.put("msg", "success");
            response.put("orderId", displayId);
        } catch (Exception e) {
            log.error("chose goods spec error specModel={" + model + "} due to " + e);
            response.put("code", "500");
            response.put("msg", "Server error!");
        }
        return response;
    }

    /**
     * 下单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order")
    public Map<String, Object> order(OrderModel model) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            response.put("code", "400");
            GoodsOrder order = orderService.getOrderByDisplayId(model.getOrderId());
            if(order == null){
                response.put("code", "404");
                response.put("msg","訂單不存在，請刷新！");
                return  response;
            }

            if(model.getNum() < 1){
                response.put("msg","商品數量不能小於1！");
                return  response;
            }

            if(model.getNum() != order.getNum()){
                order.setNum(model.getNum());
                order.setOrderPrice(model.getMoney());
            }

            if(StringUtils.isBlank(model.getTel())){
                response.put("msg","電話號碼錯誤，請重新填寫！");
                return  response;
            }

            if(StringUtils.isBlank(model.getAddress())){
                response.put("msg","詳細地址錯誤，請重新填寫！");
                return  response;
            }


            if(StringUtils.isBlank(model.getName())){
                response.put("msg","收件人姓名不能爲空，請重新填寫！");
                return  response;
            }

            if(StringUtils.isNotBlank(model.getEmail())){
                String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(model.getEmail());
                if(!matcher.matches()){
                    response.put("msg","請輸入正確的郵箱，請重新填寫！");
                    return response;
                }
            }

            Member member = new Member();
            member.setRegion(model.getPostcode());
            member.setMobilephone(model.getTel());
            member.setAddress(model.getAddress());
            member.setEmail(model.getEmail());
            member.setName(model.getName());
            member.setRemark(model.getRemark());

            order.setMember(member);
            order.setOrderState(OrderState.confirmOrder.getCode());
            orderService.confirmOrder(order);
            response.put("code", "200");
            response.put("msg", "success");
            response.put("info", new OrderVO(order));
        } catch (Exception e) {
            log.error("Get goods info error model={"+ model +"} due to " + e);
            response.put("code", "500");
            response.put("msg", "Server error!");
        }
        return response;
    }

}
