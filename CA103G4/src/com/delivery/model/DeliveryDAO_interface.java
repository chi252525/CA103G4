package com.delivery.model;

import java.util.*;

public interface DeliveryDAO_interface {

	public void insert(DeliveryVO deliveryVO);

	public void update(DeliveryVO deliveryVO);

	public void delete(String deliv_no);

	public DeliveryVO findByPrimaryKey(String deliv_no);

	public List<DeliveryVO> getAll();

}
