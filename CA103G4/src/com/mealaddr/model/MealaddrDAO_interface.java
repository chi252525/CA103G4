package com.mealaddr.model;
import java.util.List;

public interface MealaddrDAO_interface {
	void insert(MealaddrVO mealaddrVO);
	void update(MealaddrVO mealaddrVO);
	void delete(String mealaddr_No);
	MealaddrVO findByPrimaryKey(String mealaddr_No);
	List<MealaddrVO> getAll();
	MealaddrVO findByMem_no(String mem_No);
}


