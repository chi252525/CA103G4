package com.custommeals.model;

import java.util.List;

public class CustommealsJDBCDAO {
	
	public static void main(String[] args) {
		CustommealsDAO dao = new CustommealsDAO();
		
		//新增
		CustommealsVO custom1 = new CustommealsVO();
		custom1.setCustom_No("C0000000001");
		custom1.setMem_No("M000001");
		custom1.setCustom_Name("海陸雙拼大餐");
		custom1.setCustom_Price(480);
		custom1.setCustom_Photo(null);
		dao.insert(custom1);
		
		//修改
		CustommealsVO custom2 = new CustommealsVO();
		custom2.setCustom_No("C0000000004");
		custom2.setMem_No("M000004");
		custom2.setCustom_Name("咖哩龍蝦拉麵");
		custom2.setCustom_Price(490);
		custom2.setCustom_Photo(null);
		dao.update(custom2);
		
		//刪除
		dao.delete("C0000000004");
		
		//查詢
		List<CustommealsVO> list = dao.getAll();
		for (CustommealsVO custom : list) {
			System.out.print(custom.getCustom_No() + ",");
			System.out.print(custom.getMem_No() + ",");
			System.out.print(custom.getCustom_Name() + ",");
			System.out.print(custom.getCustom_Price() + ",");
			System.out.print(custom.getCustom_Photo() + ",");

			System.out.println();
		}
		
	}
}
