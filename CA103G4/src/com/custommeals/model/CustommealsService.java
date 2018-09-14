package com.custommeals.model;

import java.util.List;

import com.ingclass.model.IngclassDAO;
import com.ingclass.model.IngclassDAO_interface;
import com.ingclass.model.IngclassVO;

public class CustommealsService {
	private CustommealsDAO_interface dao;

	public CustommealsService() {
		dao = new CustommealsDAO();
	}

	public CustommealsVO addCustommeals(String ingdtc_id, String ingdtc_name) {

		CustommealsVO custommealsVO = new CustommealsVO();

		custommealsVO.setIngdtc_Id(ingdtc_id);
		custommealsVO.setIngdtc_Name(ingdtc_name);

		dao.insert(custommealsVO);

		return custommealsVO;
	}

	public CustommealsVO updateCustommeals(String ingdtc_id, String ingdtc_name) {

		CustommealsVO custommealsVO = new CustommealsVO();

		custommealsVO.setIngdtc_Id(ingdtc_id);
		custommealsVO.setIngdtc_Name(ingdtc_name);

		dao.update(custommealsVO);

		return custommealsVO;
	}

	public void deleteCustommeals(String ingdtc_id) {
		dao.delete(ingdtc_id);
	}

	public CustommealsVO getOneCustommeals(String ingdtc_id) {
		return dao.findByPrimaryKey(ingdtc_id);
	}

	public List<CustommealsVO> getAll() {
		return dao.getAll();
	}
}
