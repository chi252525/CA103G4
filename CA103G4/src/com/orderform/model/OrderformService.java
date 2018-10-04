package com.orderform.model;

import java.sql.Connection;
import java.util.List;

import com.custommeals.model.CustommealsVO;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformService {
	private OrderformDAO_interface dao;

	public OrderformService() {
		dao = new OrderformDAO();
	}

	public OrderformVO addOrd(OrderformVO orderformVO, List<OrderinvoiceVO> list) {

		OrderformVO orderformInVO = new OrderformVO();
		dao.insertWithInvoice(orderformVO, list);

		return orderformInVO;
	}

	public OrderformVO upOrdDel(OrderformVO orderformVO, Connection con) {

		OrderformVO ordVO = new OrderformVO();
		dao.updateWithDelivery(orderformVO, con);

		return ordVO;
	}
	
	public List<OrderformVO> getDeliv() {
		return dao.getDel();
	}
	
	public List<OrderformVO> getOrderNoByMemNo(String mem_No) {
		return dao.getOrderNoByMemNo(mem_No);
	}

	public OrderformVO getOneOrderform(String mem_No) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
