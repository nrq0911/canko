package com.canko.controller;

import com.canko.domain.Goods;
import com.canko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nrq on 2017/6/17.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品id获取商品信息
     * */
    @RequestMapping(value="/{goodsId}",method = RequestMethod.GET)
    public String getGoods(@PathVariable int goodsId, Model model){
        Goods goods = goodsService.getGoodsById(goodsId);
        model.addAttribute("goods",goods);
        return "product";
    }

    /**
     * 新增货物信息
     * */
    @RequestMapping(value="/addGoods",method = RequestMethod.POST)
    public String addGoods(){


        return "goodsList";
    }

    /**
     * 货物列表
     * */
    @RequestMapping(value = "/goodsList",method = RequestMethod.POST)
    public String goodsList(){

        return "goodsList";
    }

}
