package com.menu.model;

import java.util.List;

public class MenuJDBCDAO {

	public static void main(String[] args) {
		MenuDAO dao = new MenuDAO();
		
		//新增
		MenuVO menu1 = new MenuVO();
		menu1.setMenu_No("M001");
		menu1.setMenu_Id("極致豚骨玉子麵");
		menu1.setMenu_Type("經典餐點");
		menu1.setMenu_Price(299);
		menu1.setMenu_Intro("48小時溫火慢熬精緻豚骨湯頭，搭配叉燒肉及溫泉蛋");
		menu1.setMenu_Photo(null);
		menu1.setMenu_Status(1);
		dao.insert(menu1);
		
		
		
		//修改
		MenuVO menu2 = new MenuVO();
		menu2.setMenu_No("M003");
		menu2.setMenu_Id("地獄麻辣拉麵");
		menu2.setMenu_Type("經典餐點");
		menu2.setMenu_Price(269);
		menu2.setMenu_Intro("川味麻辣湯頭，搭配辣味肉燥與叉燒肉");
		menu2.setMenu_Photo(null);
		menu2.setMenu_Status(1);
		dao.update(menu2);
		
		//刪除
		dao.delete("M003");
		
		//查詢
		List<MenuVO> list = dao.getAll();
		for (MenuVO menu : list) {
			System.out.print(menu.getMenu_No() + ",");
			System.out.print(menu.getMenu_Id() + ",");
			System.out.print(menu.getMenu_Type() + ",");
			System.out.print(menu.getMenu_Price() + ",");
			System.out.print(menu.getMenu_Intro() + ",");
			System.out.print(menu.getMenu_Photo() + ",");
			System.out.print(menu.getMenu_Status() + ",");

			System.out.println();
		}
		
	}
	
	
}





