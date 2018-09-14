package com.ingredientcombination.model;

import java.util.List;


public class IngredientCombinationService {
	private IngredientCombinationDAO_interface dao;

	public IngredientCombinationService() {
		dao = new IngredientCombinationDAO();
	}

	public IngredientCombinationVO addIngredientCombination(String custom_no, String ingdt_id) {

		IngredientCombinationVO ingredientCombinationVO = new IngredientCombinationVO();

		ingredientCombinationVO.setCustom_No(custom_no);
		ingredientCombinationVO.setIngdt_Id(ingdt_id);

		dao.insert(ingredientCombinationVO);

		return ingredientCombinationVO;
	}

	public IngredientCombinationVO updateIngredientCombination(String custom_no, String ingdt_id) {

		IngredientCombinationVO ingredientCombinationVO = new IngredientCombinationVO();

		ingredientCombinationVO.setCustom_No(custom_no);
		ingredientCombinationVO.setIngdt_Id(ingdt_id);

		dao.update(ingredientCombinationVO);

		return ingredientCombinationVO;
	}

	public void deleteIngredientCombination(String custom_no) {
		dao.delete(custom_no);
	}

	public IngredientCombinationVO getOneIngredientCombination(String custom_no) {
		return dao.findByPrimaryKey(custom_no);
	}

	public List<IngredientCombinationVO> getAll() {
		return dao.getAll();
	}
}
