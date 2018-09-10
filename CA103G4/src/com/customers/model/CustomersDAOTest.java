package com.customers.model;

import java.util.List;

public class CustomersDAOTest {

	public static void main(String[] args) {
		CustomersVO customersvo = new CustomersVO();
		CustomersDAO dao = new CustomersDAO();
		// insert
		customersvo.setCus_No(Integer.toString(026));
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
		// delete
		dao.delete(006);

		// getall
		List<CustomersVO> list = dao.getAll();
		for(int i =0 ; i < list.size(); i ++ ) {
			CustomersVO customersvo2 = list.get(i);
			System.out.println("候位編號: " + customersvo2.getCus_No());
			System.out.println("客人姓名: " + customersvo2.getCus_Name());
			System.out.println("電話: " + customersvo2.getCus_Phone());
			System.out.println("訂位人數: " + customersvo2.getCus_People());
			System.out.println("=============================");
		}
		
	}

}
