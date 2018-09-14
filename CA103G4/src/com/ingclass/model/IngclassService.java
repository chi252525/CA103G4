package com.ingclass.model;

import java.util.List;


public class IngclassService {

	private IngclassDAO_interface dao;

	public IngclassService() {
		dao = new IngclassDAO();
	}

	public IngclassVO addIngclass(String ingdtc_id, String ingdtc_name) {

		IngclassVO ingclassVO = new IngclassVO();

		ingclassVO.setIngdtc_Id(ingdtc_id);
		ingclassVO.setIngdtc_Name(ingdtc_name);

		dao.insert(ingclassVO);

		return ingclassVO;
	}

	public IngclassVO updateIngclass(String ingdtc_id, String ingdtc_name) {

		IngclassVO ingclassVO = new IngclassVO();

		ingclassVO.setIngdtc_Id(ingdtc_id);
		ingclassVO.setIngdtc_Name(ingdtc_name);

		dao.update(ingclassVO);

		return ingclassVO;
	}

	public void deleteIngclass(String ingdtc_id) {
		dao.delete(ingdtc_id);
	}

	public IngclassVO getOneIngclass(String ingdtc_id) {
		return dao.findByPrimaryKey(ingdtc_id);
	}

	public List<IngclassVO> getAll() {
		return dao.getAll();
	}
}
	
	

