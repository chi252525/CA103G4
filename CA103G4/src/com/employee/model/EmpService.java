package com.employee.model;

import java.sql.Date;
import java.util.List;

import com.empauthority.model.EmpauthorityVO;

public class EmpService {
	
	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
		
	}

	public EmpVO addEmp(String branch_No, String emp_Acnum,String emp_Psw ,String emp_Name, String emp_Gender, String emp_Pos, String emp_Tel, byte[] emp_Photo) {
		EmpVO empVO = new EmpVO();
		
		empVO.setBranch_No(branch_No);
		empVO.setEmp_Acnum(emp_Acnum);
		empVO.setEmp_Psw(emp_Psw);
		empVO.setEmp_Name(emp_Name);
		empVO.setEmp_Gender(emp_Gender);
		empVO.setEmp_Pos(emp_Pos);
		empVO.setEmp_Tel(emp_Tel);
		empVO.setEmp_Photo(emp_Photo);
		dao.insert(empVO);
		
		return empVO;
		
	}
	
	public EmpVO updateEmp(String emp_Status,String emp_No ,String branch_No, String emp_Acnum,String emp_Psw ,String emp_Name, String emp_Gender, String emp_Pos, String emp_Tel, byte[] emp_Photo) {
		EmpVO empVO = new EmpVO();
		
		empVO.setBranch_No(branch_No);
		empVO.setEmp_Acnum(emp_Acnum);
		empVO.setEmp_Psw(emp_Psw);
		empVO.setEmp_Name(emp_Name);
		empVO.setEmp_Gender(emp_Gender);
		empVO.setEmp_Pos(emp_Pos);
		empVO.setEmp_Tel(emp_Tel);
		empVO.setEmp_Photo(emp_Photo);
		empVO.setEmp_Status(emp_Status);
		empVO.setEmp_No(emp_No);
		dao.update(empVO);		
		return empVO;
		
	}
	
	
	public EmpVO findOnebyEmpNo(String emp_No) {
		return dao.findByPrimaryKey(emp_No);	
				
	}
	
	public EmpVO findOneByEmpAcnum(String emp_Acnum) {
		return dao.findByEmpAcnum(emp_Acnum);
	}
	
	public List<EmpVO> getAll(){
		return dao.getAll();
	}
	
	public EmpVO addEmpWithAutoKeys(String branch_No, String emp_Acnum,String emp_Psw ,String emp_Name, String emp_Gender, String emp_Pos, String emp_Tel, byte[] emp_Photo, List<EmpauthorityVO> empauthorlist  ) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setBranch_No(branch_No);
		empVO.setEmp_Acnum(emp_Acnum);
		empVO.setEmp_Psw(emp_Psw);
		empVO.setEmp_Name(emp_Name);
		empVO.setEmp_Gender(emp_Gender);
		empVO.setEmp_Pos(emp_Pos);
		empVO.setEmp_Tel(emp_Tel);
		empVO.setEmp_Photo(emp_Photo);
		dao.insertWithEmpauthorityVO(empVO, empauthorlist);
		
		
		return empVO;
	}
	
	public List<EmpVO> getEmpAllByBranchNo(String branch_No) {
		return dao.EmpVOfindByBranchNo(branch_No);
	}
	
	
	
}
