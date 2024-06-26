package android.com.coupon.model;

import java.sql.Connection;
import java.util.List;


public interface CouponDAO_interface {
	void insert(CouponVO couponVO,Integer coucat_Amo);
	void insertbyGenaratedKeys(Connection con,String coucat_No,Integer coucat_Amo);
	void updateStatus(CouponVO couponVO);
	List<CouponVO> findByCoucatNo(String coucat_No) ;  
	CouponVO getOneCoupon(String coup_Sn);
	CouponVO getOneCouponWithCoucat(String coup_Sn);
	public void updateOrderNoAndCoupState(String coup_sn, String orderNo, Connection con);
	
	
}
