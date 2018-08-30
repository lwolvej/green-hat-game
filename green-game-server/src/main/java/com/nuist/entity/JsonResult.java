package com.nuist.entity;


public class JsonResult {

    private Boolean success;

    private Object data;

    private String msg;

    public JsonResult(Boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
