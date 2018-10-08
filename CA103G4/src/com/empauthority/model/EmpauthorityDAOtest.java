package com.empauthority.model;

import java.util.ArrayList;
import java.util.List;

import com.employee.model.EmpVO;

public class EmpauthorityDAOtest {

	public static void main(String args[]) {

//新增
//		EmpauthorityVO empauthorVO = new EmpauthorityVO();
//		empauthorVO.setEmp_No("E000000002");
//		empauthorVO.setFea_No("F003");
//		EmpauthorityDAO dao = new EmpauthorityDAO();
//		dao.insert(empauthorVO);
		
//查詢BY EMP_NO	

//		EmpauthorityDAO dao = new EmpauthorityDAO();
//		List<EmpauthorityVO> empauthlist = new ArrayList<>();
//		empauthlist=dao.oneEmpFealist("E000000001");
//		for(EmpauthorityVO empauthorVO : empauthlist) {
//			System.out.println(empauthorVO.getFea_No());
//			
//		}
		
//		EmpauthorityJDBCDAO dao = new EmpauthorityJDBCDAO();
//		dao.delete("E000000002");
		
		
		EmpauthorityJDBCDAO dao = new EmpauthorityJDBCDAO();
		List<EmpauthorityVO> empauthlist = new ArrayList();
		
		EmpauthorityVO empauthVO1 = new EmpauthorityVO();
		empauthVO1.setEmp_No("E000000004");
		empauthVO1.setFea_No("F002");
		empauthlist.add(empauthVO1);
		
		EmpauthorityVO empauthVO2 = new EmpauthorityVO();
		empauthVO2.setEmp_No("E000000004");
		empauthVO2.setFea_No("F004");
		empauthlist.add(empauthVO2);
		
		EmpauthorityVO empauthVO3 = new EmpauthorityVO();
		empauthVO3.setEmp_No("E000000004");
		empauthVO3.setFea_No("F005");
		empauthlist.add(empauthVO3);
		
		dao.delete2("E000000004", empauthlist);
		
	}
}
