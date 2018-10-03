package com.delivery.model;

import java.util.*;

import com.orderform.model.OrderformVO;

public interface DeliveryDAO_interface {

	public void insert(DeliveryVO deliveryVO, List<OrderformVO> list);

	public void update(DeliveryVO deliveryVO);

	public DeliveryVO findByPrimaryKey(String deliv_no);

	public List<DeliveryVO> getByThreeKey(String deliv_no, String emp_no, String deliv_status);

	public List<DeliveryVO> getByStatus();
	
	public List<DeliveryVO> getAll();
	
	public List<String> getOut();

}
