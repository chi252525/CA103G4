package com.couponhistory.model;

import java.util.List;


public class CouponhistoryService {
	private CouponhistoryDAO_interface dao;
	
	public CouponhistoryService() {
		dao=new CouponhistoryDAO();
	}
	public List<CouponhistoryVO> getCouponByMem(String mem_No){
		return dao.getCouponByMem(mem_No);
	}
}
