package com.ingclass.model;

import java.util.List;

public class IngclassTest {

	public static void main(String[] args) {
		IngclassDAO dao = new IngclassDAO();
	
//		//?–°å¢?
//		IngclassVO ingdtc1 = new IngclassVO();
//		ingdtc1.setIngdtc_Id("T0001");
//		ingdtc1.setIngdtc_Name("éºµæ??");
//
//		dao.insert(ingdtc1);
		
		//ä¿®æ”¹
		IngclassVO ingdtc2 = new IngclassVO();
		ingdtc2.setIngdtc_Id("T0011");
		ingdtc2.setIngdtc_Name("RR");

		dao.update(ingdtc2);
//		
		//?ˆª?™¤
//		dao.delete("T0004");
//		
//		//?Ÿ¥è©?
//		List<IngclassVO> list = dao.getAll();
//		for (IngclassVO ingdtc : list) {
//			System.out.print(ingdtc.getIngdtc_Id() + ",");
//			System.out.print(ingdtc.getIngdtc_Name() + ",");
//
//			System.out.println();
//		}
		
	}
}
