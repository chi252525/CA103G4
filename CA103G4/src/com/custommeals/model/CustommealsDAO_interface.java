package com.custommeals.model;
import java.util.List;


public interface CustommealsDAO_interface {
	public void insert(CustommealsVO custommealsVO);
	public void update(CustommealsVO custommealsVO);
	public void delete(String custommeals_No);
	public CustommealsVO findByPrimaryKey(String custommeals_No);
	public List<CustommealsVO> getAll();

}
