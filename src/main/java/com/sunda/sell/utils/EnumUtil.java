package com.sunda.sell.utils;

import com.sunda.sell.enums.CodeEnum;

/**
 * Created by 老蹄子 on 2018/8/2 下午9:32
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
