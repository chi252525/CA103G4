package com.orderinvoice.model;

import java.util.List;

import com.orderform.model.OrderformJDBCDAO;

public class OrderinvoiceTest {
	public static void main(String[] args) {
		OrderinvoiceJDBCDAO dao = new OrderinvoiceJDBCDAO();

		// 新增(完成)
//		OrderinvoiceVO orderinvoiceVO1 = new OrderinvoiceVO();
//		orderinvoiceVO1.setInvo_no("'IN'||LPAD(to_char(oredrinvoice_seq.NEXTVAL), 9, '0')"); //可不寫
//		orderinvoiceVO1.setOrder_no("O000000004");
//		orderinvoiceVO1.setMenu_no("M003");
//		orderinvoiceVO1.setCustom_no(null);
//		orderinvoiceVO1.setInvo_status(1);
//		dao.insert(orderinvoiceVO1);

		// 修改(完成)
//		OrderinvoiceVO orderinvoiceVO2 = new OrderinvoiceVO();
//		orderinvoiceVO2.setInvo_status(2);
//		orderinvoiceVO2.setInvo_no("IN000000001");
//		orderinvoiceVO2.setOrder_no("O000000001");
//		dao.update(orderinvoiceVO2);

//		// 查詢 one (完成) ?
//		OrderinvoiceVO orderinvoiceVO3 = dao.findByPrimaryKey("IN000000005");
//		System.out.print(orderinvoiceVO3.getInvo_no() + ",");
//		System.out.print(orderinvoiceVO3.getOrder_no() + ",");
//		System.out.print(orderinvoiceVO3.getMenu_no() + ",");
//		System.out.print(orderinvoiceVO3.getCustom_no() + ",");
//		System.out.print(orderinvoiceVO3.getInvo_status() + ",");
//		System.out.println("---------------------");

		// 查詢 more(完成)
//		List<OrderinvoiceVO> list = dao.findByOrder_no("O000000003");
//		for (OrderinvoiceVO adeliv : list) {
//			System.out.print(adeliv.getInvo_no() + ",");
//			System.out.print(adeliv.getOrder_no() + ",");
//			System.out.print(adeliv.getMenu_no() + ",");
//			System.out.println(adeliv.getCustom_no() + ",");
//			System.out.println(adeliv.getInvo_status() + ",");
//			System.out.println();
//		}

		// 查詢 all (完成)
//		List<OrderinvoiceVO> list = dao.getAll();
//		for (OrderinvoiceVO adeliv : list) {
//			System.out.print(adeliv.getInvo_no() + ",");
//			System.out.print(adeliv.getOrder_no() + ",");
//			System.out.print(adeliv.getMenu_no() + ",");
//			System.out.print(adeliv.getCustom_no() + ",");
//			System.out.print(adeliv.getInvo_status() + ",");
//			System.out.println();
//		}
	}
}
