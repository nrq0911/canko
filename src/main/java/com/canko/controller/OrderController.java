package com.canko.controller;

import com.canko.domain.Goods;
import com.canko.domain.GoodsOrder;
import com.canko.domain.Member;
import com.canko.domain.enumerate.OrderState;
import com.canko.service.GoodsService;
import com.canko.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nrq on 2017/6/17.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    private transient Logger logger = Logger.getLogger(OrderController.class);

    /**
     * 查询订单
     * */
    @RequestMapping(value = "/order/searchOrder",method = RequestMethod.POST)
    public String searchOrder(Model model, String orderSearch){
        if(StringUtils.isBlank(orderSearch)){
            return "query-result";
        }
        GoodsOrder order = orderService.getOrderBy(orderSearch.trim());
        model.addAttribute("order",order);
        if(order == null){
            return "query-result";
        }
        Member member = orderService.getMemberById(order.getMemberId());
        Goods goods = goodsService.getGoodsById(order.getGoodsId());
        String orderState ;
        if(order.getOrderState() >= OrderState.Shipping.getCode()){
            orderState = "未發貨";
        }else{
            orderState = "發貨中";
        }
        model.addAttribute("orderState",orderState);
        model.addAttribute("member",member);
        model.addAttribute("goods",goods);
        return "query-result";
    }

    /**
     * 用户下单,挑选产品规格
     * */
    @RequestMapping(value="/order/placeOrder/{goodsId}",method = RequestMethod.GET)
    public String placeOrder(@PathVariable String goodsId, Model model){
        int id = 0;
        try{
            id = Integer.valueOf(goodsId);
        }catch (Exception e){
            logger.info(e);
        }
        Goods goods = goodsService.getGoodsById(id);
        if(goods == null){
            return "error_page/404";
        }
        String [] firstLevel = goods.getFirstLevel().split(";");
        if(StringUtils.isNotBlank(goods.getSecondLevel()) && StringUtils.isNotBlank(goods.getSecondLevelName())){
            String [] secondLevel = goods.getSecondLevel().split(";");
            model.addAttribute("secondLevel",secondLevel);
            if(StringUtils.isNotBlank(goods.getThirdLevel()) && StringUtils.isNotBlank(goods.getThirdLevelName())) {
                String[] thirdLevel = goods.getThirdLevel().split(";");
                model.addAttribute("thirdLevel", thirdLevel);
            }
        }
        model.addAttribute("firstLevel",firstLevel);
        model.addAttribute("goods",goods);
        return "order-goods";
    }

    /**
     * 购买产品,填写商品规格
     * */
    @RequestMapping(value="/order/buyProduct",method = RequestMethod.POST)
    public String buyProduct(Model model, HttpServletRequest request,
                             @RequestParam("price") Double price,
                             @RequestParam("spec") String spec,
                             @RequestParam("goodsId") Integer goodsId,
                             @RequestParam("number") Integer number){
        HttpSession session = request.getSession();
        Double sessionPrice = (Double) session.getAttribute("price");
        if(sessionPrice == null){
            sessionPrice = price;
        }else{
            sessionPrice += price;
        }
        Goods goods = goodsService.getGoodsById(goodsId);
        session.setAttribute("price",sessionPrice);
        model.addAttribute("spec",spec);
        model.addAttribute("goods",goods);

        GoodsOrder order = new GoodsOrder();
        order.setCreateTime(new Date());
        order.setGoods(goods);
        order.setGoodsSpec(spec);
        String displayId = System.currentTimeMillis() + getRandomString(6);
        order.setDisplayId(displayId);
        order.setNum(number);
        order.setOrderState(OrderState.placeOrder.getCode());
        order.setOrderCurrency(1);
        order.setOrderPrice(sessionPrice);
        orderService.addOrder(order);
        model.addAttribute("order",order);

        return "order-center";
    }

    /**
     * 确认订单,填写资料
     * */
    @RequestMapping(value="/order/confirm",method = RequestMethod.POST)
    public String confirmOrder(Model model,@RequestParam("id")String displayId,
                               @RequestParam("province") String province,
                               @RequestParam("city") String city,
                               @RequestParam("name")String name,
                               @RequestParam("tel")String tel,
                               @RequestParam("email")String email,
                               @RequestParam("address")String address,
                               @RequestParam(value="remark",defaultValue = "")String remark
                               ){
        GoodsOrder order = orderService.getOrderByDisplayId(displayId);
        if(order == null){
            model.addAttribute("msg","訂單不存在，請刷新！");
            return "order-center";
        }
        Member member = new Member();
        if(StringUtils.isBlank(province) || StringUtils.isBlank(city)){
            model.addAttribute("order",order);
            model.addAttribute("msg","地區填寫錯誤，請重新填寫！");
            return "order-center";
        }
        member.setRegion(province + city);

        if(StringUtils.isBlank(tel)){
            model.addAttribute("order",order);
            model.addAttribute("msg","電話號碼錯誤，請重新填寫！");
            return "order-center";
        }
        member.setMobilephone(tel);

        if(StringUtils.isBlank(address)){
            model.addAttribute("order",order);
            model.addAttribute("msg","詳細地址錯誤，請重新填寫！");
            return "order-center";
        }
        member.setAddress(province + city + address);

        if(StringUtils.isBlank(email) || StringUtils.isBlank(name)){
            model.addAttribute("order",order);
            model.addAttribute("msg","郵箱和收件人姓名不能爲空，請重新填寫！");
            return "order-center";
        }

        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            model.addAttribute("order",order);
            model.addAttribute("msg","請輸入正確的郵箱，請重新填寫！");
            return "order-center";
        }

        member.setEmail(email);
        member.setName(name);
        member.setRemark(remark);

        order.setMember(member);
        order.setOrderState(OrderState.confirmOrder.getCode());
        order.setOrderRemark(remark);
        try{
            orderService.confirmOrder(order);
        }catch (Exception e){
            logger.error("用户确认订单出错" + e);
            model.addAttribute("order",order);
            model.addAttribute("msg","系統繁忙，請稍後重試！");
            return "order-center";
        }
        model.addAttribute("goodsId",order.getGoodsId());
        model.addAttribute("order",order);
        return "confirm-order";
    }

    /**
     * 修改订单
     * */
    @RequestMapping(value = "/order/updateOrder",method = RequestMethod.POST)
    public String updateOrder(@RequestParam("id") String displayId,Model model,
                              @RequestParam(value="number",defaultValue = "1") Integer num){
        GoodsOrder order = orderService.getOrderByDisplayId(displayId);
        if(order == null){
            model.addAttribute("msg","訂單不存在，請刷新！");
            return "order-center";
        }
        Goods goods = goodsService.getGoodsById(order.getGoodsId());
        order.setGoods(goods);
        order.setNum(num);
        order.setOrderPrice(goods.getMarketPrice()*num);
        orderService.updateOrder(order);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "order-center";
    }

    /**
     * 删除订单
     * */
    @RequestMapping(value = "/order/delete/{displayId}",method = RequestMethod.GET)
    public String deleteOrder(Model model,@PathVariable String displayId){
        GoodsOrder order = orderService.getOrderByDisplayId(displayId);
        model.addAttribute("order",new GoodsOrder());
        if(order != null){
            orderService.updateOrderState(order.getId(),OrderState.orderFailure.getCode());
        }
        Goods goods = new Goods();
        goods.setId(1);
        model.addAttribute("goods",goods);
        return "order-center";
    }

    @RequestMapping(value = "/order/orderList",method = RequestMethod.GET)
    public String orderList(){
        return "order/orderList";
    }

    @RequestMapping(value = "/order/orderList",method = RequestMethod.POST)
    public String orderList(Model model,HttpServletRequest request,
                            @RequestParam("name")String memberName,
                            @RequestParam("tel")String tel,
                            @RequestParam("displayId")String displayId){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        List<GoodsOrder> orderList = orderService.getGoodsOrderList(memberName,tel,displayId,startTime,endTime);
        model.addAttribute("orderList",orderList);
        model.addAttribute("displayId",displayId);
        model.addAttribute("name",memberName);
        model.addAttribute("tel",tel);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        return "order/orderList";
    }

    /**
     * 更新订单状态
     * */
    @ResponseBody
    @RequestMapping(value = "/order/update/status",method = RequestMethod.POST)
    public boolean updateStatus(@RequestParam(value="id")int id,
            @RequestParam(value = "status",defaultValue = "0")int status){
        GoodsOrder order = orderService.getOrderById(id);
        if(order == null){
            return false;
        }
        if(status == 0){
            return false;
        }
        orderService.updateOrderState(id,status);
        return true;
    }

    /**
     * 更新物流信息
     * */
    @ResponseBody
    @RequestMapping(value = "/order/update/information",method = RequestMethod.POST)
    public boolean updateInformation(@RequestParam(value="id")int id,
            @RequestParam(value="information")String information){
        GoodsOrder order = orderService.getOrderById(id);
        if(order == null){
            return false;
        }
        if(StringUtils.isBlank(information)){
            return false;
        }
        orderService.updateOrderInformation(id,information);
        return true;
    }

    private String getRandomString(int n){
        char[] numChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer sb = new StringBuffer(n);
        Random random = new Random();
        int m;
        for(int i=0 ; i<n ; i++){
            m = random.nextInt(10);
            sb.append(numChar[m]);
        }
        return sb.toString();
    }

}
