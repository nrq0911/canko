package com.canko.domain.enumerate;

/**
 * Created by nrq on 2017/6/17.
 * 订单状态
 */
public enum OrderState {

    placeOrder(11,"下單"),
    confirmOrder(12,"已下單"),
    notShipped(21,"待發貨"),
    Shipping(22,"運輸中"),
    checkedOrder(31,"已驗收"),
    returnOrder(32,"未驗收"),
    orderFailure(-1,"下單失敗"),
    other(0,"其他");

    private final int code;
    private final String description;

    OrderState(int code,String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderState getOrderState(int code){
        switch (code){
            case 11:
                return placeOrder;
            case 12:
                return confirmOrder;
            case 21:
                return notShipped;
            case 22:
                return Shipping;
            case 31:
                return checkedOrder;
            case 32:
                return returnOrder;
            case 41:
                return orderFailure;
            case 50:
                return other;
            default:
                return other;
        }
    }

}
