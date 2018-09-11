package com.coupon.model;

import com.coupon.model.CouponDAO;
import com.coupon.model.CouponVO;

public class CouponTest {

	public static void main(String[] args) {
//		insert  done
//		CouponVO cv1=new CouponVO();
//	cv1.setCoucat_No("20180911-000002");
	CouponDAO dao =new CouponDAO();
//	dao.insert(cv1,10);
	
//	UPDATESTATUS_FALSE done
//	CouponVO cv2=new CouponVO();
////	cv2.setCoucat_No("20180911-000001");
//	cv2.setCoup_Sn("M-00000000006");
//	dao.updateStatus(cv2);
//	 
	 //FINDBYCOUCAT_NO done
	CouponVO cv3=dao.findByCoucatNo("20180911-000002");
	 System.out.println(cv3.getCoucat_No());
	 System.out.println(cv3.getCoup_Sn());
	 System.out.println(cv3.getCoup_Status());
	 System.out.println("=========================");
	
	}

	
}
