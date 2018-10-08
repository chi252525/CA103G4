package com.empauthority.model;

import java.sql.Connection;
import java.util.List;

import com.employee.model.EmpVO;


public interface EmpauthorityDAO_interface {
	
	public void insert(EmpauthorityVO empauthorVO);
	public void update(EmpauthorityVO empauthorVO);
	public List<EmpauthorityVO> oneEmpFealist(String emp_No);
	public void delete(String emp_No);
	public void delete2(String emp_No,List<EmpauthorityVO> empauthorlist);
	public void insert2(EmpauthorityVO empauthorVO, Connection con);


	
	
	

}
