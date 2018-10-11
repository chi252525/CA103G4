package com.orderinvoice.model;

import java.util.*;

public interface OrderinvoiceDAO_interface {

	public void insert(OrderinvoiceVO orderinvoiceVO);

	public void update(OrderinvoiceVO orderinvoiceVO);

	public void delete(String orderinvoiceVO);

	public List<OrderinvoiceVO> findByOrder_no(String order_no);

	public List<OrderinvoiceVO> getAll();
	
	public void insert2 (OrderinvoiceVO orderinvoiceVO , java.sql.Connection con);
	
	public void updateSta(OrderinvoiceVO orderinvoiceVO);
	
	public int getByOrder_no(String order_no);
	 
	 
}
