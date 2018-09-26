package com.custommeals.model;

import java.util.ArrayList;
import java.util.List;
import com.ingredientcombination.model.IngredientCombinationVO;


public class CustommealsTest {
	
	public static void main(String[] args) {
		CustommealsJDBCDAO dao = new CustommealsJDBCDAO();
		
//		//新增
//		CustommealsVO custom1 = new CustommealsVO();
//		custom1.setcustom_No("C0000000001");
//		custom1.setmem_No("M000001");
//		custom1.setcustom_Name("eeeeee餐");
//		custom1.setcustom_Price(480);
//		dao.insert(custom1);
		
		
		CustommealsVO custom2 = new CustommealsVO();
		custom2.setcustom_No("C0000000001");
		custom2.setmem_No("M000001");
		custom2.setcustom_Name("海陸雙拼大餐");
		custom2.setcustom_Price(480);

		
		
		List<IngredientCombinationVO> list2 = new ArrayList();
		IngredientCombinationVO ingt =new 	IngredientCombinationVO();
		ingt.setIngdt_Id("I0001");
		IngredientCombinationVO ingt2 =new 	IngredientCombinationVO();
		ingt2.setIngdt_Id("I0002");
		list2.add(ingt);
		list2.add(ingt2);
		dao.insertWithIngredientCombination(custom2,list2);
		
		
		
		
		
//		//修改
//		CustommealsVO custom2 = new CustommealsVO();
//		custom2.setcustom_No("C0000000006");
//		custom2.setmem_No("M000006");
//		custom2.setcustom_Name("咖哩龍蝦拉麵");
//		custom2.setcustom_Price(490);		
//		dao.update(custom2);
//		
		
//		//只修改餐點名
		CustommealsVO custom2 = new CustommealsVO();
		custom2.setcustom_No("C0000000006");
		custom2.setcustom_Name("咖哩龍蝦拉麵");
		dao.updateNameOnly("咖哩龍蝦拉麵","C0000000001");
		
		
		
//		//刪除
//		dao.delete("C0000000004");

		//查詢
//		List<CustommealsVO> list = dao.getAll();
//		for (CustommealsVO custom : list) {
//			System.out.print(custom.getcustom_No() + ",");
//			System.out.print(custom.getmem_No() + ",");
//			System.out.print(custom.getcustom_Name() + ",");
//			System.out.print(custom.getcustom_Price() + ",");
<<<<<<< HEAD
=======
//			System.out.print(custom.getcustom_Photo() + ",");
>>>>>>> 3a7874aa3b7145489ed16b794a0c414df9ae4bc2
//
//			System.out.println();
//		}
		
	}
}
