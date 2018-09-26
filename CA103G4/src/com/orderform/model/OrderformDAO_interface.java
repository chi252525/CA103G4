package com.orderform.model;

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
	
	public void insertWithInvoice(OrderformVO orderformVO , List<OrderinvoiceVO> list);

	public void updateWithDelivery(OrderformVO orderformVO , List<DeliveryVO> list);
	
}
