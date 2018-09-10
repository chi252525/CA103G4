package com.customers.model;

public class CustomersDAOTest {

	public static void main(String[] args) {
		CustomersVO customersvo = new CustomersVO();
		CustomersDAO dao = new CustomersDAO();
		//insert&update
		customersvo.setCus_No(0026);
		customersvo.setCus_Name("Kevin_Nash");
		customersvo.setCus_Phone("0909392246");
		customersvo.setCus_People(3);
		dao.insertOrUpdate(customersvo);
		//upsate
		customersvo.setCus_No(0026);
		customersvo.setCus_Name("Bamboo");
		customersvo.setCus_Phone("0800091000");
		//delete
		dao.delete(6);
		//getall
		dao.getAll();
		
		
	}

}
