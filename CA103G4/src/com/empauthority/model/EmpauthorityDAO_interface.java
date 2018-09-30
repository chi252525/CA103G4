package com.empauthority.model;

import java.sql.Connection;
import java.util.List;

import com.employee.model.EmpVO;


public interface EmpauthorityDAO_interface {
	
	public void insert(EmpauthorityVO empauthorVO);
	public void update(EmpauthorityVO empauthorVO);
	public List<EmpauthorityVO> oneEmpFealist(String empVO);
	public void insert2(EmpauthorityVO empauthorVO, Connection con);

	
	
	

}
