package com.couponhistory.model;

import java.util.List;

import com.coucat.model.CoucatVO;
import com.coupon.model.CouponVO;


public class CouponhistoryService {
	private CouponhistoryDAO_interface dao;
	
	public CouponhistoryService() {
		dao=new CouponhistoryDAO();
	}
	public List<CouponhistoryVO> getCouponByMem(String mem_No){
		return dao.getCouponByMem(mem_No);
	}
	
	public void insertOneCouponRecord(String coup_sn,String mem_no,Integer coup_state ) {
		CouponhistoryVO chVO= new CouponhistoryVO();
		chVO.setCoup_sn(coup_sn);
		chVO.setMem_no(mem_no);
		chVO.setCoup_state(coup_state);
		dao.insert(chVO);
	}
	
	public List<CouponhistoryVO> getByMem(String mem_No){
		return dao.getByMem(mem_No);
	}
}
