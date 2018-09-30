package com.empauthority.model;

import java.util.List;

import com.employee.model.EmpVO;
import com.feature.model.FeatureVO;

public class EmpauthorityService {

	private EmpauthorityDAO_interface dao;
	
	public EmpauthorityService() {
		dao = new EmpauthorityDAO();
	}
	
	public EmpauthorityVO addEmpauthority(String emp_No,String fea_No ) {
		EmpauthorityVO empauthorVO = new EmpauthorityVO();
		
		empauthorVO.setEmp_No(emp_No);
		empauthorVO.setFea_No(fea_No);
		dao.insert(empauthorVO);
				
		return empauthorVO;
				
	}
	public EmpauthorityVO update(String emp_No,String fea_No) {
		EmpauthorityVO empauthorVO = new EmpauthorityVO();
		
		return empauthorVO;
	}
	
	
	public List<EmpauthorityVO> oneEmpFealist(String emp_No){
		
		return dao.oneEmpFealist(emp_No);
				
	}
	
}
