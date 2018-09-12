package com.customers.model;

import java.util.ArrayList;
import java.util.Collections;
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
		hash.put("CUS_NO", customersvo.getCus_No());
		hash.put("CUS_NAME", customersvo.getCus_Name());
		hash.put("CUS_PHONE", customersvo.getCus_Phone());
		hash.put("CUS_PEOPLE", Integer.toString(customersvo.getCus_People()));
		jedis.hmset("cus:" + customersvo.getCus_No(), hash);
		jedis.expire("cus:" + customersvo.getCus_No(), 3600);

		jedis.close();
	}

	@Override
	public void delete(String cus_key) {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		Long delNum = jedis.hdel(cus_key, "CUS_NO", "CUS_NAME", "CUS_PHONE", "CUS_PEOPLE");
		if (delNum > 0 && delNum < 4)
			System.out.println("刪除 " + delNum + " 筆屬性");
		else if (delNum == 4)
			System.out.println("刪除 1筆 顧客資料");
		else
			System.out.println("沒刪除任何東西..");
		jedis.close();
	}

	@Override
	public CustomersVO findByKey(String cus_No) {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		Map<String, String> hm = jedis.hgetAll(cus_No);
		CustomersVO customersvo = new CustomersVO();
		customersvo.setCus_No(hm.get("CUS_NO"));
		customersvo.setCus_Name(hm.get("CUS_NAME"));
		customersvo.setCus_Phone(hm.get("CUS_PHONE"));
		customersvo.setCus_People(Integer.parseInt(hm.get("CUS_PEOPLE")));
		System.out.printf("%d 秒後自動銷毀 " , jedis.ttl("cus:" + customersvo.getCus_No()));
		jedis.close();
		return customersvo;
	}

	@Override
	public List<CustomersVO> getAll() {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		Set<String> keySet = jedis.keys("cus:*");

		Iterator<String> it = keySet.iterator();
		List<CustomersVO> list = new ArrayList<CustomersVO>();
		while (it.hasNext()) {
			String Cus_No = it.next();
			Map<String, String> map = jedis.hgetAll(Cus_No);

			CustomersVO customersvo = new CustomersVO();
			customersvo.setCus_No(map.get("CUS_NO"));
			customersvo.setCus_Name(map.get("CUS_NAME"));
			customersvo.setCus_Phone(map.get("CUS_PHONE"));
			customersvo.setCus_People(Integer.parseInt(map.get("CUS_PEOPLE")));
			list.add(customersvo);
		}
		Collections.sort(list);
		jedis.close();
		return list;
	}

}
