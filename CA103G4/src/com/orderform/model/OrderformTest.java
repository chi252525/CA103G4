package com.orderform.model;

import java.util.List;

import com.delivery.model.DeliveryJDBCDAO;

public class OrderformTest {
	public static void main(String[] args) {
		
		OrderformJDBCDAO dao = new OrderformJDBCDAO();
		
		// 新增(完成)
//		OrderformVO orderformVO1 = new OrderformVO();
//		orderformVO1.setOrder_no("'O'||LPAD(to_char(oredrform_seq.NEXTVAL), 9, '0')"); //可不寫
//		orderformVO1.setDek_no("D000000001");
//		orderformVO1.setMem_no("M000001");
//		orderformVO1.setBranch_no("0001");
//		orderformVO1.setDeliv_no("D-000000001");
//		orderformVO1.setOrder_type("1");
//		orderformVO1.setOrder_price(500);
//		orderformVO1.setOrder_status("4");
//		orderformVO1.setDeliv_addres(null);
//		orderformVO1.setOrder_pstatus("3");
//		dao.insert(deliveryVO1);

		// 修改(完成)
//		OrderformVO orderformVO2 = new OrderformVO();
//		orderformVO2.setOrder_status("2");
//		orderformVO2.setOrder_pstatus("1");
//		orderformVO2.setOrder_no("O000000005");
//		dao.update(orderformVO2);

//		// 查詢 one (完成)
//		OrderformVO orderformVO3 = dao.findByPrimaryKey("O000000002");
//		System.out.print(orderformVO3.getOrder_no() + ",");
//		System.out.print(orderformVO3.getDek_no() + ",");
//		System.out.print(orderformVO3.getMem_no() + ",");
//		System.out.println(orderformVO3.getBranch_no() + ",");
//		System.out.print(orderformVO3.getDeliv_no() + ",");
//		System.out.print(orderformVO3.getOrder_type() + ",");
//		System.out.print(orderformVO3.getOrder_price() + ",");
//		System.out.println(orderformVO3.getOrder_status() + ",");
//		System.out.print(orderformVO3.getDeliv_addres() + ",");
//		System.out.println(orderformVO3.getOrder_pstatus() + ",");
//		System.out.println("---------------------");

		// 查詢 notok(完成)
//		List<OrderformVO> lists = dao.getNotOk();
//		for (OrderformVO adeliv : lists) {
//			System.out.print(adeliv.getOrder_no() + ",");
//			System.out.print(adeliv.getDek_no() + ",");
////			System.out.print(adeliv.getMem_no() + ",");
////			System.out.println(adeliv.getBranch_no() + ",");
//			System.out.print(adeliv.getDeliv_no() + ",");
//			System.out.print(adeliv.getOrder_type() + ",");
//			System.out.print(adeliv.getOrder_price() + ",");
//			System.out.println(adeliv.getOrder_status() + ",");
//			System.out.print(adeliv.getDeliv_addres() + ",");
//			System.out.println(adeliv.getOrder_pstatus() + ",");
//			System.out.println();
//		}
		
		// 查詢 more(修改中)
//		List<DeliveryVO> list = dao.getByThreeKey(null,"E000000001",null);
//		for (DeliveryVO adeliv : list) {
//			System.out.print(adeliv.getDeliv_no() + ",");
//			System.out.print(adeliv.getBranch_no() + ",");
//			System.out.print(adeliv.getEmp_no() + ",");
//			System.out.println(adeliv.getDeliv_status() + ",");
//			System.out.println();
//		}

		// 查詢 all (完成)
//		List<OrderformVO> list = dao.getAll();
//		for (OrderformVO adeliv : list) {
//			System.out.print(adeliv.getOrder_no() + ",");
//			System.out.print(adeliv.getDek_no() + ",");
//			System.out.print(adeliv.getMem_no() + ",");
//			System.out.println(adeliv.getBranch_no() + ",");
//			System.out.print(adeliv.getDeliv_no() + ",");
//			System.out.print(adeliv.getOrder_type() + ",");
//			System.out.print(adeliv.getOrder_price() + ",");
//			System.out.println(adeliv.getOrder_status() + ",");
//			System.out.print(adeliv.getDeliv_addres() + ",");
//			System.out.println(adeliv.getOrder_pstatus() + ",");
//			System.out.println();
//		}

	}

}
