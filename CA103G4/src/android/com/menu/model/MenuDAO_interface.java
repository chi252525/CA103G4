package android.com.menu.model;

import java.util.List;

public interface MenuDAO_interface {

		public void insert(MenuVO menuVO);
		public void update(MenuVO menuVO);
		public void delete(String menu_No);
		public MenuVO findByPrimaryKey(String menu_No);
		public List<MenuVO> getAll();
		
		public byte[] getImage(String menu_No);
	
}
