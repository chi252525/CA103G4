package com.orderinvoice.model;

import java.util.*;

public interface OrderinvoiceDAO_interface {

	public void insert(OrderinvoiceVO orderinvoiceVO);

	public void update(OrderinvoiceVO orderinvoiceVO);

	public void delete(String orderinvoiceVO);

	public OrderinvoiceVO findByPrimaryKey(String orderinvoiceVO);

	public List<OrderinvoiceVO> getAll();
}
