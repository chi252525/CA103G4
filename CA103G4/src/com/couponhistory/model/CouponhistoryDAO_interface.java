package com.couponhistory.model;

import java.util.*;

public interface CouponhistoryDAO_interface {
	
	public void insert(CouponhistoryVO couponhistoryVO);

	public void update(CouponhistoryVO couponhistoryVO);

	public void delete(String coup_sn);

	public List<CouponhistoryVO> findByCoup_State(Integer coup_state);
	public List<CouponhistoryVO> getAll();
	//取得會員有的的優惠卷
	public List<CouponhistoryVO> getCouponByMem(String mem_No);
	
	public List<CouponhistoryVO> getByMem(String mem_No);
}
