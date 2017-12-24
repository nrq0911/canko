package com.canko.controller;

import com.canko.domain.Goods;
import com.canko.domain.GoodsOrder;
import com.canko.service.GoodsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @RequestMapping(value="/goods/add")
    public boolean addGoods(Goods goods){

        return true;
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
