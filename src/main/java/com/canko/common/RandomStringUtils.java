package com.canko.common;

public class RandomStringUtils {

    public static String getRandomNum(int n){
        n = (n < 6 || n >100) ? 6 : n;
        Integer a = Double.valueOf(Math.random()*Math.random()).intValue();
        return a.toString();
    }
}
