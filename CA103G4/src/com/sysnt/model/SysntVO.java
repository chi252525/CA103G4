package com.sysnt.model;

import java.io.Serializable;

public class SysntVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nt_No;
	private String nt_Tittle;
	private String nt_Cont;
	public SysntVO() {
		super();
	}
	public SysntVO(String nt_No, String nt_Tittle, String nt_Cont) {
		super();
		this.nt_No = nt_No;
		this.nt_Tittle = nt_Tittle;
		this.nt_Cont = nt_Cont;
	}
	public String getNt_No() {
		return nt_No;
	}
	public void setNt_No(String nt_No) {
		this.nt_No = nt_No;
	}
	public String getNt_Tittle() {
		return nt_Tittle;
	}
	public void setNt_Tittle(String nt_Tittle) {
		this.nt_Tittle = nt_Tittle;
	}
	public String getNt_Cont() {
		return nt_Cont;
	}
	public void setNt_Cont(String nt_Cont) {
		this.nt_Cont = nt_Cont;
	}
}
