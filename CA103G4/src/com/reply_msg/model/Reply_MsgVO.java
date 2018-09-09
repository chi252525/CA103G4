package com.reply_msg.model;

import java.sql.Timestamp;

public class Reply_MsgVO {
	
	private String rply_No;
	private String mem_No;
	private String post_No;
	private String rply_Cont;
	private Timestamp rply_Time;
	private String rply_Status;
	
	
	public Reply_MsgVO(String rply_No, String mem_No, String post_No, String rply_Cont, Timestamp rply_Time,
			String rply_Status) {
		super();
		this.rply_No = rply_No;
		this.mem_No = mem_No;
		this.post_No = post_No;
		this.rply_Cont = rply_Cont;
		this.rply_Time = rply_Time;
		this.rply_Status = rply_Status;
	}
	
	public Reply_MsgVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRply_No() {
		return rply_No;
	}
	public void setRply_No(String rply_No) {
		this.rply_No = rply_No;
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
	public String getRply_Cont() {
		return rply_Cont;
	}
	public void setRply_Cont(String rply_Cont) {
		this.rply_Cont = rply_Cont;
	}
	public Timestamp getRply_Time() {
		return rply_Time;
	}
	public void setRply_Time(Timestamp rply_Time) {
		this.rply_Time = rply_Time;
	}
	public String getRply_Status() {
		return rply_Status;
	}
	public void setRply_Status(String rply_Status) {
		this.rply_Status = rply_Status;
	}
	
	
}
