package com.canko.controller;

import com.canko.domain.Goods;
import com.canko.domain.GoodsOrder;
import com.canko.service.GoodsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private GoodsService goodsService;

    private static final Logger log = Logger.getLogger(PageController.class);

    @ResponseBody
    @RequestMapping(value="/goods/list")
    public Map<String, Object> getGoodsList(String name, Integer page, Integer rows){
        if(Objects.isNull(page) || page < 0){
            page = 0;
        }
        if(Objects.isNull(rows) || rows < 20){
            rows = 20;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", goodsService.countGoodsByName(name));
        result.put("rows", goodsService.getGoodsListByName(name, page, rows));

        return result;
    }

    @ResponseBody
    @RequestMapping("/goods/{id}")
    public Map<String, Object> getGoods(@PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            Goods goods = goodsService.getGoodsById(id);
            if(Objects.isNull(goods)) {
                result.put("code", 404);
                result.put("msg", "Goods not exist!");
            }else{
                result.put("code", 200);
                result.put("msg", "success!");
                result.put("info", goods);
            }
        }catch (Exception e){
            log.error("Get goods {id=" + id + "} info error due to " + e );
            result.put("code", 500);
            result.put("msg", "Get goods info error!");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/goods/add")
    public boolean addGoods(Goods goods){
        try{
            goods.setDeadlineTime(new Date());
            goods.setDiscount((goods.getMarketPrice()*10)/Double.valueOf(goods.getPrimePrice()));
            goods.setDisplayId(String.valueOf(System.currentTimeMillis()));
            goodsService.addGoods(goods);
            return true;
        }catch (Exception e){
            log.error("add goods " + goods + " error due to " + e);
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value="/goods/update")
    public boolean updateGoods(Goods goods){

        return true;
    }

    @ResponseBody
    @RequestMapping(value="/order/list")
    public List<GoodsOrder> getOrderList(){

        return null;
    }


}
