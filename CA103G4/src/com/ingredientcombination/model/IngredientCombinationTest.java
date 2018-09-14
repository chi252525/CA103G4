package com.ingredientcombination.model;

import java.util.List;

public class IngredientCombinationTest {

	public static void main(String[] args) {
		IngredientCombinationDAO dao = new IngredientCombinationDAO();
	
//		//新增
//		IngredientCombinationVO ingdt1 = new IngredientCombinationVO();
//		ingdt1.setCustom_No("C0000000006");
//		ingdt1.setIngdt_Id("I0006");
//
//		dao.insert(ingdt1);
		
		//修改
		IngredientCombinationVO ingdt2 = new IngredientCombinationVO();
		ingdt2.setCustom_No("C0000000005");
		ingdt2.setIngdt_Id("I0002");

		dao.update(ingdt2);
//		
//		//刪除
//		dao.delete("C0000000006");
//		
//		//查詢
//		List<IngredientCombinationVO> list = dao.getAll();
//		for (IngredientCombinationVO ingdt : list) {
//			System.out.print(ingdt.getCustom_No() + ",");
//			System.out.print(ingdt.getIngdt_Id() + ",");
//
//			System.out.println();
//		}
//		
	}
	
	
}
