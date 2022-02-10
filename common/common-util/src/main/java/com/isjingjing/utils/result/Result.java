package com.isjingjing.utils.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @authors:静静
 * @description:null
 */
@Data
public class Result {

    private Integer code;

    private boolean status;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public static Result success() {

        Result result = new Result();

        result.setCode(StatusCode.SUCCESS.getCode());

        result.setStatus(StatusCode.SUCCESS.isStatus());

        result.setMessage("成功");


        return result;
    }

    public static Result fail() {

        Result result = new Result();

        result.setCode(StatusCode.FALI.getCode());

        result.setStatus(StatusCode.FALI.isStatus());

        result.setMessage("失败");

        return result;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result status(boolean status) {
        this.status = status;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    public Result data(String key, Object data) {
        this.data.put(key, data);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.data = map;
        return this;
    }

}
