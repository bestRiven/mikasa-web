package com.mikasa.dao;

import com.mikasa.model.SuccessKill;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sherlock on 2016/12/2.
 */
public interface SuccessKillDao {

    /**
     * 插入购买明细
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

    /**
     * 根据id查询
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKill queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
}
