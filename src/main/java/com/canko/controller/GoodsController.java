package com.canko.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.canko.domain.Goods;
import com.canko.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by nrq on 2017/6/17.
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品id获取商品信息
     * */
    @RequestMapping(value="/goods/{goodsId}",method = RequestMethod.GET)
    public String getGoods(@PathVariable int goodsId, Model model){
        Goods goods = goodsService.getGoodsById(goodsId);
        String goodsInformation = goods.getGoodsInformation();
        String[] informations = goodsInformation.split(",");
        String jsonUrl = goods.getGoodsImages();
        Map<String,String> map = JSON.parseObject(jsonUrl,new TypeReference<Map<String,String>>(){});
        double retrenchPrice = goods.getPrimePrice() - goods.getMarketPrice();
        model.addAttribute("informations",informations);   //商品描述
        model.addAttribute("retrenchPrice",retrenchPrice); //节约资金
        model.addAttribute("goodsImage",map);   //图片url
        model.addAttribute("goods",goods);
        return "product";
    }

    /**
     * 新增货物信息
     * */
    @RequestMapping(value="/goods/addGoods",method = RequestMethod.POST)
    public String addGoods(Goods goods){
        goodsService.addGoods(goods);
        return "redirect:/goodsList";
    }

    /**
     * 货物列表
     * */
    @RequestMapping(value = "/goods/goodsList",method = RequestMethod.POST)
    public String goodsList(Model model){
        List<Goods> goodsList = goodsService.getGoodsListBy();
        model.addAttribute("goodsList",goodsList);
        return "goodsList";
    }

    /**
     * 修改货物信息
     * */
    @RequestMapping(value = "/goods/update",method = RequestMethod.POST)
    public String updateGoods(Goods goods){
        goodsService.updateGoods(goods);
        return "redirect:/goodsList";
    }

}
