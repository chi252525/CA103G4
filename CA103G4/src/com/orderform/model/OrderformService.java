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
		orderformInVO = dao.insertWithInvoice(orderformVO, list);

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

	public OrderformVO getOneOrderform(String order_no) {
		dao.findByPrimaryKey(order_no);
		return dao.findByPrimaryKey(order_no);
	}
	

	public List<OrderformVO> getForOut() {
		return dao.forOut();
	}
	
	public OrderformVO updateForSta(String order_no, int order_status) {
		OrderformVO oVO = new OrderformVO();
		oVO.setOrder_no(order_no);
		oVO.setOrder_status(order_status);
		
		dao.updateByOrdNo(oVO);
		return null;
	}
	
	public List<OrderformVO> getAll(){
		return dao.getAll();
	}
	
	
	
}
