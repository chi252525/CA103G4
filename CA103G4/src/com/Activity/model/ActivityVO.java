package com.Activity.model;
import java.sql.Date;

public class ActivityVO {
	private String act_No;
	private String coucat_No;
	private Integer act_Cat;
	private String act_Name;
	private byte[] act_Carousel;
	private byte[] act_Pic;
	private String act_Content;
	private String act_Start;
	private String act_End;
	public ActivityVO() {
		super();
	}
	
	public ActivityVO(String coucat_No, Integer act_Cat, 
			String act_Name, String act_Content,
			String act_Start, String act_End) {
		super();
		this.coucat_No = coucat_No;
		this.act_Cat = act_Cat;
		this.act_Name = act_Name;
		this.act_Content = act_Content;
		this.act_Start = act_Start;
		this.act_End = act_End;
	}
	
	
	public ActivityVO(String act_No,String coucat_No, Integer act_Cat, 
			String act_Name, String act_Content,
			String act_Start, String act_End) {
		super();
		this.act_No = act_No;
		this.coucat_No = coucat_No;
		this.act_Cat = act_Cat;
		this.act_Name = act_Name;
		this.act_Content = act_Content;
		this.act_Start = act_Start;
		this.act_End = act_End;
	}

	public String getAct_No() {
		return act_No;
	}
	public void setAct_No(String act_No) {
		this.act_No = act_No;
	}
	public String getCoucat_No() {
		return coucat_No;
	}
	public void setCoucat_No(String coucat_No) {
		this.coucat_No = coucat_No;
	}
	public Integer getAct_Cat() {
		return act_Cat;
	}
	public void setAct_Cat(Integer act_Cat) {
		this.act_Cat = act_Cat;
	}
	public String getAct_Name() {
		return act_Name;
	}
	public void setAct_Name(String act_Name) {
		this.act_Name = act_Name;
	}
	public byte[] getAct_Carousel() {
		return act_Carousel;
	}
	public void setAct_Carousel(byte[] act_Carousel) {
		this.act_Carousel = act_Carousel;
	}
	public byte[] getAct_Pic() {
		return act_Pic;
	}
	public void setAct_Pic(byte[] act_Pic) {
		this.act_Pic = act_Pic;
	}
	public String getAct_Content() {
		return act_Content;
	}
	public void setAct_Content(String act_Content) {
		this.act_Content = act_Content;
	}
	public String getAct_Start() {
		return act_Start;
	}
	public void setAct_Start(String act_Start) {
		this.act_Start = act_Start;
	}
	public String getAct_End() {
		return act_End;
	}
	public void setAct_End(String act_End) {
		this.act_End = act_End;
	}

	
	
	
}