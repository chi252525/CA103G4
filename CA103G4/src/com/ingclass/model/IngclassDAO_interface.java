package com.ingclass.model;

import java.util.List;

public interface IngclassDAO_interface {
	
	public void insert(IngclassVO ingclassVO);
	public void update(IngclassVO ingclassVO);
	public void delete(String ingclass_No);
	public IngclassVO findByPrimaryKey(String ingclass_No);
	public List<IngclassVO> getAll();

}
