package com.coupon.model;

import java.sql.Connection;

public interface CouponDAO_interface {
	void insert(CouponVO couponVO,Integer coucat_Amo);
	void insertbyGenratedKeys(Connection con,String coucat_No,Integer coucat_Amo);
	void updateStatus(CouponVO couponVO);
	CouponVO findByCoucatNo(String coucat_No) ;  
	
}
