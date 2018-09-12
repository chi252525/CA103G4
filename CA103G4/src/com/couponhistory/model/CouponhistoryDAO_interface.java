package com.couponhistory.model;

import java.util.*;

public interface CouponhistoryDAO_interface {
	
	public void insert(CouponhistoryVO couponhistoryVO);

	public void update(CouponhistoryVO couponhistoryVO);

	public void delete(String coup_sn);

	public CouponhistoryVO findByCoup_sn(String coup_sn);

	public List<CouponhistoryVO> getAll();
}
