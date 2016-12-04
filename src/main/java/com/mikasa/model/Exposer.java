package com.mikasa.model;

/**
 * Created by sherlock on 2016/12/2.
 */
public class Exposer {

    private boolean success;

    private long seckillId;

    private String md5String;

    private long nowTime;

    private long startTime;

    private long endTime;

    public Exposer(){

    }

    public Exposer(boolean success, long seckillId) {
        this.success = success;
        this.seckillId = seckillId;
    }

    public Exposer(boolean success, long seckillId, String md5String) {
        this.success = success;
        this.seckillId = seckillId;
        this.md5String = md5String;
    }

    public Exposer(boolean success, long seckillId, long nowTime, long startTime, long endTime) {
        this.success = success;
        this.seckillId = seckillId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5String() {
        return md5String;
    }

    public void setMd5String(String md5String) {
        this.md5String = md5String;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
