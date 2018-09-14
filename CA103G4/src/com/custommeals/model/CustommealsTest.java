package com.custommeals.model;

import java.util.List;

public class CustommealsTest {
	
	public static void main(String[] args) {
		CustommealsDAO dao = new CustommealsDAO();
		
//		//?–°å¢?
//		CustommealsVO custom1 = new CustommealsVO();
//		custom1.setCustom_No("C0000000001");
//		custom1.setMem_No("M000001");
//		custom1.setCustom_Name("æµ·é™¸??™æ‹¼å¤§é??");
//		custom1.setCustom_Price(480);
//		custom1.setCustom_Photo(null);
//		dao.insert(custom1);
		
//		//ä¿®æ”¹
//		CustommealsVO custom2 = new CustommealsVO();
//		custom2.setCustom_No("C0000000006");
//		custom2.setMem_No("M000006");
//		custom2.setCustom_Name("??–å“©é¾è¦??‰éºµ");
//		custom2.setCustom_Price(490);
//		custom2.setCustom_Photo(null);
//		dao.update(custom2);
//		
//		//?ˆª?™¤
//		dao.delete("C0000000004");

		//?Ÿ¥è©?
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
