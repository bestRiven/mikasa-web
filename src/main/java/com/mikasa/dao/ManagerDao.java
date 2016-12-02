package com.mikasa.dao;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
	List<Map<String,Object>> queryStore(Map<String, Object> params);
	int queryStoreCount(Map<String, Object> params);
	int insertStore(Map<String, Object> params);
	int updateStore(Map<String, Object> params);
	int deleteStore(Map<String, Object> params);
	
	List<Map<String,Object>> queryStoreSku(Map<String, Object> params);
	int queryStoreSkuCount(Map<String, Object> params);
	int insertStoreSku(Map<String, Object> params);
	int updateStoreSku(Map<String, Object> params);
	int deleteStoreSku(Map<String, Object> params);
	
	/**暂时没用**/
	int batchInsertStore(List<Map<String, Object>> params);

	int batchInsertStoreSku(List<Map<String, Object>> params);
	List<Map<String,Object>> queryLocaltionByOrderId(Map<String, Object> params);
	
	
	List<Map<String,Object>> queryOrders(Map<String, Object> map);
	int queryOrdersCount(Map<String, Object> map);
	List<Map<String, Object>> queryOrdersGroup(Map<String, Object> map);
	int queryOrdersGroupCount(Map<String, Object> map);
}
