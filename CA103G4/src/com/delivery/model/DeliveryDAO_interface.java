package com.delivery.model;

import java.util.*;

public interface DeliveryDAO_interface {

	public void insert(DeliveryVO deliveryVO);

	public void update(DeliveryVO deliveryVO);

	public DeliveryVO findByDeliv_no(String deliv_no);

	public List<DeliveryVO> getByThreeKey(String deliv_no, String emp_no, String deliv_status);

	public List<DeliveryVO> getAll();

}
