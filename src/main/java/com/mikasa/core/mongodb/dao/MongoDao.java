package com.mikasa.core.mongodb.dao;//package org.shrimp.core.mongodb.dao;

import com.mongodb.DBObject;
import java.util.List;

/**
 * Created by zhangyu on 16/8/5.
 */
public interface MongoDao {

    DBObject findOne(String collection, DBObject query, DBObject fields);

    List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize);

    List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int limit);

    void delete(String collection, DBObject dbObject);

    void save(String collection, DBObject dbObject);

    void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi);

    Long count(String collection, DBObject query);

    List distinct(String collection, String key, DBObject query);

}
