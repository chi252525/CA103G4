package com.post.model;

import java.sql.Timestamp;

public class PostVO implements java.io.Serializable{







	private String post_No;
	private String mem_No;
	private String custom_No;
	private String post_Cont;
	private Integer post_Eva;
	private byte[] post_Photo;
	private Timestamp post_Time;
	private Integer post_Views;
	
	
	public String getPost_No() {
		return post_No;
	}
	public void setPost_No(String post_No) {
		this.post_No = post_No;
	}
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public String getCustom_No() {
		return custom_No;
	}
	public void setCustom_No(String custom_No) {
		this.custom_No = custom_No;
	}
	
	public String getPost_Cont() {
		return post_Cont;
	}
	public void setPost_Cont(String post_Cont) {
		this.post_Cont = post_Cont;
	}
	public Integer getPost_Eva() {
		return post_Eva;
	}
	public void setPost_Eva(Integer post_Eva) {
		this.post_Eva = post_Eva;
	}
	public byte[] getPost_Photo() {
		return post_Photo;
	}
	public void setPost_Photo(byte[] post_Photo) {
		this.post_Photo = post_Photo;
	}
	public Timestamp getPost_Time() {
		return post_Time;
	}
	public void setPost_Time(Timestamp post_Time) {
		this.post_Time = post_Time;
	}
	public Integer getPost_Views() {
		return post_Views;
	}
	public void setPost_Views(Integer post_Views) {
		this.post_Views = post_Views;
	}
	
	

	private Integer post_Count;

	public Integer getPost_Count() {
		return post_Count;
	}
	public void setPost_Count(Integer post_Count) {
		this.post_Count = post_Count;
	}

}
