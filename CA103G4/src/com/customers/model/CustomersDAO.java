package com.customers.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class CustomersDAO implements CustomersDAO_interface {
	private static final String host = "localhost";
	private static final int port = 6379;
	private static final String password = "123456";

	@Override
	public void insertOrUpdate(CustomersVO customersvo) {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		HashMap<String, String> hash = new HashMap<>();
//		hash.put("CUS_NO", Integer.toString(customersvo.getCus_No()));
		hash.put("CUS_NAME", customersvo.getCus_Name());
		hash.put("CUS_PHONE", customersvo.getCus_Phone());
		hash.put("CUS_PEOPLE", Integer.toString(customersvo.getCus_People()));
		jedis.hmset(Integer.toString(customersvo.getCus_No()), hash);
		System.out.println("新增完成!");

		jedis.close();
	}

	@Override
	public void delete(Integer cus_No) {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		String cus_key = Integer.toString(cus_No);
		Long delNum = jedis.hdel("cus:"+cus_key, "CUS_NO","CUS_NAME", "CUS_PHONE", "CUS_PEOPLE");
		if (delNum > 0&&delNum<4)
			System.out.println("刪除 " + delNum + " 筆屬性");
		else if (delNum == 4)
			System.out.println("刪除 1筆 顧客資料");
		else
			System.out.println("沒刪除任何東西..");
		jedis.close();
	}

	@Override
	public CustomersVO findByPrimaryKey(Integer cus_No) {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		String cus_key = Integer.toString(cus_No);
		Map<String, String> hm = jedis.hgetAll(cus_key);
		CustomersVO customersvo = new CustomersVO();
		customersvo.setCus_Name(hm.get("CUS_NAME"));
		customersvo.setCus_Name(hm.get("CUS_PHONE"));
		customersvo.setCus_Name(hm.get("CUS_PEOPLE"));
		jedis.close();
		return customersvo;
	}

	@Override
	public List<CustomersVO> getAll() {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		Set<String> keySet = jedis.keys("cus:*");
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String Cus_No = it.next();
			Map<String, String> map = jedis.hgetAll(Cus_No);
			System.out.println("候位編號: " + map.get("CUS_NO"));
			System.out.println("客人姓名: " + map.get("CUS_NAME"));
			System.out.println("電話: " + map.get("CUS_PHONE"));
			System.out.println("訂位人數: " + map.get("CUS_PEOPLE"));
			System.out.println("==========================");
		}
		jedis.close();
		return null;
	}

}
