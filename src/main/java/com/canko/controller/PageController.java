package com.canko.controller;

import com.canko.common.ExcelData;
import com.canko.common.ExportExcelUtils;
import com.canko.common.UploadUtils;
import com.canko.domain.Goods;
import com.canko.service.ExportExcelService;
import com.canko.service.GoodsService;
import com.canko.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExportExcelService exportExcelService;

    private static final Logger log = Logger.getLogger(PageController.class);

    @ResponseBody
    @RequestMapping(value="/goods/list")
    public Map<String, Object> getGoodsList(String name, Integer page, Integer rows){
        if(Objects.isNull(page) || page < 1){
            page = 1;
        }
        if(Objects.isNull(rows) || rows < 20){
            rows = 20;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("total", goodsService.countGoodsByName(name));
            result.put("rows", goodsService.getGoodsListByName(name, page, rows));
            return result;
        }catch (Exception e){
            log.error("Get goods list error due to " + e);
            result.put("msg", "Server error!");
            result.put("total", 0);
            result.put("rows", null);
            return result;
        }

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
    @RequestMapping(value="/goods/add", method = RequestMethod.POST, consumes="application/json")
    public Map<String, Object> addGoods(@RequestBody Goods goods){
        Map<String,Object> result = new HashMap<>();
        result.put("code", 400);
        String msg = checkGoodsProperties(goods);
        if(StringUtils.isNotBlank(msg)){
            result.put("msg",msg);
            return result;
        }
        try{
            goods.setDeadlineTime(new Date());
            double discount = (goods.getMarketPrice()*10)/Double.valueOf(goods.getPrimePrice());
            goods.setDiscount(new BigDecimal(discount).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
            goods.setDisplayId(String.valueOf(System.currentTimeMillis()));
            goodsService.addGoods(goods);
            result.put("code", 200);
            result.put("msg", "success!");
            return result;
        }catch (Exception e){
            log.error("add goods " + goods + " error due to " + e);
            result.put("code", 500);
            result.put("msg", "Server error!");
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value="/goods/update", method = RequestMethod.POST, consumes="application/json")
    public Map<String, Object> updateGoods(@RequestBody Goods goods){
        Map<String,Object> result = new HashMap<>();
        result.put("code", 400);
        String msg = checkGoodsProperties(goods);
        if(StringUtils.isNotBlank(msg)){
            result.put("msg",msg);
            return result;
        }

        try{
            Goods dataGoods = goodsService.getGoodsByDisplayId(goods.getDisplayId());
            if(dataGoods == null){
                result.put("msg","您要修改的商品不存在！");
                return result;
            }
            goods.setId(dataGoods.getId());
            double discount = (goods.getMarketPrice()*10)/Double.valueOf(goods.getPrimePrice());
            goods.setDiscount(new BigDecimal(discount).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
            goodsService.updateGoods(goods);
            result.put("code", 200);
            result.put("msg", "success!");
            return result;
        }catch (Exception e){
            log.error("update goods " + goods + "error due to " + e);
            result.put("code", 500);
            result.put("msg", "Server error!");
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value="/order/list")
    public Map<String, Object> getOrderList(String name,String tel,String displayId,
                                            String startTime,String endTime,Integer page, Integer rows){
        if(Objects.isNull(page) || page < 1){
            page = 1;
        }
        if(Objects.isNull(rows) || rows < 20){
            rows = 20;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            result.put("total", orderService.countGoodsOrderBy(name,tel,displayId,startTime,endTime));
            result.put("rows", orderService.getGoodsOrderList(name,tel,displayId,startTime,endTime,page,rows));
            return result;
        }catch (Exception e){
            result.put("total", 0);
            result.put("rows", null);
            result.put("msg","Server error!");
            log.error("Get order list error due to " + e);
            return result;
        }
    }

    @RequestMapping(value="/export")
    public void export(HttpServletResponse response, String startTime, String endTime){
        try {
            exportExcelService.exportOrderList(response, "order.xlsx", startTime, endTime);
        }catch (Exception e){
            log.error("export order list error due to " + e);
        }
    }

    @ResponseBody
    @RequestMapping(value="/upload")
    public Map<String, Object>  springUpload(@RequestParam(value = "file[]", required = false) MultipartFile[] files) {
        Map<String, Object> result = new LinkedHashMap<>();
        try{
            Map<String,String> fileNameMap = new LinkedHashMap<>();
            for(MultipartFile file : files){
                String fileName = insertUUID(file.getOriginalFilename());
                if(UploadUtils.upload(fileName, file)){
                    fileNameMap.put(file.getOriginalFilename(), "http://p26q3xewv.bkt.clouddn.com/" + fileName);
                }
            }
            result.put("code", 200);
            result.put("msg", "success!");
            result.put("fileAddress", fileNameMap);
        }catch (Exception e){
            log.error("upload file error due to " + e);
            result.put("code", 500);
            result.put("msg", "Server error!");
        }
        return result;
    }

    private String checkGoodsProperties(Goods goods){
        if(StringUtils.isBlank(goods.getName())){
            return "商品名称不能为空！";
        }

        if(StringUtils.isBlank(goods.getGoodsUrl())){
            return "商品大图地址不能为空！";
        }

        if(StringUtils.isBlank(goods.getCarouselUrl())){
            return "商品轮播图地址不能为空！";
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

        if(StringUtils.isBlank(goods.getGoodsImages())) {
            return "商品图片地址不能为空！";
        }

        if(StringUtils.isBlank(goods.getGoodsInformation())){
            return "商品描述不能为空！";
        }
        if(StringUtils.isBlank(goods.getFirstLevelName()) || StringUtils.isBlank(goods.getFirstLevel())){
            return "商品一级菜单及属性不能为空！";
        }

        if(StringUtils.isNotBlank(goods.getSecondLevelName())){
            if(StringUtils.isBlank(goods.getSecondLevel())){
                return "商品二级属性不能为空！";
            }
        }

        if(StringUtils.isNotBlank(goods.getThirdLevelName())){
            if(StringUtils.isBlank(goods.getThirdLevel())){
                return "商品三级属性不能为空！";
            }
        }

        if(StringUtils.isBlank(goods.getBuyInformation())){
            return "商品抢购描述不能为空！";
        }


        return null;
    }

    private String insertUUID(String fileName){
        StringBuilder stringBuilder = new StringBuilder(fileName);
        stringBuilder.insert(fileName.lastIndexOf("."), "_" + UUID.randomUUID().toString().replace("-", ""));
        return stringBuilder.toString();
    }

}
