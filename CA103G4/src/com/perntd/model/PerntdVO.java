package com.perntd.model;

import java.io.Serializable;

public class PerntdVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String perntd_No;
	private String mem_No;
	private String nt_No;
	private String perntd_Cont;
	private String perntd_Date;
	public PerntdVO() {
		super();
	}
	public PerntdVO(String perntd_No, String mem_No, String nt_No, String perntd_Cont, String perntd_Date) {
		super();
		this.perntd_No = perntd_No;
		this.mem_No = mem_No;
		this.nt_No = nt_No;
		this.perntd_Cont = perntd_Cont;
		this.perntd_Date = perntd_Date;
	}
	public String getPerntd_No() {
		return perntd_No;
	}
	public void setPerntd_No(String perntd_No) {
		this.perntd_No = perntd_No;
	}
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public String getNt_No() {
		return nt_No;
	}
	public void setNt_No(String nt_No) {
		this.nt_No = nt_No;
	}
	public String getPerntd_Cont() {
		return perntd_Cont;
	}
	public void setPerntd_Cont(String perntd_Cont) {
		this.perntd_Cont = perntd_Cont;
	}
	public String getPerntd_Date() {
		return perntd_Date;
	}
	public void setPerntd_Date(String perntd_Date) {
		this.perntd_Date = perntd_Date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
