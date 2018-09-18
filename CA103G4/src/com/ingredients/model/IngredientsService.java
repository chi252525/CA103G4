package com.ingredients.model;

import java.util.List;


public class IngredientsService {

	private IngredientsDAO_interface dao;

	public IngredientsService() {
		dao = new IngredientsDAO();
	}

	public IngredientsVO addIngredients(String ingdtc_Id, String ingdt_Name, Integer ingdt_Status, String ingdt_Point, String ingdt_Unit, Integer ingdt_Price, byte[] ingdt_Photo) {

		IngredientsVO ingredientsVO = new IngredientsVO();

		ingredientsVO.setingdtc_Id(ingdtc_Id);
		ingredientsVO.setingdt_Name(ingdt_Name);
		ingredientsVO.setingdt_Status(ingdt_Status);
		ingredientsVO.setingdt_Point(ingdt_Point);
		ingredientsVO.setingdt_Unit(ingdt_Unit);
		ingredientsVO.setingdt_Price(ingdt_Price);
		ingredientsVO.setingdt_Photo(ingdt_Photo);

		dao.insert(ingredientsVO);

		return ingredientsVO;
	}

	public IngredientsVO updateIngredients(String ingdt_Id, String ingdtc_Id, String ingdt_Name, Integer ingdt_Status, String ingdt_Point, String ingdt_Unit, Integer ingdt_Price, byte[] ingdt_Photo) {

		IngredientsVO ingredientsVO = new IngredientsVO();

		ingredientsVO.setingdt_Id(ingdt_Id);
		ingredientsVO.setingdtc_Id(ingdtc_Id);
		ingredientsVO.setingdt_Name(ingdt_Name);
		ingredientsVO.setingdt_Status(ingdt_Status);
		ingredientsVO.setingdt_Point(ingdt_Point);
		ingredientsVO.setingdt_Unit(ingdt_Unit);
		ingredientsVO.setingdt_Price(ingdt_Price);
		ingredientsVO.setingdt_Photo(ingdt_Photo);

		dao.update(ingredientsVO);

		return ingredientsVO;
	}

	public void deleteIngredients(String ingdt_Id) {
		dao.delete(ingdt_Id);
	}

	public IngredientsVO getOneIngredients(String ingdt_Id) {
		return dao.findByPrimaryKey(ingdt_Id);
	}

	public List<IngredientsVO> getAll() {
		return dao.getAll();
	}
	
}
