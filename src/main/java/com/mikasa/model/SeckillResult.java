package com.mikasa.model;

/**
 * Created by sherlock on 2016/12/2.
 */
public class SeckillResult<T> {

    private boolean success;

    private T t;

    private String message;

    public SeckillResult(boolean success, T t) {
        this.success = success;
        this.t = t;
    }

    public SeckillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
