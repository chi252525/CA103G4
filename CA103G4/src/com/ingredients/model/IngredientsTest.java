package com.ingredients.model;

import java.util.List;

public class IngredientsTest {

	public static void main(String[] args) {
		IngredientsJDBCDAO dao = new IngredientsJDBCDAO();
		
//		//新增
//		IngredientsVO ingdt1 = new IngredientsVO();
//		ingdt1.setingdt_Id("I0001");
//		ingdt1.setingdtc_Id("T0001");
//		ingdt1.setingdt_Name("細拉麵");
//		ingdt1.setingdt_Status(1);
//		ingdt1.setingdt_Point("1");
//		ingdt1.setingdt_Unit("一份");
//		ingdt1.setingdt_Price(20);
//		ingdt1.setingdt_Photo(null);
//		dao.insert(ingdt1);
		
//		//查一個餐點名稱的內含那些食材
		List<IngredientsVO> list = dao.findIngtByCustomNo("C0000000001");
		for (IngredientsVO ingdt : list) {
			System.out.print(ingdt.getingdt_Id() + ",");
			System.out.print(ingdt.getingdtc_Id() + ",");
			System.out.print(ingdt.getingdt_Name() + ",");
			System.out.print(ingdt.getingdt_Status() + ",");
			System.out.print(ingdt.getingdt_Point() + ",");
			System.out.print(ingdt.getingdt_Unit() + ",");
			System.out.print(ingdt.getingdt_Price() + ",");
			System.out.print(ingdt.getingdt_Photo() + ",");
			System.out.println();
		}
		
		//修改
//		IngredientsVO ingdt2 = new IngredientsVO();
//		ingdt2.setingdt_Id("I0003");
//		ingdt2.setingdtc_Id("T0001");
//		ingdt2.setingdt_Name("炸豬排");
//		ingdt2.setingdt_Status(1);
//		ingdt2.setingdt_Point("2");
//		ingdt2.setingdt_Unit("一份");
//		ingdt2.setingdt_Price(60);
//		ingdt2.setingdt_Photo(null);
//		dao.update(ingdt2);
		
		//刪除
//		dao.delete("I0061");
		
//		//查詢
//		List<IngredientsVO> list = dao.getAll();
//		for (IngredientsVO ingdt : list) {
//			System.out.print(ingdt.getingdt_Id() + ",");
//			System.out.print(ingdt.getingdtc_Id() + ",");
//			System.out.print(ingdt.getingdt_Name() + ",");
//			System.out.print(ingdt.getingdt_Status() + ",");
//			System.out.print(ingdt.getingdt_Point() + ",");
//			System.out.print(ingdt.getingdt_Unit() + ",");
//			System.out.print(ingdt.getingdt_Price() + ",");
//			System.out.print(ingdt.getingdt_Photo() + ",");
//			System.out.println();
//		}
		
	}
	
}
