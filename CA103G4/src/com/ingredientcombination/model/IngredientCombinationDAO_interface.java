package com.ingredientcombination.model;

import java.sql.Connection;
import java.util.List;

public interface IngredientCombinationDAO_interface {

			public void insert(IngredientCombinationVO ingredientCombinationVO);
			public void update(IngredientCombinationVO ingredientCombinationVO);
			public void delete(String ingredientCombination_No);
			public IngredientCombinationVO findByPrimaryKey(String menu_No);
			public List<IngredientCombinationVO> getAll();
			public void insert2(IngredientCombinationVO ingredientCombinationVO, Connection con);
	
	
	
	
}
