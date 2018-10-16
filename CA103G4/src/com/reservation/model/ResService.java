package com.reservation.model;

import java.util.List;


public class ResService {
	
	private ResDAO_interface dao;

	public ResService() {
		dao = new ResDAO();
	}

	public ResVO addRes(String mem_no, String dek_no, java.sql.Timestamp res_timebg,
			java.sql.Timestamp res_timefn, Integer res_people, Integer res_status) {

		ResVO resVO = new ResVO();
		resVO.setMem_no(mem_no);
		resVO.setDek_no(dek_no);
		resVO.setRes_timebg(res_timebg);
		resVO.setRes_timefn(res_timefn);
		resVO.setRes_people(res_people);
		resVO.setRes_status(res_status);
		dao.insert(resVO);

		return resVO;
	}

	public ResVO updateRes(String mem_no, String dek_no, java.sql.Timestamp res_timebg,
			java.sql.Timestamp res_timefn, Integer res_people, Integer res_status, String res_no) {

		ResVO resVO = new ResVO();

		resVO.setMem_no(mem_no);
		resVO.setDek_no(dek_no);
		resVO.setRes_timebg(res_timebg);
		resVO.setRes_timefn(res_timefn);
		resVO.setRes_people(res_people);
		resVO.setRes_status(res_status);
		resVO.setRes_no(res_no);
		dao.update(resVO);	

		return resVO;
	}

	
	public ResVO getOneRes(String res_no) {
		return dao.findByPrimaryKey(res_no);
	}
	   
	public List<ResVO> getAllByBGNO(String res_timebg){
		return dao.getAllByBGNO(res_timebg);
		
	}

	public List<ResVO> getAll() {
		return dao.getAll();
	}
}
