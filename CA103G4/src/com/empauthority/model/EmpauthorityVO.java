package com.empauthority.model;

public class EmpauthorityVO {
	
	private String emp_No;
	private String fea_No;
	
	
	public EmpauthorityVO(String emp_No, String fea_No) {
		super();
		this.emp_No = emp_No;
		this.fea_No = fea_No;
	}
	public EmpauthorityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmp_No() {
		return emp_No;
	}
	public void setEmp_No(String emp_No) {
		this.emp_No = emp_No;
	}
	public String getFea_No() {
		return fea_No;
	}
	public void setFea_No(String fea_No) {
		this.fea_No = fea_No;
	}
	
	

}
