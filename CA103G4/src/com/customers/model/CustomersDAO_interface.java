package com.customers.model;

import java.util.List;

public interface CustomersDAO_interface {
	void insertOrUpdate(CustomersVO customersvo);

	void delete(String cus_key);

	CustomersVO findByKey(String cus_No);

	List<CustomersVO> getAll();
	
	
}
