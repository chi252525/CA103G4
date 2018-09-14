package com.menu.model;

import java.util.List;

public class MenuTest {

	public static void main(String[] args) {
		MenuDAO dao = new MenuDAO();
		
//		//?–°å¢?
//		MenuVO menu1 = new MenuVO();
//		menu1.setMenu_No("M001");
//		menu1.setMenu_Id("æ¥µè‡´è±šéª¨??‰å?éºµ");
//		menu1.setMenu_Type("ç¶“å…¸é¤é??");
//		menu1.setMenu_Price(299);
//		menu1.setMenu_Intro("48å°æ?‚æº«?«?…¢?†¬ç²¾ç·»è±šéª¨æ¹¯é ­ï¼Œæ­??å?‰ç?’è?‰å?Šæº«æ³‰è??");
//		menu1.setMenu_Photo(null);
//		menu1.setMenu_Status(1);
//		dao.insert(menu1);
		
		
		
//		//ä¿®æ”¹
//		MenuVO menu2 = new MenuVO();
//		menu2.setMenu_No("M003");
//		menu2.setMenu_Id("?œ°???");
//		menu2.setMenu_Type("ç¶“å…¸é¤é??");
//		menu2.setMenu_Price(269);
//		menu2.setMenu_Intro("OOOOOOOOO");
//		menu2.setMenu_Photo(null);
//		menu2.setMenu_Status(1);
//		dao.update(menu2);
//		
//		//?ˆª?™¤
//		dao.delete("M003");
//		
		//?Ÿ¥è©?
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





