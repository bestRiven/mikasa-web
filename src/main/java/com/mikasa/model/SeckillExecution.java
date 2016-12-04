package com.mikasa.model;

import com.mikasa.enums.SeckillStatEnum;

/**
 * Created by sherlock on 2016/12/2.
 */
public class SeckillExecution {

    private long seckillId;

    private SeckillStatEnum seckillStatEnum;

    private SuccessKill successKill;

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.seckillStatEnum = seckillStatEnum;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, SuccessKill successKill) {
        this.seckillId = seckillId;
        this.seckillStatEnum = seckillStatEnum;
        this.successKill = successKill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public SeckillStatEnum getSeckillStatEnum() {
        return seckillStatEnum;
    }

    public void setSeckillStatEnum(SeckillStatEnum seckillStatEnum) {
        this.seckillStatEnum = seckillStatEnum;
    }

    public SuccessKill getSuccessKill() {
        return successKill;
    }

    public void setSuccessKill(SuccessKill successKill) {
        this.successKill = successKill;
    }
}
