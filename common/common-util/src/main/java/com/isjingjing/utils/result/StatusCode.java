package com.isjingjing.utils.result;

/**
 * @authors:静静
 * @description:null
 */

public enum StatusCode {

    SUCCESS(20000,true),
    FALI(40000,false);

    private Integer code;

    private boolean status;

    private StatusCode(Integer code,boolean status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



}
