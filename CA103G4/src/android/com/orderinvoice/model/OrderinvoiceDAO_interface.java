package android.com.orderinvoice.model;

import java.sql.Connection;
import java.util.*;

public interface OrderinvoiceDAO_interface {

	public void insert(OrderinvoiceVO orderinvoiceVO, Connection con);
	
	public void insert(OrderinvoiceVO orderinvoiceVO);

	public void update(OrderinvoiceVO orderinvoiceVO);

	public void delete(String orderinvoiceVO);

	public List<OrderinvoiceVO> findByOrder_no(String order_no);

	public List<OrderinvoiceVO> getAll();
}
