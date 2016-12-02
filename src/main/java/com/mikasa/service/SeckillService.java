package com.mikasa.service;

import com.mikasa.exception.RepeatKillException;
import com.mikasa.exception.SeckillCloseException;
import com.mikasa.exception.SeckillException;
import com.mikasa.model.Exposer;
import com.mikasa.model.Seckill;
import com.mikasa.model.SeckillExecution;

import java.util.List;

/**
 * Created by sherlock on 2016/12/2.
 */

public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    //输出秒杀开启接口地址
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行描述操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)  throws SeckillCloseException,RepeatKillException,SeckillException;
    /**
     * 通过存储过程执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5);
}
