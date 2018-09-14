package com.sysnt.model;

import java.util.List;

public class SysntService {
	private SysntDAO_interface dao;
	public SysntService() {
		dao = new SysntDAO();
	}
	
	public SysntVO addSysnt(String nt_Tittle, String nt_Cont) {	
		SysntVO sysntVO = new SysntVO();		
		sysntVO.setNt_Tittle(nt_Tittle);
		sysntVO.setNt_Cont(nt_Cont);
		dao.insert(sysntVO);
		return sysntVO;
	}
	
	public SysntVO updateSysnt(String nt_No, String nt_Tittle, String nt_Cont) {	
		SysntVO sysntVO = new SysntVO();		
		sysntVO.setNt_No(nt_No);
		sysntVO.setNt_Tittle(nt_Tittle);
		sysntVO.setNt_Cont(nt_Cont);
		dao.update(sysntVO);
		return sysntVO;
	}
	
	public void deleteSysnt(String nt_No) {	
		dao.delete(nt_No);
	}
	
	public SysntVO getOneSysnt(String nt_No) {
		return dao.findByPrimaryKey(nt_No);
	}
	
	public List<SysntVO> getAll(){
		return dao.getAll();
	}
}
