package com.mikasa.exception;

/**
 * Created by sherlock on 2016/12/2.
 */
public class SeckillCloseException extends RuntimeException {

    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public SeckillCloseException() {
        super();
    }

    public SeckillCloseException(String message) {
        super(message);
        msgDes = message;
    }

    public SeckillCloseException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
