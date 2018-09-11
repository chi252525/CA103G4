package com.coupon.model;

public interface CouponDAO_interface {
	void insert(CouponVO couponVO,Integer coucat_Amo);
	void updateStatus(CouponVO couponVO);
	CouponVO findByCoucatNo(String coucat_No);  
	
}
