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

		EmpauthorityDAO dao = new EmpauthorityDAO();
		List<EmpauthorityVO> empauthlist = new ArrayList<>();
		empauthlist=dao.oneEmpFealist("E000000001");
		for(EmpauthorityVO empauthorVO : empauthlist) {
			System.out.println(empauthorVO.getFea_No());
			
		}
		
				
	}
}
