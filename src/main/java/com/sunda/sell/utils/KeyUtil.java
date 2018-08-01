package com.sunda.sell.utils;

import java.util.Random;

/**
 * Created by 老蹄子 on 2018/8/1 下午2:35
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
