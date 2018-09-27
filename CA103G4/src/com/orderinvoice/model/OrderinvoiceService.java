package com.orderinvoice.model;

import java.sql.Connection;
import java.util.List;



public class OrderinvoiceService {
private OrderinvoiceDAO_interface dao;
	
	public OrderinvoiceService() {
		dao = new OrderinvoiceDAO();
	}

	public OrderinvoiceVO addInvoice(OrderinvoiceVO orderinvoiceVO, Connection con) {
		OrderinvoiceVO ordVO = new OrderinvoiceVO();
		dao.insert2(orderinvoiceVO, con);
		return ordVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
