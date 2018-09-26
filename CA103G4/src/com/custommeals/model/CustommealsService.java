package com.custommeals.model;

import java.util.List;


public class CustommealsService {
	private CustommealsDAO_interface dao;

	public CustommealsService() {
		dao = new CustommealsDAO();
	}

	public CustommealsVO addCustommeals(String mem_no, String custom_name, Integer custom_price, byte[] custom_photo) {

		CustommealsVO custommealsVO = new CustommealsVO();

		custommealsVO.setmem_No(mem_no);
		custommealsVO.setcustom_Name(custom_name);
		custommealsVO.setcustom_Price(custom_price);
		custommealsVO.setcustom_Photo(custom_photo);

		dao.insert(custommealsVO);

		return custommealsVO;
	}

	public CustommealsVO updateCustommeals(String custom_no, String mem_no, String custom_name, Integer custom_price, byte[] custom_photo) {

		CustommealsVO custommealsVO = new CustommealsVO();

		custommealsVO.setcustom_No(custom_no);
		custommealsVO.setmem_No(mem_no);
		custommealsVO.setcustom_Name(custom_name);
		custommealsVO.setcustom_Price(custom_price);
		custommealsVO.setcustom_Photo(custom_photo);

		dao.update(custommealsVO);

		return custommealsVO;
	}
	
	


	public void deleteCustommeals(String custom_no) {
		dao.delete(custom_no);
	}

	public CustommealsVO getOneCustommeals(String custom_no) {
		return dao.findByPrimaryKey(custom_no);
	}

	public List<CustommealsVO> getAll() {
		return dao.getAll();
	}
	
	//add by Ning
	public List<CustommealsVO> getMealByMemBuyed(String mem_No) {
		return dao.getMealByMemBuyed(mem_No);
	}
	//add by Ning
	public CustommealsVO updateNameOnly( String custom_Name,String custom_No) {
		CustommealsVO custommealsVO = new CustommealsVO();
		custommealsVO.setcustom_No(custom_No);
		custommealsVO.setcustom_Name(custom_Name);
		dao.updateNameOnly(custom_Name,custom_No);
		return custommealsVO;
	}
}
