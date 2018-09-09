package com.generalmanager.model;

import java.io.Serializable;


public class GeneralmanagerVO implements Serializable {
	
	private String mger_No;
	private String mger_Acnum;
	private String mger_Psw;
	
	public GeneralmanagerVO() {
		super();
	}
	
	public GeneralmanagerVO(String mger_No,String mger_Acnum, String mger_Psw , String emp_No) {
		this.mger_No = mger_No;
		this.mger_Acnum = mger_Acnum;
		this.mger_Psw = mger_Psw;
		
	}
	public String getMger_No() {
		return mger_No;
	}
	public void setMger_No(String mger_No) {
		this.mger_No = mger_No;
	}
	public String getMger_Acnum() {
		return mger_Acnum;
	}
	public void setMger_Acnum(String mger_Acnum) {
		this.mger_Acnum = mger_Acnum;
	}
	public String getMger_Psw() {
		return mger_Psw;
	}
	public void setMger_Psw(String mger_Psw) {
		this.mger_Psw = mger_Psw;
	}


	
}
