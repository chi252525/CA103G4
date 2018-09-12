package com.couponhistory.model;

import java.util.List;

import com.delivery.model.DeliveryJDBCDAO;
import com.delivery.model.DeliveryVO;

public class CouponhistoryTest {
	public static void main(String[] args) {
		CouponhistoryJDBCDAO dao = new CouponhistoryJDBCDAO();
		
//		// 新增(完成)
//		CouponhistoryVO couponhistoryVO1 = new CouponhistoryVO();
//
//		couponhistoryVO1.setCoup_sn("M-00000000021"); //coupon有才能新增
//		couponhistoryVO1.setMem_no("M000002");
//		couponhistoryVO1.setOrder_no(null);
//		couponhistoryVO1.setCoup_state(0);
//		dao.insert(couponhistoryVO1);

		//is註解
//		if(order_no != null) {
//			couponhistoryVO2.setCoup_state(1);
//			couponhistoryVO2.setCoup_sn("M-00000000020");
//		} else if (order_no == 0) {
//			couponhistoryVO2.setCoup_state(1); or couponhistoryVO2.setCoup_state(1);
//			couponhistoryVO2.setCoup_sn("M-00000000020");
//		}
		/*=========================================================================*/
		// 修改(完成)
//		CouponhistoryVO couponhistoryVO2 = new CouponhistoryVO();
//		couponhistoryVO2.setOrder_no("O000000002");
//		couponhistoryVO2.setCoup_state(1);
//		couponhistoryVO2.setCoup_sn("M-00000000020");
//		dao.update(couponhistoryVO2);

//		 查詢 one
//		CouponhistoryVO couponhistoryVO3 = dao.findByCoup_sn("M-00000000020");
//		System.out.print(couponhistoryVO3.getCoup_sn() + ",");
//		System.out.print(couponhistoryVO3.getCoup_state() + ",");
//		System.out.println("---------------------");

		// 查詢 more
//		List<DeliveryVO> list = dao.getByThreeKey(null,"E000000001",null);
//		for (DeliveryVO adeliv : list) {
//			System.out.print(adeliv.getDeliv_no() + ",");
//			System.out.print(adeliv.getBranch_no() + ",");
//			System.out.print(adeliv.getEmp_no() + ",");
//			System.out.println(adeliv.getDeliv_status() + ",");
//			System.out.println();
//		}
		
		// 查詢 all(完成)
//		List<CouponhistoryVO> list = dao.getAll();
//		for (CouponhistoryVO adeliv : list) {
//			System.out.print(adeliv.getCoup_sn() + ",");
//			System.out.println(adeliv.getCoup_state() + ",");
//			System.out.println();
//		}
		

	}
}
