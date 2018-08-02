package com.sunda.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by 老蹄子 on 2018/8/2 下午3:15
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
