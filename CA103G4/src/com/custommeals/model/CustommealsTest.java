package com.custommeals.model;

import java.util.List;

public class CustommealsTest {
	
	public static void main(String[] args) {
		CustommealsDAO dao = new CustommealsDAO();
		
//		//新增
//		CustommealsVO custom1 = new CustommealsVO();
//		custom1.setcustom_No("C0000000001");
//		custom1.setmem_No("M000001");
//		custom1.setcustom_Name("海陸雙拼大餐");
//		custom1.setcustom_Price(480);
//		custom1.setcustom_Photo(null);
//		dao.insert(custom1);
		
//		//修改
//		CustommealsVO custom2 = new CustommealsVO();
//		custom2.setcustom_No("C0000000006");
//		custom2.setmem_No("M000006");
//		custom2.setcustom_Name("咖哩龍蝦拉麵");
//		custom2.setcustom_Price(490);
//		custom2.setcustom_Photo(null);
//		dao.update(custom2);
//		
//		//刪除
//		dao.delete("C0000000004");

		//查詢
		List<CustommealsVO> list = dao.getAll();
		for (CustommealsVO custom : list) {
			System.out.print(custom.getcustom_No() + ",");
			System.out.print(custom.getmem_No() + ",");
			System.out.print(custom.getcustom_Name() + ",");
			System.out.print(custom.getcustom_Price() + ",");
			System.out.print(custom.getcustom_Photo() + ",");

			System.out.println();
		}
		
	}
}
