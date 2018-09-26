package com.custommeals.model;
import java.util.List;

import com.ingredientcombination.model.IngredientCombinationVO;


public interface CustommealsDAO_interface {
	public void insert(CustommealsVO custommealsVO);
	public void update(CustommealsVO custommealsVO);
	
	public void delete(String custommeals_No);
	public CustommealsVO findByPrimaryKey(String custommeals_No);
	public List<CustommealsVO> getAll();
	public List<CustommealsVO> getMealByMemBuyed(String mem_No);
<<<<<<< HEAD
	public List<CustommealsVO> getMealByMem(String mem_No);
	public void insertWithIngredientCombination(CustommealsVO custommealsVO, List<IngredientCombinationVO> list);
=======
	public void updateNameOnly(String custom_Name,String custom_No);
>>>>>>> 3a7874aa3b7145489ed16b794a0c414df9ae4bc2
}
