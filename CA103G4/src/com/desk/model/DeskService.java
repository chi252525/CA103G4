package com.desk.model;

import java.util.List;

import com.reservation.model.ResVO;


public class DeskService {
private DeskDAO_interface dao;
	
	public DeskService() {
		dao = new DeskDAO();
	}
	
	public DeskVO addDesk(String branch_no, String Dek_id, String Dek_set, String Dek_status) {
		
		DeskVO deskVO = new DeskVO();
		deskVO.setBranch_no("0002");
		deskVO.setDek_id("G2");
		deskVO.setDek_set(2);
		deskVO.setDek_status(2);
		dao.insert(deskVO);
		
		return deskVO;
	}
	
	public DeskVO updateDes(String dek_no, String branch_no, String Dek_id, String Dek_set, String Dek_status) {
		
		DeskVO deskVO = new DeskVO();
		deskVO.setDek_no("D000000001");
		deskVO.setBranch_no("0001");
		deskVO.setDek_id("G2");
		deskVO.setDek_set(2);
		deskVO.setDek_status(2);
		dao.update(deskVO);	
		
		return deskVO;
	}
	
	public DeskVO getOneDes(String dek_no) {
		return dao.findByPrimaryKey(dek_no);
	}

	public List<DeskVO> getAll() {
		return dao.getAll();
	}
	
	
	public void desk_res(DeskVO deskVO, ResVO resVO) {		
		dao.insertAutoGK(deskVO, resVO);
	}
	
	public List<DeskVO> getByBrano(String branch_no) {
		return dao.getByBr(branch_no);
	}
	
}
