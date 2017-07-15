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

    /**
     * 根据商品id获取商品信息
     * */
    @RequestMapping(value="/goods/{goodsId}",method = RequestMethod.GET)
    public String getGoods(@PathVariable int goodsId, Model model){
        Goods goods = goodsService.getGoodsById(goodsId);
        if(goods == null){
            return "404";
        }
        String goodsInformation = goods.getGoodsInformation();
        String[] information = goodsInformation.split(";");
        String[] buyInformation = goods.getBuyInformation().split(";");
        String jsonUrl = goods.getGoodsImages();
        Map<Integer,String> map = JSON.parseObject(jsonUrl,new TypeReference<Map<Integer,String>>(){});
        double retrenchPrice = goods.getPrimePrice() - goods.getMarketPrice();
        model.addAttribute("informations",information);   //商品描述
        model.addAttribute("buyInformation",buyInformation); //抢购描述
        model.addAttribute("retrenchPrice",retrenchPrice); //节约资金
        model.addAttribute("goodsImage",map);   //图片url
        model.addAttribute("goods",goods);
        return "product";
    }

    @RequestMapping(value="/goods/addGoods",method = RequestMethod.GET)
    public String addGoods(){
        return "/addGoods";
    }

    /**
     * 新增货物信息
     * */
    @RequestMapping(value="/goods/addGoods",method = RequestMethod.POST)
    public String addGoods(Goods goods,Model model,
                           @RequestParam(value = "goodsImg")String[] goodsImg){
        if(StringUtils.isBlank(goods.getName())){
            String msg = "商品名称不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(StringUtils.isBlank(goods.getGoodsUrl())){
            String msg = "商品大图地址不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(goods.getPrimePrice() <= 100 || goods.getMarketPrice() <= 100 ){
            String msg = "商品原价和活动价不能低于100！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(goods.getPrimePrice() < goods.getMarketPrice()){
            String msg = "商品原价不能低于活动价！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(goods.getSalesVolume() < 0 || goods.getStock() < 0){
            String msg = "商品销售量和活动价不能小于0！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(StringUtils.isBlank(goods.getGoodsInformation())){
            String msg = "商品描述不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }
        goods.setGoodsInformation(goods.getGoodsInformation().trim().replace("&",";"));

        if(goodsImg == null || StringUtils.isBlank(goodsImg[0])){
            String msg = "商品图片地址不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }

        if(StringUtils.isBlank(goods.getFirstLevelName()) || StringUtils.isBlank(goods.getFirstLevel())){
            String msg = "商品一级菜单及属性不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }
        goods.setFirstLevel(goods.getFirstLevel().trim().replace("&",";"));

        if(StringUtils.isNotBlank(goods.getSecondLevelName())){
            if(StringUtils.isBlank(goods.getSecondLevel())){
                String msg = "商品二级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "/addGoods";
            }else{
                goods.setSecondLevel(goods.getSecondLevel().trim().replace("&",";"));
            }
        }

        if(StringUtils.isNotBlank(goods.getThirdLevelName())){
            if(StringUtils.isBlank(goods.getThirdLevel())){
                String msg = "商品三级菜单属性不能为空！";
                model.addAttribute("msg",msg);
                return "/addGoods";
            }else{
                goods.setThirdLevel(goods.getThirdLevel().trim().replace("&",";"));
            }
        }

        if(StringUtils.isBlank(goods.getBuyInformation())){
            String msg = "商品抢购描述不能为空！";
            model.addAttribute("msg",msg);
            return "/addGoods";
        }
        goods.setBuyInformation(goods.getBuyInformation().trim().replace("&",";"));

        goods.setDeadlineTime(new Date());
        goods.setDiscount(goods.getMarketPrice()/goods.getPrimePrice());
        goods.setDisplayId(String.valueOf(System.currentTimeMillis()));

        goods.setGoodsImages(JSON.toJSONString(arrayToMap(goodsImg)));

        logger.info("Add new Goods:" + goods.toString());
        goodsService.addGoods(goods);
        return "forward:/goods/goodsList";
    }

    @RequestMapping(value = "/goods/goodsList",method = RequestMethod.GET)
    public String goodsList(){
        return "goodsList";
    }

    /**
     * 货物列表
     * */
    @RequestMapping(value = "/goods/goodsList",method = RequestMethod.POST)
    public String goodsList(Model model,@RequestParam(value = "name",defaultValue = "") String goodsName){
        List<Goods> goodsList = goodsService.getGoodsListByName(goodsName);
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("name",goodsName);
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

}
