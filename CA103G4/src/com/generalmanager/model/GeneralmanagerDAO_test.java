package com.generalmanager.model;

public class GeneralmanagerDAO_test {
	
	public static void main(String []args) {
		
	//測試新增資料ok
//		GeneralmanagerVO mgerVO = new GeneralmanagerVO();
//		mgerVO.setMger_No("G3");
//		mgerVO.setMger_Acnum("THIRD");
//		mgerVO.setMger_Psw("123456");
//		
//		GeneralmanagerDAO dao = new GeneralmanagerDAO();
//		dao.insert(mgerVO);			
//	}
	
	//測試修改資料ok
	GeneralmanagerVO mgerVO = new GeneralmanagerVO();
		mgerVO.setMger_No("G2");
		mgerVO.setMger_Acnum("SECOND");
		mgerVO.setMger_Psw("123456");
	GeneralmanagerDAO dao = new GeneralmanagerDAO();
		dao.update(mgerVO);
	}
}
