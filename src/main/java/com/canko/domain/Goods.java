package com.canko.domain;

import java.util.Date;

/**
 * Created by nrq on 2017/6/16.
 * 商品信息类
 */
public class Goods {

    private int id;

    /**商品ID*/
    private String displayId;

    /**商品封面图*/
    private String goodsUrl;

    /**原价*/
    private double primePrice;

    /**销售价*/
    private double marketPrice;

    /**折扣*/
    private double discount;

    /**截至时间*/
    private Date deadlineTime;

    /**销量*/
    private int salesVolume;

    /**库存*/
    private int stock;

    /**商品详情*/
    private String goodsInformation;

    /**商品图片*/
    private String goodsImages;

    /**抢购描述*/
    private String buyInformation;

    /**备注*/
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public double getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(double primePrice) {
        this.primePrice = primePrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
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

    public String getGoodsInformation() {
        return goodsInformation;
    }

    public void setGoodsInformation(String goodsInformation) {
        this.goodsInformation = goodsInformation;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public String getBuyInformation() {
        return buyInformation;
    }

    public void setBuyInformation(String buyInformation) {
        this.buyInformation = buyInformation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "GoodsMapper{" +
                "id=" + id +
                ", displayId='" + displayId + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", primePrice=" + primePrice +
                ", marketPrice=" + marketPrice +
                ", discount=" + discount +
                ", deadlineTime=" + deadlineTime +
                ", salesVolume=" + salesVolume +
                ", stock=" + stock +
                ", goodsInformation='" + goodsInformation + '\'' +
                ", goodsImages='" + goodsImages + '\'' +
                ", buyInformation='" + buyInformation + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
