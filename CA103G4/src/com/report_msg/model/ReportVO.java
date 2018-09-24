package com.report_msg.model;

import java.sql.Timestamp;

public class ReportVO implements java.io.Serializable{

	private String mem_No;
	private String rply_No;
	private String rpt_Rsm;
	private String rpt_Status;
	private Timestamp rpt_Time;
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public String getRply_No() {
		return rply_No;
	}
	public void setRply_No(String rply_No) {
		this.rply_No = rply_No;
	}
	public String getRpt_Rsm() {
		return rpt_Rsm;
	}
	public void setRpt_Rsm(String rpt_Rsm) {
		this.rpt_Rsm = rpt_Rsm;
	}
	public String getRpt_Status() {
		return rpt_Status;
	}
	public void setRpt_Status(String rpt_Status) {
		this.rpt_Status = rpt_Status;
	}
	public Timestamp getRpt_Time() {
		return rpt_Time;
	}
	public void setRpt_Time(Timestamp rpt_Time) {
		this.rpt_Time = rpt_Time;
	}
	
	
	
	
	
}
