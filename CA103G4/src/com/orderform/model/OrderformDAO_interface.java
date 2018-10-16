package com.orderform.model;

import java.sql.Connection;
import java.util.*;

import com.orderinvoice.model.*;
import com.delivery.model.*;

public interface OrderformDAO_interface {
	
	public void insert(OrderformVO orderformVO);

	public void update(OrderformVO orderformVO);

	public void delete(String order_no);

	public OrderformVO findByPrimaryKey(String order_no);

	public List<OrderformVO> getNotOk();

	public List<OrderformVO> getMore(String order_no,String delivery_no);
	
	public List<OrderformVO> getAll();
	
	public OrderformVO insertWithInvoice(OrderformVO orderformVO , List<OrderinvoiceVO> list);

	public void updateWithDelivery(OrderformVO orderformVO , Connection con);
	
	public List<OrderformVO> getDel();

	public List<OrderformVO> getOrderNoByMemNo(String mem_No);

	public OrderformVO findByForeignKey(String mem_No);
	
	public List<OrderformVO> forOut();
	
	public void updateByOrdNo(OrderformVO orderformVO);
	
	public List<OrderformVO> getByOk(String delivery_no);
	
	public List<OrderformVO> getAll(Map<String, String[]> map);
	
	public void updateOk(String ord_no);
	
}
