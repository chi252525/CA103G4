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

	
	
	
//	public String getEmp_No() {
//		return emp_No;
//	}
//	/**
//	 * @param emp_no the emp_no to set
//	 */
//	public void setEmp_No(String emp_No) {
//		this.emp_No = emp_No;
//	}
//	/**
//	 * @return the branch_no
//	 */
//	public String getBranch_no() {
//		return branch_no;
//	}
//	/**
//	 * @param branch_no the branch_no to set
//	 */
//	public void setBranch_no(String branch_no) {
//		this.branch_no = branch_no;
//	}
//	/**
//	 * @return the emp_acnum
//	 */
//	public String getEmp_acnum() {
//		return emp_acnum;
//	}
//	/**
//	 * @param emp_acnum the emp_acnum to set
//	 */
//	public void setEmp_acnum(String emp_acnum) {
//		this.emp_acnum = emp_acnum;
//	}
//	/**
//	 * @return the emp_psw
//	 */
//	public String getEmp_psw() {
//		return emp_psw;
//	}
//	/**
//	 * @param emp_psw the emp_psw to set
//	 */
//	public void setEmp_psw(String emp_psw) {
//		this.emp_psw = emp_psw;
//	}
//	/**
//	 * @return the emp_name
//	 */
//	public String getEmp_name() {
//		return emp_name;
//	}
//	/**
//	 * @param emp_name the emp_name to set
//	 */
//	public void setEmp_name(String emp_name) {
//		this.emp_name = emp_name;
//	}
//	/**
//	 * @return the emp_gender
//	 */
//	public String getEmp_gender() {
//		return emp_gender;
//	}
//	/**
//	 * @param emp_gender the emp_gender to set
//	 */
//	public void setEmp_gender(String emp_gender) {
//		this.emp_gender = emp_gender;
//	}
//	/**
//	 * @return the emp_pos
//	 */
//	public String getEmp_pos() {
//		return emp_pos;
//	}
//	/**
//	 * @param emp_pos the emp_pos to set
//	 */
//	public void setEmp_pos(String emp_pos) {
//		this.emp_pos = emp_pos;
//	}
//	/**
//	 * @return the emp_tel
//	 */
//	public String getEmp_tel() {
//		return emp_tel;
//	}
//	/**
//	 * @param emp_tel the emp_tel to set
//	 */
//	public void setEmp_tel(String emp_tel) {
//		this.emp_tel = emp_tel;
//	}
//	/**
//	 * @return the emp_status
//	 */
//	public String getEmp_status() {
//		return emp_status;
//	}
//	/**
//	 * @param emp_status the emp_status to set
//	 */
//	public void setEmp_status(String emp_status) {
//		this.emp_status = emp_status;
//	}
//	/**
//	 * @return the emp_credate
//	 */
//	public Date getEmp_credate() {
//		return emp_credate;
//	}
//	/**
//	 * @param emp_credate the emp_credate to set
//	 */
//	public void setEmp_credate(Date emp_credate) {
//		this.emp_credate = emp_credate;
//	}
//	/**
//	 * @return the emp_photo
//	 */
//	public byte[] getEmp_photo() {
//		return emp_photo;
//	}
//	/**
//	 * @param emp_photo the emp_photo to set
//	 */
//	public void setEmp_photo(byte[] emp_photo) {
//		this.emp_photo = emp_photo;
//	}
//	
//	
//	public EmpVO(String eMP_NO, String bRANCH_NO, String eMP_ACNUM, String eMP_PSW, String eMP_NAME, Integer eMP_GENDER,
//			String eMP_POS, Integer eMP_TEL, Integer eMP_STATUS, Date eMP_CREDATE, Byte eMP_PHOTO) {
//		super();
//		EMP_NO = eMP_NO;
//		BRANCH_NO = bRANCH_NO;
//		EMP_ACNUM = eMP_ACNUM;
//		EMP_PSW = eMP_PSW;
//		EMP_NAME = eMP_NAME;
//		EMP_GENDER = eMP_GENDER;
//		EMP_POS = eMP_POS;
//		EMP_TEL = eMP_TEL;
//		EMP_STATUS = eMP_STATUS;
//		EMP_CREDATE = eMP_CREDATE;
//		EMP_PHOTO = eMP_PHOTO;
//	}

	

	
	
}
