package com.ingredients.model;

import java.util.List;

public interface IngredientsDAO_interface {

			public void insert(IngredientsVO ingredientsVO);
			public void update(IngredientsVO ingredientsVO);
			public void delete(String ingredients_No);
			public IngredientsVO findByPrimaryKey(String ingredients_No);
			public List<IngredientsVO> getAll();
		
}
