package com.canko.model;

import com.alibaba.fastjson.JSON;
import com.canko.domain.Goods;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ProductVO {

    /**商品ID*/
    private String id;

    /**商品名称*/
    private String title;

    /**商品大图*/
    private String goodsUrl;

    /**原价*/
    private long shopPrice;

    /**销售价*/
    private long marketPrice;

    /**折扣*/
    private double discount;

    /**销量*/
    private int salesVolume;

    /**库存*/
    private int stock;

    /**商品详情*/
    private List<String> goodsInformation;

    /**商品图片*/
    private Map<Integer,String> goodsImages;

    /**一级菜单名称*/
    private String firstLevelName;

    /**一级菜单属性*/
    private List<String> firstLevel;

    /**一级商品属性对应的图片*/
    private Map<String,String> firstLevelPicture;

    /**二级菜单名称*/
    private String secondLevelName;

    /**二级菜单属性*/
    private List<String> secondLevel;

    /**三级菜单名称*/
    private String thirdLevelName;

    /**三级菜单属性*/
    private List<String> thirdLevel;

    /**抢购描述*/
    private List<String> buyInformation;

    /**轮播图地址*/
    private List<String> carouselUrl;

    /**商品视频地址*/
    private String video;

    /**备注*/
    private String remark;

    public ProductVO(){}

    public ProductVO(Goods goods) {
        this.id = goods.getDisplayId();
        this.video = goods.getVideo();
        this.title = goods.getName();
        this.goodsUrl = goods.getGoodsUrl();
        this.marketPrice = goods.getMarketPrice();
        this.shopPrice = goods.getPrimePrice();
        this.discount = goods.getDiscount();
        this.salesVolume = goods.getSalesVolume();
        this.stock = goods.getStock();
        this.goodsInformation = new ArrayList<>();
        for(String str : goods.getGoodsInformation().split(";")){
            this.goodsInformation.add(str);
        }
        this.goodsImages = JSON.parseObject(goods.getGoodsImages(), Map.class);
        this.firstLevelName = goods.getFirstLevelName();
        this.firstLevel = new ArrayList<>();
        String[] levels = goods.getFirstLevel().split(";");
        for(String str : levels){
            this.firstLevel.add(str.replace("\r", "").replace("\n",""));
        }
        this.firstLevelPicture = JSON.parseObject(goods.getFirstLevelPicture(), Map.class);
        if(Objects.isNull(firstLevelPicture) || firstLevelPicture.isEmpty()){
            firstLevelPicture = new LinkedHashMap<>();
            for(String level : firstLevel){
                firstLevelPicture.put(level, goodsUrl);
            }
        }
        this.secondLevelName = goods.getSecondLevelName();
        this.secondLevel = new ArrayList<>();
        if(StringUtils.isNotBlank(goods.getSecondLevel())){
            for(String str : goods.getSecondLevel().split(";")){
                this.secondLevel.add(str);
            }
        }
        this.thirdLevelName = goods.getThirdLevelName();
        this.thirdLevel = new ArrayList<>();
        if(StringUtils.isNotBlank(goods.getThirdLevel())){
            for(String str : goods.getThirdLevel().split(";")){
                this.thirdLevel.add(str);
            }
        }

        this.buyInformation = new ArrayList<>();
        for(String str : goods.getBuyInformation().split(";")){
            this.buyInformation.add(str);
        }
        this.carouselUrl = new ArrayList<>();
        if(StringUtils.isNotBlank(goods.getCarouselUrl())){
            for(String str : goods.getCarouselUrl().split(";")){
                this.carouselUrl.add(str);
            }
        }else {
            this.carouselUrl.add(goods.getGoodsUrl());
        }
        this.video = goods.getVideo();
        this.remark = goods.getRemark();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public long getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(long shopPrice) {
        this.shopPrice = shopPrice;
    }

    public long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<String> getGoodsInformation() {
        return goodsInformation;
    }

    public void setGoodsInformation(List<String> goodsInformation) {
        this.goodsInformation = goodsInformation;
    }

    public Map<Integer,String> getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(Map<Integer,String> goodsImages) {
        this.goodsImages = goodsImages;
    }

    public String getFirstLevelName() {
        return firstLevelName;
    }

    public void setFirstLevelName(String firstLevelName) {
        this.firstLevelName = firstLevelName;
    }

    public List<String> getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(List<String> firstLevel) {
        this.firstLevel = firstLevel;
    }

    public Map<String,String> getFirstLevelPicture() {
        return firstLevelPicture;
    }

    public void setFirstLevelPicture(Map<String,String> firstLevelPicture) {
        this.firstLevelPicture = firstLevelPicture;
    }

    public String getSecondLevelName() {
        return secondLevelName;
    }

    public void setSecondLevelName(String secondLevelName) {
        this.secondLevelName = secondLevelName;
    }

    public List<String> getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(List<String> secondLevel) {
        this.secondLevel = secondLevel;
    }

    public String getThirdLevelName() {
        return thirdLevelName;
    }

    public void setThirdLevelName(String thirdLevelName) {
        this.thirdLevelName = thirdLevelName;
    }

    public List<String> getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(List<String> thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public List<String> getBuyInformation() {
        return buyInformation;
    }

    public void setBuyInformation(List<String> buyInformation) {
        this.buyInformation = buyInformation;
    }

    public List<String> getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(List<String> carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", shopPrice=" + shopPrice +
                ", marketPrice=" + marketPrice +
                ", discount=" + discount +
                ", salesVolume=" + salesVolume +
                ", stock=" + stock +
                ", goodsInformation=" + goodsInformation +
                ", goodsImages=" + goodsImages +
                ", firstLevelName='" + firstLevelName + '\'' +
                ", firstLevel=" + firstLevel +
                ", firstLevelPicture=" + firstLevelPicture +
                ", secondLevelName='" + secondLevelName + '\'' +
                ", secondLevel=" + secondLevel +
                ", thirdLevelName='" + thirdLevelName + '\'' +
                ", thirdLevel=" + thirdLevel +
                ", buyInformation=" + buyInformation +
                ", carouselUrl=" + carouselUrl +
                ", video='" + video + '\'' +
                '}';
    }
}
