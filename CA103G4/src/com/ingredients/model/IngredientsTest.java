package com.ingredients.model;

import java.util.List;

public class IngredientsTest {

	public static void main(String[] args) {
		IngredientsDAO dao = new IngredientsDAO();
		
//		//?–°å¢?
//		IngredientsVO ingdt1 = new IngredientsVO();
//		ingdt1.setIngdt_Id("I0001");
//		ingdt1.setIngdtc_Id("T0001");
//		ingdt1.setIngdt_Name("ç´°æ?‰éºµ");
//		ingdt1.setIngdt_Status(1);
//		ingdt1.setIngdt_Point("1");
//		ingdt1.setIngdt_Unit("ä¸?ä»?");
//		ingdt1.setIngdt_Price(20);
//		dao.insert(ingdt1);
		
		//ä¿®æ”¹
		IngredientsVO ingdt2 = new IngredientsVO();
		ingdt2.setIngdt_Id("I0003");
		ingdt2.setIngdtc_Id("T0001");
		ingdt2.setIngdt_Name("?‚¸è±¬æ??");
		ingdt2.setIngdt_Status(1);
		ingdt2.setIngdt_Point("2");
		ingdt2.setIngdt_Unit("ä¸?ä»?");
		ingdt2.setIngdt_Price(60);
		dao.update(ingdt2);
		
		//?ˆª?™¤
//		dao.delete("I0061");
		
//		//?Ÿ¥è©?
//		List<IngredientsVO> list = dao.getAll();
//		for (IngredientsVO ingdt : list) {
//			System.out.print(ingdt.getIngdt_Id() + ",");
//			System.out.print(ingdt.getIngdtc_Id() + ",");
//			System.out.print(ingdt.getIngdt_Name() + ",");
//			System.out.print(ingdt.getIngdt_Status() + ",");
//			System.out.print(ingdt.getIngdt_Point() + ",");
//			System.out.print(ingdt.getIngdt_Unit() + ",");
//			System.out.print(ingdt.getIngdt_Price() + ",");
//			
//			System.out.println();
//		}
		
	}
	
}
