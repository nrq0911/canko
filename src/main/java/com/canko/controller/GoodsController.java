package com.canko.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.canko.domain.Goods;
import com.canko.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nrq on 2017/6/17.
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    private transient final Logger logger = Logger.getLogger(GoodsController.class);

    /** 根据商品id获取商品信息*/
    @RequestMapping(value="/goods/{goodsId}",method = RequestMethod.GET)
    public String getGoods(@PathVariable String goodsId, Model model){
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
        String goodsInformation = goods.getGoodsInformation();
        String[] information = goodsInformation.split(";");
        String[] buyInformation = goods.getBuyInformation().split(";");
        String jsonUrl = goods.getGoodsImages();
        Map<Integer,String> map = JSON.parseObject(jsonUrl,new TypeReference<Map<Integer,String>>(){});
        long retrenchPrice = goods.getPrimePrice() - goods.getMarketPrice();
        model.addAttribute("informations",information);   //商品描述
        model.addAttribute("buyInformation",buyInformation); //抢购描述
        model.addAttribute("retrenchPrice",retrenchPrice); //节约资金
        model.addAttribute("goodsImage",map);   //图片url
        model.addAttribute("goods",goods);
        return "product";
    }

    @RequestMapping(value="/admin/goods/addGoods",method = RequestMethod.GET)
    public String addGoods(){
        return "goods/addGoods";
    }

    /** 新增货物信息*/
    @RequestMapping(value="/admin/goods/addGoods",method = RequestMethod.POST)
    public String addGoods(Goods goods,Model model,
                           @RequestParam(value = "goodsImg")String[] goodsImg){

        String msg = checkGoodsProperties(goods);
        if(StringUtils.isNotBlank(msg)){
            model.addAttribute("msg",msg);
            return "goods/addGoods";
        }

        if(goodsImg == null || StringUtils.isBlank(goodsImg[0])){
            msg = "商品图片地址不能为空！";
            model.addAttribute("msg",msg);
            return "goods/addGoods";
        }

        goods.setGoodsInformation(goods.getGoodsInformation().trim().replace("&",";"));
        goods.setFirstLevel(goods.getFirstLevel().trim().replace("&",";"));
        goods.setBuyInformation(goods.getBuyInformation().trim().replace("&",";"));

        if(StringUtils.isNotBlank(goods.getSecondLevelName())){
            if(StringUtils.isBlank(goods.getSecondLevel())){
                msg = "商品二级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "goods/addGoods";
            }else{
                goods.setSecondLevel(goods.getSecondLevel().trim().replace("&",";"));
            }
        }

        if(StringUtils.isNotBlank(goods.getThirdLevelName())){
            if(StringUtils.isBlank(goods.getThirdLevel())){
                msg = "商品三级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "goods/addGoods";
            }else{
                goods.setThirdLevel(goods.getThirdLevel().trim().replace("&",";"));
            }
        }

        goods.setDeadlineTime(new Date());
        goods.setDiscount((goods.getMarketPrice()*10)/Double.valueOf(goods.getPrimePrice()));
        goods.setDisplayId(String.valueOf(System.currentTimeMillis()));

        goods.setGoodsImages(JSON.toJSONString(arrayToMap(goodsImg)));

        logger.info("Add new Goods:" + goods.toString());

        try{
            goodsService.addGoods(goods);
            return "redirect:/admin/goods/goodsList";
        }catch (Exception e){
            msg = "后台服务出错错误！";
            logger.info("Add Goods error:" + e);
            model.addAttribute("msg",msg);
            return "goods/addGoods";
        }

    }

    @RequestMapping(value = "/admin/goods/goodsList",method = RequestMethod.GET)
    public String goodsList(){
        return "goods/goodsList";
    }

    /** 货物列表 */
    @RequestMapping(value = "/admin/goods/goodsList",method = RequestMethod.POST)
    public String goodsList(Model model,@RequestParam(value = "name",defaultValue = "") String goodsName){
        List<Goods> goodsList = goodsService.getGoodsListByName(goodsName);
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("name",goodsName);
        return "goods/goodsList";
    }

    @RequestMapping(value = "/admin/goods/update/{goodsId}",method = RequestMethod.GET)
    public String updateGoods(@PathVariable String goodsId,Model model){
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
        goods.setGoodsInformation(goods.getGoodsInformation().trim().replace(";","&"));
        goods.setFirstLevel(goods.getFirstLevel().trim().replace(";","&"));
        goods.setBuyInformation(goods.getBuyInformation().trim().replace(";","&"));
        if(StringUtils.isNotBlank(goods.getSecondLevel())){
            goods.setSecondLevel(goods.getSecondLevel().trim().replace(";","&"));
        }
        if(StringUtils.isNotBlank(goods.getThirdLevel())){
            goods.setThirdLevel(goods.getThirdLevel().trim().replace(";","&"));
        }
        Map<Integer,String> map = JSON.parseObject(goods.getGoodsImages(),new TypeReference<Map<Integer,String>>(){});
        model.addAttribute("goodsImages",map);
        model.addAttribute("goods",goods);
        return "goods/updateGoods";
    }

    /** 修改货物信息*/
    @RequestMapping(value = "/admin/goods/update",method = RequestMethod.POST)
    public String updateGoods(Goods goods,Model model,
                              @RequestParam(value = "goodsImg")String[] goodsImg){
        Goods dataGoods = goodsService.getGoodsById(goods.getId());
        if(dataGoods == null){
            model.addAttribute("msg","您要修改的商品不存在！");
            return "goods/updateGoods";
        }

        String msg = checkGoodsProperties(goods);
        if(StringUtils.isNotBlank(msg)){
            model.addAttribute("msg",msg);
            return "goods/updateGoods";
        }

        if(goodsImg == null || StringUtils.isBlank(goodsImg[0])){
            msg = "商品图片地址不能为空！";
            model.addAttribute("msg",msg);
            return "goods/updateGoods";
        }

        dataGoods.setName(goods.getName());
        dataGoods.setGoodsUrl(goods.getGoodsUrl());
        dataGoods.setPrimePrice(goods.getPrimePrice());
        dataGoods.setMarketPrice(goods.getMarketPrice());
        dataGoods.setSalesVolume(goods.getSalesVolume());
        dataGoods.setStock(goods.getStock());
        dataGoods.setGoodsInformation(goods.getGoodsInformation().trim().replace("&",";"));
        dataGoods.setFirstLevelName(goods.getFirstLevelName());
        dataGoods.setFirstLevel(goods.getFirstLevel().trim().replace("&",";"));
        dataGoods.setBuyInformation(goods.getBuyInformation().trim().replace("&",";"));

        if(StringUtils.isNotBlank(goods.getSecondLevelName())){
            if(StringUtils.isBlank(goods.getSecondLevel())){
                msg = "商品二级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "goods/updateGoods";
            }else{
                dataGoods.setSecondLevelName(goods.getSecondLevelName());
                dataGoods.setSecondLevel(goods.getSecondLevel().trim().replace("&",";"));
            }
        }

        if(StringUtils.isNotBlank(goods.getThirdLevelName())){
            if(StringUtils.isBlank(goods.getThirdLevel())){
                msg = "商品三级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "goods/updateGoods";
            }else{
                dataGoods.setThirdLevelName(goods.getThirdLevelName());
                dataGoods.setThirdLevel(goods.getThirdLevel().trim().replace("&",";"));
            }
        }

        dataGoods.setDeadlineTime(new Date());
        dataGoods.setDiscount((goods.getMarketPrice()*10)/Double.valueOf(goods.getPrimePrice()));
        dataGoods.setGoodsImages(JSON.toJSONString(arrayToMap(goodsImg)));
        if(StringUtils.isNotBlank(goods.getRemark())){
            dataGoods.setRemark(goods.getRemark());
        }

        try{
            goodsService.updateGoods(dataGoods);
            return "redirect:/admin/goods/goodsList";
        }catch (Exception e){
            msg = "后台服务出错错误！";
            logger.info("Update Goods error:" + e);
            model.addAttribute("msg",msg);
            return "redirect:/admin/goods/update/" + goods.getId();
        }

    }

    private Map<Integer,String> arrayToMap(String[] strings){
        Map<Integer,String> map = new LinkedHashMap<>();
        int n = 1;
        for(String str : strings){
            if(StringUtils.isNotBlank(str)){
                map.put(n++,str);
            }
        }
        return map;
    }

    private String checkGoodsProperties(Goods goods){
        if(StringUtils.isBlank(goods.getName())){
            return "商品名称不能为空！";
        }

        if(StringUtils.isBlank(goods.getGoodsUrl())){
            return "商品大图地址不能为空！";
        }

        if(goods.getPrimePrice() <= 100 || goods.getMarketPrice() <= 100 ){
            return "商品原价和活动价不能低于100！";
        }

        if(goods.getPrimePrice() < goods.getMarketPrice()){
            return "商品原价不能低于活动价！";
        }

        if(goods.getSalesVolume() < 0 || goods.getStock() < 0){
            return "商品销售量和活动价不能小于0！";
        }

        if(StringUtils.isBlank(goods.getGoodsInformation())){
            return "商品描述不能为空！";
        }
        if(StringUtils.isBlank(goods.getFirstLevelName()) || StringUtils.isBlank(goods.getFirstLevel())){
            return "商品一级菜单及属性不能为空！";
        }

        if(StringUtils.isBlank(goods.getBuyInformation())){
            return "商品抢购描述不能为空！";
        }

        return "";
    }

}
