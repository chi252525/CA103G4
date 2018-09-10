package com.custommeals.model;
import java.util.List;


public interface CustommealsDAO_interface {
	public void insert(CustommealsVO custommealsVO);
	public void update(CustommealsVO custommealsVO);
	public void delete(String custommeals_No);
	public CustommealsVO findByPrimaryKey(String custommeals_No);
	public List<CustommealsVO> getAll();
	CustommealsVO findByMem_No(String Mem_No);
	CustommealsVO findbyCustom_No(String Custom_No);
	CustommealsVO findByPrimaryKey1(String Custom_No);
}
