package com.feature.model;

import java.util.List;

public interface FeatureDAO_interface {
	public void insert(FeatureVO feaVO);
	public void update(FeatureVO feaVO);
	public void delete(String fea_No);
	public FeatureVO findByPrimaryKey(String fea_No);
	public List<FeatureVO> getAll(); 
}
