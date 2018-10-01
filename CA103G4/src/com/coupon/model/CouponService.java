package com.coupon.model;

import java.util.List;
import com.coucat.model.CoucatVO;


public class CouponService {
private CouponDAO_interface dao;
	
	public CouponService() {
		dao=new CouponDAO();
	}
	
	public CouponVO getOneCoupon(String coup_Sn) {
		return dao.getOneCoupon(coup_Sn);
	}
	//測試用
	public void insertCouponSelfOnly(String coucat_No, Integer coucat_Amo) {
		CouponVO couponVO= new CouponVO();
		//Coucat來的數量(測試用)
		CoucatVO coucatVO= new CoucatVO();
		coucatVO.setCoucat_Amo(coucat_Amo);
		couponVO.setCoucat_No(coucat_No);
		dao.insert(couponVO, coucat_Amo);
	}
	
	public void updateCouoponStatus(String coup_Sn) {
		CouponVO couponVO= new CouponVO();
		couponVO.setCoup_Sn(coup_Sn);
		dao.updateStatus(couponVO);
		
	}
	
	public List<CouponVO> getCouponByCata(String coucat_No){
		return dao.findByCoucatNo(coucat_No);
	}
	
	
	
}
