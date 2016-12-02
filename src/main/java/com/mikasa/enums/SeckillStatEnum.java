package com.mikasa.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sherlock on 2016/12/2.
 */
public class SeckillStatEnum extends EnumType{

    public static final Map<Integer, SeckillStatEnum> mapping = new HashMap<Integer, SeckillStatEnum>();

    public static final SeckillStatEnum SUCCESS = new SeckillStatEnum(1, "已创建");
    public static final SeckillStatEnum INNER_ERROR = new SeckillStatEnum(2, "已审核");
    public static final SeckillStatEnum REPEAT_KILL = new SeckillStatEnum(3, "已完成");
    public static final SeckillStatEnum END = new SeckillStatEnum(6, "已接单");
    public static final SeckillStatEnum CANCELED = new SeckillStatEnum(4, "已取消");
    public static final SeckillStatEnum DELETED = new SeckillStatEnum(-1, "已删除");
    public SeckillStatEnum(){}
    SeckillStatEnum(int type, String name) {
        super(type, name);
        mapping.put(type, this);
    }

    public static SeckillStatEnum[] getAll() {
        return mapping.values().toArray(new SeckillStatEnum[mapping.size()]);
    }
}
