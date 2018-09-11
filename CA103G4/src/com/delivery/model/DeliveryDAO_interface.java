package com.delivery.model;

import java.util.*;

public interface DeliveryDAO_interface {

	public void insert(DeliveryVO deliveryVO);

	public void update(DeliveryVO deliveryVO);

	public DeliveryVO findByThreeKey(String emp_no, String branch_no, String deliv_status);

	public List<DeliveryVO> getAll();

}
