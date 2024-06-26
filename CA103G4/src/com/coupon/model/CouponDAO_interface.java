package com.coupon.model;

import java.sql.Connection;
import java.util.List;


public interface CouponDAO_interface {
	void insert(CouponVO couponVO,Integer coucat_Amo);
	void insertbyGenaratedKeys(Connection con,String coucat_No,Integer coucat_Amo);
	void updateStatus(CouponVO couponVO);
	List<CouponVO> findByCoucatNo(String coucat_No);  
	CouponVO getOneCoupon(String coup_Sn);
	List<CouponVO> findByCoucatNo_CP0(String coucat_No,String coucat_Status); 
	CouponVO findCoucatByCoupSn(String coup_Sn);
}
