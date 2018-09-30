package com.employee.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable{
	public EmpVO() {
		
	}
	private String  emp_No;    	
	private String  branch_No;    	  
	private String  emp_Acnum; 	
	private String  emp_Psw;	
	private String  emp_Name;	
	private String  emp_Gender;  	
	private String  emp_Pos;	
	private String  emp_Tel;     	
	private String  emp_Status;  	
	private Date    emp_Credate; 	 
	private byte[]  emp_Photo;
	
	
	
	public EmpVO(String emp_No, String branch_No, String emp_Acnum, String emp_Psw, String emp_Name, String emp_Gender,
			String emp_Pos, String emp_Tel, String emp_Status, Date emp_Credate, byte[] emp_Photo) {
		super();
		this.emp_No = emp_No;
		this.branch_No = branch_No;
		this.emp_Acnum = emp_Acnum;
		this.emp_Psw = emp_Psw;
		this.emp_Name = emp_Name;
		this.emp_Gender = emp_Gender;
		this.emp_Pos = emp_Pos;
		this.emp_Tel = emp_Tel;
		this.emp_Status = emp_Status;
		this.emp_Credate = emp_Credate;
		this.emp_Photo = emp_Photo;
	}
	public String getEmp_No() {
		return emp_No;
	}
	public void setEmp_No(String emp_No) {
		this.emp_No = emp_No;
	}
	public String getBranch_No() {
		return branch_No;
	}
	public void setBranch_No(String branch_No) {
		this.branch_No = branch_No;
	}
	public String getEmp_Acnum() {
		return emp_Acnum;
	}
	public void setEmp_Acnum(String emp_Acnum) {
		this.emp_Acnum = emp_Acnum;
	}
	public String getEmp_Psw() {
		return emp_Psw;
	}
	public void setEmp_Psw(String emp_Psw) {
		this.emp_Psw = emp_Psw;
	}
	public String getEmp_Name() {
		return emp_Name;
	}
	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}
	public String getEmp_Gender() {
		return emp_Gender;
	}
	public void setEmp_Gender(String emp_Gender) {
		this.emp_Gender = emp_Gender;
	}
	public String getEmp_Pos() {
		return emp_Pos;
	}
	public void setEmp_Pos(String emp_Pos) {
		this.emp_Pos = emp_Pos;
	}
	public String getEmp_Tel() {
		return emp_Tel;
	}
	public void setEmp_Tel(String emp_Tel) {
		this.emp_Tel = emp_Tel;
	}
	public String getEmp_Status() {
		return emp_Status;
	}
	public void setEmp_Status(String emp_Status) {
		this.emp_Status = emp_Status;
	}
	public Date getEmp_Credate() {
		return emp_Credate;
	}
	public void setEmp_Credate(Date emp_Credate) {
		this.emp_Credate = emp_Credate;
	}
	public byte[] getEmp_Photo() {
		return emp_Photo;
	}
	public void setEmp_Photo(byte[] emp_Photo) {
		this.emp_Photo = emp_Photo;
	}
	
	
}
