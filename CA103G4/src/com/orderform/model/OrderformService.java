package com.orderform.model;

import java.util.List;

import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformService {
	private OrderformDAO_interface dao;
	
	public OrderformService() {
		dao = new OrderformDAO();
	}
	
	public OrderformVO addOrd(OrderformVO orderformVO, List<OrderinvoiceVO> list) {
		
		dao.insertWithInvoice(orderformVO,list);
		
		return orderformVO;
		
	}
	
	
	
	
	
}
