package com.mikasa.service.impl;

import com.mikasa.dao.SeckillDao;
import com.mikasa.dao.SuccessKillDao;
import com.mikasa.enums.SeckillStatEnum;
import com.mikasa.exception.RepeatKillException;
import com.mikasa.exception.SeckillCloseException;
import com.mikasa.exception.SeckillException;
import com.mikasa.model.Exposer;
import com.mikasa.model.Seckill;
import com.mikasa.model.SeckillExecution;
import com.mikasa.model.SuccessKill;
import com.mikasa.service.RedisSeckillService;
import com.mikasa.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sherlock on 2016/12/2.
 */

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKillDao successKillDao;

    @Autowired
    private RedisSeckillService redisSeckillService;

    //加盐处理
    private final String slat="xvzbnxsd^&&*)(*()kfmv4165323DGHSBJ";


    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }


    public Exposer exportSeckillUrl(long seckillId) {

        //优化点：缓存优化

        Seckill seckill = redisSeckillService.getSeckill(seckillId);
        if (seckill == null) {
            //访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                //放入redis
                redisSeckillService.putSeckill(seckill);
            }
        }


        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //当前系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转换特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true,seckillId,md5);
    }


    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || (!md5.equals(getMD5(seckillId)))) {
            throw new SeckillException("Seckill data rewrite");
        }
        //执行秒杀逻辑：减库存，记录购买行为
        Date nowTime = new Date();
        try {

            //记录购买行为
            int insertCount = successKillDao.insertSuccessKill(seckillId, userPhone);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("Seckill repeated");
            } else {
                //减库存
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录，秒杀结束
                    throw new SeckillCloseException("Seckill is closed");
                } else {
                    //秒杀成功
                    SuccessKill successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);

                }
            }


        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage());
            //所有编译期异常转换为运行时异常
            throw new SeckillException("Seckill inner error" + e.getMessage());
        }

    }

    /**
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     */
    public SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5) {
        if (md5 == null || (!md5.equals(getMD5(seckillId)))) {
            throw new SeckillException("Seckill data rewrite");
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        //执行存储过程，result被赋值
        try {
            seckillDao.seckillByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessKill successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
            } else {
                return new SeckillExecution(seckillId, SeckillStatEnum.FAILED);//SeckillStatEnum.stateOf(result)
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
        }
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
