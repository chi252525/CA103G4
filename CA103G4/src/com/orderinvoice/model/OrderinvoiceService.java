package com.orderinvoice.model;

import java.sql.Connection;
import java.util.List;



public class OrderinvoiceService {
private OrderinvoiceDAO_interface dao;
	
	public OrderinvoiceService() {
		dao = new OrderinvoiceDAO();
	}

	public OrderinvoiceVO addInvoice(OrderinvoiceVO orderinvoiceVO, Connection con) {
		OrderinvoiceVO oinVO = new OrderinvoiceVO();
		dao.insert2(orderinvoiceVO, con);
		return oinVO;
	}
	
	public List<OrderinvoiceVO> findByOrder_no(String order_no) {
		return dao.findByOrder_no(order_no);
	}
	
	public int getByOrder_no(String order_no) {
		
		return dao.getByOrder_no(order_no);
	}
	
	
	public OrderinvoiceVO forUpdate(String order_no,String menu_no,String custom_no) {
		OrderinvoiceVO oinVO = new OrderinvoiceVO();
		oinVO.setOrder_no(order_no);
		oinVO.setMenu_no(menu_no);
		oinVO.setCustom_no(custom_no);
		
		dao.updateSta(oinVO);
		return null;
	}
	
	public List<OrderinvoiceVO> findOneByOrder(String order_no) {
		return dao.getOne(order_no);
	}
	
	
}
