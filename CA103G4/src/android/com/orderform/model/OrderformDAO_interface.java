package android.com.orderform.model;

import java.sql.Connection;
import java.util.*;

public interface OrderformDAO_interface {
	
	public void insert(OrderformVO orderformVO);

	public void update(OrderformVO orderformVO);
	
	public void updateOrderStatus(String order_no);

	public void delete(String order_no);

	public OrderformVO findByPrimaryKey(String order_no);

	public List<OrderformVO> getNotOk();

	public List<OrderformVO> getMore(String order_no,String delivery_no);
	
	public List<OrderformVO> getAll();
	
	String addWithOrderItem(OrderformVO orderformVO, List<OrderInvoiceVO> orderList, String coupSn);
	
	public List<OrderformVO> findByOrderTypeAndStatus(Integer order_type, Integer order_status);
	
	public List<OrderformVO> findByDeliveryNo(String delivery_no);
	
	public OrderformVO findByDekNoAndOrderStatus(String dek_no, Integer order_status);

}
