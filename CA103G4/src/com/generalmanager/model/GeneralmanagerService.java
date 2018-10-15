package com.generalmanager.model;

public class GeneralmanagerService {

	private GeneralmanagerDAO_interface dao;
	
	public GeneralmanagerService(){
		dao = new GeneralmanagerDAO();
	}
	
	public GeneralmanagerVO getOneByAcnum(String mger_Acnum) {
		return dao.findByAcnum(mger_Acnum);
	
	}
}
