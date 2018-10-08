package com.menu.model;

import java.util.List;


public class MenuService {
	private MenuDAO_interface dao;

	public MenuService() {
		dao = new MenuDAO();
	}

	public MenuVO addMenu(String menu_id, String menu_type, Integer menu_price, String menu_intro ,byte[] menu_photo, Integer menu_status) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_Id(menu_id);
		menuVO.setMenu_Type(menu_type);
		menuVO.setMenu_Price(menu_price);
		menuVO.setMenu_Intro(menu_intro);
		menuVO.setMenu_Photo(menu_photo);
		menuVO.setMenu_Status(menu_status);

		dao.insert(menuVO);

		return menuVO;
	}

	public MenuVO updateMenu(String menu_no, String menu_id, String menu_type, Integer menu_price, String menu_intro ,byte[] menu_photo, Integer menu_status) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_No(menu_no);
		menuVO.setMenu_Id(menu_id);
		menuVO.setMenu_Type(menu_type);
		menuVO.setMenu_Price(menu_price);
		menuVO.setMenu_Intro(menu_intro);
		menuVO.setMenu_Photo(menu_photo);
		menuVO.setMenu_Status(menu_status);

		dao.update(menuVO);

		return menuVO;
	}

	public void deleteMenu(String menu_no) {
		dao.delete(menu_no);
	}

	public MenuVO getOneMenu(String menu_no) {
		return dao.findByPrimaryKey(menu_no);
	}

	public List<MenuVO> getAll() {
		return dao.getAll();
	}

	public MenuVO updateMenu2(String menu_no, Integer menu_status) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_No(menu_no);
		menuVO.setMenu_Status(menu_status);

		dao.update2(menuVO);

		return menuVO;
		

	}


}
