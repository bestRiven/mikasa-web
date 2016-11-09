package com.mikasa.core.mongodb.dao.impl;//package org.shrimp.core.mongodb.dao.impl;

import com.mikasa.core.mongodb.dao.MongoDao;
import com.mongodb.Cursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 16/8/5.
 */

@Service
public class MongoDaoImpl implements MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public DBObject findOne(String collection, DBObject query, DBObject fields) {
        return mongoTemplate.getCollection(collection).findOne(query, fields);
    }

    @Override
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize) {
        List<DBObject> list = new ArrayList<DBObject>();
        Cursor cursor = mongoTemplate.getCollection(collection).find(query, fields).skip((pageNum - 1) * pageSize).limit(pageSize).sort(orderBy);
        while (cursor.hasNext()) {
           list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }

    @Override
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int limit) {
        List<DBObject> list = new ArrayList<DBObject>();
        Cursor cursor = mongoTemplate.getCollection(collection).find(query, fields).sort(orderBy).limit(limit);
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }

    @Override
    public void delete(String collection, DBObject dbObject) {
        mongoTemplate.getCollection(collection).remove(dbObject);
    }

    @Override
    public void save(String collection, DBObject dbObject) {
        mongoTemplate.getCollection(collection).save(dbObject);
    }

    @Override
    public void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi) {
        mongoTemplate.getCollection(collection).update(query, update, upsert, multi);
    }

    @Override
    public Long count(String collection, DBObject query) {
        return mongoTemplate.getCollection(collection).count(query);
    }

    @Override
    public List distinct(String collection, String key, DBObject query) {
        return mongoTemplate.getCollection(collection).distinct(key, query);
    }
}
