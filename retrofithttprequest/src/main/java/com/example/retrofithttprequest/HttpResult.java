package com.example.retrofithttprequest;

public class HttpResult<T> {
    /**
     * 返回码200成功,100或400需要重新登录
     */
    private int flag;
    /**
     * 返回的消息
     */
    private String msg;
    /**
     * 实际数据内容体
     */
    private T rsObj;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRsObj() {
        return rsObj;
    }

    public void setRsObj(T rsObj) {
        this.rsObj = rsObj;
    }
}
