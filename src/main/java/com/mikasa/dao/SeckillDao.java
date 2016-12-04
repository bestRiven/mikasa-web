package com.mikasa.dao;

import com.mikasa.model.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sherlock on 2016/12/2.
 */
public interface SeckillDao {

    //减库存
    int reduceNumber(@Param("seckillId")long seckillId, @Param("killTime")Date killTime);

    Seckill queryById(long seckilled);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    void seckillByProcedure(Map<String, Object> paramMap);
}
