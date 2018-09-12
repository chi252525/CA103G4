package com.empauthority.model;

import java.util.List;

public interface EmpauthorityDAO_interface {
	
	public void insert(EmpauthorityVO empauthorVO);
	public void update(EmpauthorityVO empauthorVO);
	public List<EmpauthorityVO> findByEmp(EmployeeVO empVO);

	
	
	

}
