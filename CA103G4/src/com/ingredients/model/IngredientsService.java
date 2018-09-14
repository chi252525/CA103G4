package com.ingredients.model;

import java.util.List;


public class IngredientsService {

	private IngredientsDAO_interface dao;

	public IngredientsService() {
		dao = new IngredientsDAO();
	}

	public IngredientsVO addIngredients(String ingdt_id, String ingdtc_id, String ingdt_name, Integer ingdt_status, String ingdt_point, String ingdt_unit, Integer ingdt_price) {

		IngredientsVO ingredientsVO = new IngredientsVO();

		ingredientsVO.setIngdt_Id(ingdt_id);
		ingredientsVO.setIngdtc_Id(ingdtc_id);
		ingredientsVO.setIngdt_Name(ingdt_name);
		ingredientsVO.setIngdt_Status(ingdt_status);
		ingredientsVO.setIngdt_Point(ingdt_point);
		ingredientsVO.setIngdt_Unit(ingdt_unit);
		ingredientsVO.setIngdt_Price(ingdt_price);

		dao.insert(ingredientsVO);

		return ingredientsVO;
	}

	public IngredientsVO updateIngredients(String ingdt_id, String ingdtc_id, String ingdt_name, Integer ingdt_status, String ingdt_point, String ingdt_unit, Integer ingdt_price) {

		IngredientsVO ingredientsVO = new IngredientsVO();

		ingredientsVO.setIngdt_Id(ingdt_id);
		ingredientsVO.setIngdtc_Id(ingdtc_id);
		ingredientsVO.setIngdt_Name(ingdt_name);
		ingredientsVO.setIngdt_Status(ingdt_status);
		ingredientsVO.setIngdt_Point(ingdt_point);
		ingredientsVO.setIngdt_Unit(ingdt_unit);
		ingredientsVO.setIngdt_Price(ingdt_price);

		dao.update(ingredientsVO);

		return ingredientsVO;
	}

	public void deleteIngredients(String ingdt_id) {
		dao.delete(ingdt_id);
	}

	public IngredientsVO getOneIngredients(String ingdt_id) {
		return dao.findByPrimaryKey(ingdt_id);
	}

	public List<IngredientsVO> getAll() {
		return dao.getAll();
	}
	
}
