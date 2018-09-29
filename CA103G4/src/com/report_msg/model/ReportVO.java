package com.report_msg.model;
 import java.sql.Timestamp;
 public class ReportVO implements java.io.Serializable{
 	/**
	 * 
	 */
	private String rpt_No;
	private String mem_No;
	private String post_No;
	private String rpt_Rsm;
	private String rpt_Status;
	private Timestamp rpt_Time;
	
	
	public ReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
 	public String getRpt_No() {
		return rpt_No;
	}
	public void setRpt_No(String rpt_No) {
		this.rpt_No = rpt_No;
	}
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public String getPost_No() {
		return post_No;
	}
	public void setPost_No(String post_No) {
		this.post_No = post_No;
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