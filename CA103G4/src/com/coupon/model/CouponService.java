package com.coupon.model;

import java.util.List;


public class CouponService {
private CouponDAO_interface dao;
	
	public CouponService() {
		dao=new CouponDAO();
	}
	
	public CouponVO getOneCoupon(String coup_Sn) {
		return dao.getOneCoupon(coup_Sn);
	}
	
	
}
