package com.canko.model;

import com.canko.domain.GoodsOrder;

import java.util.Date;

/**
 * Created by Nieruiqun on 2018/1/2.
 */
public class OrderVO {

    private String id;

    private String name;

    private String tel;

    private String address;

    private String email;

    private String remark;

    private String spec;

    private int num;

    private double money;

    private Date createTime;

    public OrderVO() {
    }

    public OrderVO(GoodsOrder order){
        this.id = order.getDisplayId();
        this.name = order.getMember().getName();
        this.tel = order.getMember().getMobilephone();
        this.address = order.getMember().getAddress();
        this.email = order.getMember().getEmail();
        this.remark = order.getMember().getRemark();
        this.spec = order.getGoodsSpec();
        this.num = order.getNum();
        this.money = order.getOrderPrice();
        this.createTime = order.getCreateTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
