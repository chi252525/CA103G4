package com.couponhistory.model;

import java.util.*;

public interface CouponhistoryDAO_interface {
	
	public void insert(CouponhistoryVO couponhistoryVO);

	public void update(CouponhistoryVO couponhistoryVO);

	public void delete(String coup_sn);

	public List<CouponhistoryVO> findByCoup_State(Integer coup_state);

	public List<CouponhistoryVO> getAll();
}
