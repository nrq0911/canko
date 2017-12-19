package com.canko.model;

public class SpecModel {

    private String goodsId;

    private String spec;

    private int num;

    private float money;

    public SpecModel() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "SpecModel{" +
                "goodsId='" + goodsId + '\'' +
                ", spec='" + spec + '\'' +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
