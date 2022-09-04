package com.team8.mynews.entity;

import java.io.Serializable;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.09.04 11:36
 */
public class BaseResponse implements Serializable {
    /**
     * msg : success
     * code : 0
     */

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
