package com.robot.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
/*
* result domain for the front-end
* @author: ning
 */
public class Result<T> implements Serializable {

    /**
     * whether success
     */
    private Boolean success;

    /**
     * error code
     */
    private String errCode;

    /**
     *  error message
     */
    private String errMsg;

    /**
     * return value
     */
    private T module;


    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", module=" + module +
                '}';
    }
    // success
    public Result<T> ok(T module){
        this.setSuccess(true);
        this.setErrCode("0");
        this.setErrCode("ok");
        this.setModule(module);
        return this;
    }
    // error
    public Result<T> error(String errCode,String errMsg){
        this.setSuccess(false);
        this.setErrCode(errCode);
        this.setErrMsg(errMsg);
        return this;
    }

    public Result<T> error(String errMsg){
        this.setSuccess(false);
        this.setErrCode("-1");
        this.setErrMsg(errMsg);
        return this;
    }
}
