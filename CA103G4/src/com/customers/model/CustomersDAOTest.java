package com.customers.model;

import java.util.List;

public class CustomersDAOTest {

	public static void main(String[] args) {
		CustomersVO customersvo = new CustomersVO();
		CustomersDAO dao = new CustomersDAO();
		// insert
		customersvo.setCus_No("006");
		customersvo.setCus_Name("Kevin_Cano");
		customersvo.setCus_Phone("0909392246");
		customersvo.setCus_People(12);
		dao.insertOrUpdate(customersvo);
		customersvo.setCus_No("026");
		customersvo.setCus_Name("Kevin_Nash");
		customersvo.setCus_Phone("0909392246");
		customersvo.setCus_People(3);
		dao.insertOrUpdate(customersvo);
		System.out.println("新增完成!");

		// update
		customersvo.setCus_No("026");
		customersvo.setCus_Name("Bamboo");
		customersvo.setCus_Phone("0800091000");
		dao.insertOrUpdate(customersvo);
		System.out.println("修改完成!");
		
		//Query
		System.out.println("查詢會員編號006,026:");
		CustomersVO customersvo2 = dao.findByKey("cus:006");
		System.out.println("候位編號: " + customersvo2.getCus_No());
		System.out.println("客人姓名: " + customersvo2.getCus_Name());
		System.out.println("電話: " + customersvo2.getCus_Phone());
		System.out.println("訂位人數: " + customersvo2.getCus_People());
		System.out.println("=========================");
		customersvo2 = dao.findByKey("cus:026");
		System.out.println("候位編號: " + customersvo2.getCus_No());
		System.out.println("客人姓名: " + customersvo2.getCus_Name());
		System.out.println("電話: " + customersvo2.getCus_Phone());
		System.out.println("訂位人數: " + customersvo2.getCus_People());
		System.out.println("查詢完成!");
		
		// delete
		dao.delete("cus:006");
		System.out.println("刪除完成!");
		System.out.println("================");
		// getAll
		System.out.println("===========候位顧客名單===============");
		List<CustomersVO> list = dao.getAll();
		for(int i =0 ; i < list.size(); i ++ ) {
			CustomersVO customersvo3 = list.get(i);
			System.out.println("候位編號: " + customersvo3.getCus_No());
			System.out.println("客人姓名: " + customersvo3.getCus_Name());
			System.out.println("電話: " + customersvo3.getCus_Phone());
			System.out.println("訂位人數: " + customersvo3.getCus_People());
			System.out.println("=============================");
		}
		
		
	}

}
