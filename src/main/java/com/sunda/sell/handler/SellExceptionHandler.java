package com.sunda.sell.handler;

import com.sunda.sell.exception.SellException;
import com.sunda.sell.utils.ResultVoUtil;
import com.sunda.sell.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 老蹄子 on 2018/8/5 下午3:16
 */
@ControllerAdvice
public class SellExceptionHandler {


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }
}
