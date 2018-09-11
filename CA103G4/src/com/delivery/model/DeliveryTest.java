package com.delivery.model;

import java.util.List;

import com.delivery.model.DeliveryVO;
import com.delivery.model.DeliveryJDBCDAO;

public class DeliveryTest {
	public static void main(String[] args) {

		DeliveryJDBCDAO dao = new DeliveryJDBCDAO();

		// 新增
		DeliveryVO deliveryVO1 = new DeliveryVO();
//		deliveryVO1.setDeliv_no("'0'||LPAD(to_char(delivery_seq.NEXTVAL), 5, '0')"); //可不寫
//		deliveryVO1.setBranch_no("001");
//		deliveryVO1.setEmp_no("000004");
//		deliveryVO1.setDeliv_status("1");
//		dao.insert(deliveryVO1);

		// 修改
//		DeliveryVO deliveryVO2 = new DeliveryVO();
//		deliveryVO2.setDeliv_no("000009");
//		deliveryVO2.setBranch_no("001");
//		deliveryVO2.setEmp_no("000003");
//		deliveryVO2.setDeliv_status("2");
//		dao.update(deliveryVO2);

//		 查詢 one
//		DeliveryVO deliveryVO3 = dao.findByThreeKey("000001","001","1");
//		System.out.print(deliveryVO3.getDeliv_no() + ",");
//		System.out.print(deliveryVO3.getBranch_no() + ",");
//		System.out.print(deliveryVO3.getEmp_no() + ",");
//		System.out.println(deliveryVO3.getDeliv_status() + ",");
//		System.out.println("---------------------");

		// 查詢 all
//		List<DeliveryVO> list = dao.getAll();
//		for (DeliveryVO adeliv : list) {
//			System.out.print(adeliv.getDeliv_no() + ",");
//			System.out.print(adeliv.getBranch_no() + ",");
//			System.out.print(adeliv.getEmp_no() + ",");
//			System.out.println(adeliv.getDeliv_status() + ",");
//			System.out.println();
//		}
	}
}
