package com.menu.model;

import java.util.List;

public interface MenuDAO_interface {

		public void insert(MenuVO menuVO);
		public void update(MenuVO menuVO);
		public void delete(String menu_No);
		public MenuVO findByPrimaryKey(String menu_No);
		public List<MenuVO> getAll();
		List<MenuVO> getMealByMemBuyedClassic(String mem_No);
		public void update2(MenuVO menuVO);
		List<MenuVO> getAll_front();
	
}
