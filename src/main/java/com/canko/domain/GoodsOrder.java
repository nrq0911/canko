package com.canko.domain;

import java.util.Date;

/**
 * Created by nrq on 2017/6/17.
 * 订单类
 */
public class GoodsOrder {

    private int id;

    /**订单号*/
    private String displayId;

    /**商品*/
    private Goods goods;

    /**商品规格*/
    private String goodsSpec;

    private String color;

    private String size;

    /**商品数量*/
    private int num;

    /**订单货币种类*/
    private int orderCurrency;

    /**订单价格*/
    private double orderPrice;

    /**订单状态*/
    private int orderState;

    /**物流信息*/
    private String orderInformation;

    /**订单备注*/
    private String orderRemark;

    /**订单创建时间*/
    private Date createTime;

    /**用户信息*/
    private Member member;

    /**订单更新时间*/
    private Date updateTime;

    private Integer goodsId;

    private Integer memberId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(String orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(int orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "GoodsOrder{" +
                "id=" + id +
                ", displayId='" + displayId + '\'' +
                ", goods=" + goods +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", num=" + num +
                ", orderState=" + orderState +
                ", orderInformation='" + orderInformation + '\'' +
                ", orderRemark='" + orderRemark + '\'' +
                ", createTime=" + createTime +
                ", member=" + member +
                '}';
    }
}
