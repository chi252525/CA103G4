package com.delivery.model;

import java.util.List;

public class DeliveryService {
	private DeliveryDAO_interface dao;
	
	public DeliveryService() {
		dao = new DeliveryDAO();
	}

	public DeliveryVO addDelivery(String branch_no, String emp_no, String deliv_status) {
		
		DeliveryVO deliveryVO = new DeliveryVO();
		
		deliveryVO.setBranch_no(branch_no);
		deliveryVO.setBranch_no(emp_no);
		deliveryVO.setBranch_no(deliv_status);
		dao.insert(deliveryVO);
		
		return deliveryVO;
	}
	
	public DeliveryVO updateDelivery(String deliv_status, String deliv_no) {
		
		DeliveryVO deliveryVO = new DeliveryVO();
		
		deliveryVO.setDeliv_status(deliv_status);
		deliveryVO.setDeliv_no(deliv_no);
		dao.update(deliveryVO);
		
		return deliveryVO;
	}
	
	public DeliveryVO getOneDelivery(String deliv_no){
		return dao.findByPrimaryKey(deliv_no);
	}
	
	public List<DeliveryVO> getSelect(String deliv_no, String emp_no, String deliv_status) {
		return dao.getByThreeKey(deliv_no, emp_no, deliv_status);
	}
	
	
	public List<DeliveryVO> getAll(){
		return dao.getAll();
	}
	
	
	
}
