package android.com.orderinvoice.model;

import java.sql.Connection;
import java.util.*;

public interface OrderinvoiceDAO_interface {
	
	public String getOrderNo(String invo_no);

	public void insert(OrderinvoiceVO orderinvoiceVO, Connection con);
	
	public void insert(OrderinvoiceVO orderinvoiceVO);

	public void update(OrderinvoiceVO orderinvoiceVO);
	
	public void updateStatus(String invo_no);

	public void delete(String orderinvoiceVO);

	public List<OrderinvoiceVO> findByOrder_no(String order_no);
	
	public List<OrderinvoiceVO> findByOrder_no_withMenu(String order_no, Integer invo_status);

	public List<OrderinvoiceVO> getAll();
}
