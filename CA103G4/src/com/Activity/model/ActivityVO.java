package com.Activity.model;
import java.sql.Timestamp;

public class ActivityVO {
	private String act_No;
	private String coucat_No;
	private String act_Cat;
	private String act_Name;
	private byte[] act_Carousel;
	private byte[] act_Pic;
	private String act_Content;
	private Timestamp act_Start;
	private Timestamp act_End;
	public ActivityVO() {
		super();
	}
	
	public ActivityVO(String coucat_No, String act_Cat, 
			String act_Name, String act_Content,
			Timestamp act_Start, Timestamp act_End) {
		super();
		this.coucat_No = coucat_No;
		this.act_Cat = act_Cat;
		this.act_Name = act_Name;
		this.act_Content = act_Content;
		this.act_Start = act_Start;
		this.act_End = act_End;
	}
	
	
	public ActivityVO(String act_No,String coucat_No, String act_Cat, 
			String act_Name, String act_Content,
			Timestamp act_Start, Timestamp act_End) {
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
	public String getAct_Cat() {
		return act_Cat;
	}
	public void setAct_Cat(String act_Cat) {
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
	public Timestamp getAct_Start() {
		return act_Start;
	}
	public void setAct_Start(Timestamp act_Start) {
		this.act_Start = act_Start;
	}
	public Timestamp getAct_End() {
		return act_End;
	}
	public void setAct_End(Timestamp act_End) {
		this.act_End = act_End;
	}

	
	
	
}
