package com.isjingjing.utils.exception;

import com.isjingjing.utils.exception.defineexception.CustomException;
import com.isjingjing.utils.result.Result;
import com.isjingjing.utils.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @authors:静静
 * @description:null
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e) {

        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return Result.fail().message("程序崩溃了，请联系网站负责人处理");

    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result accountIsNotExsitException(CustomException e) {

        log.error(e.getMessage());
        log.error(e.getMsg());
        log.error(ExceptionUtil.getMessage(e));
        return Result.fail().code(e.getCode()).message(e.getMsg());

    }

}
