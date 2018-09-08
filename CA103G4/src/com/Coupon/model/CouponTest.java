package com.Coupon.model;

public class CouponTest {
//	insert  done
	public static void main(String[] args) {
//	CouponVO cv1=new CouponVO();
//	cv1.setCoucat_No("20180908-000002");
	CouponDAO dao =new CouponDAO();
//	dao.insert(cv1,10);
	
//	UPDATESTATUS_FALSE done
//	CouponVO cv2=new CouponVO();
//	cv2.setCoucat_No("20180908-000001");
//	cv2.setCoup_Sn("M-00000000001");
//	dao.updateStatus(cv2);
	 
	 //FINDBYCOUCAT_NO
	CouponVO cv3=dao.findByCoucatNo("20180908-000003");
	 System.out.println(cv3.getCoucat_No());
	 System.out.println(cv3.getCoup_Sn());
	 System.out.println(cv3.getCoup_Status());
	 System.out.println("=========================");
	
	}

	
}
