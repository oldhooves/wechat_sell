package com.sunda.sell.utils;

/**
 * Created by 老蹄子 on 2018/8/2 下午4:52
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    public static boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE){
            return true;
        } else{
            return false;
        }
    }
}
