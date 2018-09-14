package com.feature.model;

import java.util.List;

public class FeatureService {
	private FeatureDAO_interface dao;
	public FeatureService() {
		dao = new FeatureDAO();
	}
	
	public FeatureVO addFeature(String fea_Name) {
		FeatureVO feaVO = new FeatureVO();
		feaVO.setFea_Name(fea_Name);
		dao.insert(feaVO);
		return feaVO;
	}
	
	public FeatureVO updateFeature(String fea_No, String fea_Name) {
		FeatureVO feaVO = new FeatureVO();
		feaVO.setFea_No(fea_No);
		feaVO.setFea_Name(fea_Name);
		dao.update(feaVO);
		return feaVO;
	}
	
	public void deleteFeature(String fea_No) {
		dao.delete(fea_No);
	}
	
	public FeatureVO getOneFeature(String fea_No) {
		return dao.findByPrimaryKey(fea_No);
	}
	
	public List<FeatureVO> getAll(){
		return dao.getAll();
	}
}
