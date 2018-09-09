package com.Coupon.model;

import java.util.List;


public interface CouponDAO_interface {
	void insert(CouponVO couponVO,Integer coucat_Amo);
	void updateStatus(CouponVO couponVO);
	CouponVO findByCoucatNo(String coucat_No) ;  
	
}
