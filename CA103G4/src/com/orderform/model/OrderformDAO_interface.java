package com.orderform.model;

import java.util.*;

public interface OrderformDAO_interface {
	
	public void insert(OrderformVO orderformVO);

	public void update(OrderformVO orderformVO);

	public void delete(String order_no);

	public OrderformVO findByTwoKey(int order_status, int order_pstatus);

	public List<OrderformVO> getAll();

}
