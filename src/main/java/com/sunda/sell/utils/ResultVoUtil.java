package com.sunda.sell.utils;

import com.sunda.sell.vo.ResultVo;

/**
 * Created by 老蹄子 on 2018/7/31 下午9:17
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }
    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
