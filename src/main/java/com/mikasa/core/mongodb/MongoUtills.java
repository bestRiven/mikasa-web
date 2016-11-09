package com.mikasa.core.mongodb;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class MongoUtills {

    @Autowired
    protected MongoOperations mongoTemplate;

	public void saveMongoDB(Object object) {
        mongoTemplate.insert(object);
    }

    public void saveMongoDB(Collection<?> collection,Class<?> clazz) {
        mongoTemplate.insert(collection, clazz);
    }

    public Object findById(Long id,Class<?> clazz){
    	return mongoTemplate.findById(id, clazz);
    }

    public List<Object> query(Criteria criteria, Object t) {
    	Query query = new Query(criteria);
        return query(query, t);
    }

	private List<Object> query(Query query, Object t) {
		List<Object> list = (List<Object>) mongoTemplate.find(query, t.getClass());
        return list;
	}
    public List<Object> query(Criteria criteria, Object t,Sort sort) {
    	Query query = new Query(criteria).with(sort);
        return query(query, t);
    }

    public void updateMongoDB(Criteria criteria,Object object) {
        this.deleteMongoDB(criteria, object.getClass());
        this.saveMongoDB(object);
    }

    public void updateMongoDB(Query query, Update update,Class<?> clazz) {
        mongoTemplate.upsert(query, update, clazz);
    }

    public void updateMongoMulti(Query query, Update update,Class<?> clazz){
    	mongoTemplate.updateMulti(query, update, clazz);
    }

    public void deleteMongoDB(Criteria criteria, Class<?> clazz) {
        mongoTemplate.remove(new Query(criteria),
        		clazz);
    }

    public long getCountMongoDB(Criteria criteria,Class<?> clazz){
        long count=mongoTemplate.count(new Query(criteria),clazz);
        return count;
    }

}
