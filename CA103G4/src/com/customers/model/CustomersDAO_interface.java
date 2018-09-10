package com.customers.model;

import java.util.List;

public interface CustomersDAO_interface {
	void insertOrUpdate(CustomersVO customersvo);

	void delete(Integer cus_No);

	CustomersVO findByPrimaryKey(Integer cus_No);

	List<CustomersVO> getAll();
}
