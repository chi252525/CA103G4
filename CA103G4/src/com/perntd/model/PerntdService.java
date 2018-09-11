package com.perntd.model;

import java.util.List;

public class PerntdService {
	
	private PerntdDAO_interface dao;
	
	public PerntdService() {
		dao = new PerntdDAO();
	}
	
	public PerntdVO addPerntd(String mem_No, String nt_No, String perntd_Cont, String perntd_Date) {
		
		PerntdVO perntdVO = new PerntdVO();
		
		perntdVO.setMem_No(mem_No);
		perntdVO.setNt_No(nt_No);
		perntdVO.setPerntd_Cont(perntd_Cont);
		perntdVO.setPerntd_Date(perntd_Date);
		dao.insert(perntdVO);
		
		return perntdVO;
		
	}
	
	public PerntdVO updatePerntd(String perntd_No, String mem_No, String nt_No, String perntd_Cont, String perntd_Date) {
		
		PerntdVO perntdVO = new PerntdVO();
		
		perntdVO.setPerntd_No(perntd_No);;
		perntdVO.setMem_No(mem_No);
		perntdVO.setNt_No(nt_No);
		perntdVO.setPerntd_Cont(perntd_Cont);
		perntdVO.setPerntd_Date(perntd_Date);
		dao.update(perntdVO);;
		
		return perntdVO;
		
	}
	
	public void deletePerntd(String perntd_No) {
		dao.delete(perntd_No);
	}
	
	public PerntdVO getOnePerntd(String perntd_No) {
		return dao.findByPrimaryKey(perntd_No);
	}
	
	public List<PerntdVO> getAll() {
		return dao.getAll();
	}
}
