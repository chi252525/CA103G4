package android.com.couponhistory.model;

import java.sql.Connection;
import java.util.*;

import android.com.coupon.model.*;

public interface CouponhistoryDAO_interface {
	
	public void insert(CouponhistoryVO couponhistoryVO);

	public void update(CouponhistoryVO couponhistoryVO);

	public void delete(String coup_sn);

	public List<CouponhistoryVO> findByCoup_State(Integer coup_state);
	public List<CouponhistoryVO> getAll();
	//取得會員有的優惠卷
	public List<CouponhistoryVO> getCouponByMem(String mem_No);
	//取得會員有的優惠卷 CouponVO裡的屬性包含CoucatVO
	public List<CouponVO> getCouponByMemNo(String mem_No);
	
}
