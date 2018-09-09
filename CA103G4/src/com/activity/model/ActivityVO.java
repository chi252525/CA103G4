package com.activity.model;
import java.sql.*;

public class ActivityVO implements java.io.Serializable{
	/**
	 * 
	 */

	private String act_No;
	private String coucat_No;
	private String act_Cat;
	private String act_Name;
	private byte[] act_Carousel;
	private byte[] act_Pic;
	private String act_Content;
	private String act_Start;
	private String act_End;
	private String act_Usecou;
	
	public ActivityVO(String coucat_No, String act_Cat, String act_Name, byte[] act_Carousel, byte[] act_Pic,
			String act_Content, String act_Start, String act_End, String act_Usecou) {
		super();
		this.coucat_No = coucat_No;
		this.act_Cat = act_Cat;
		this.act_Name = act_Name;
		this.act_Carousel = act_Carousel;
		this.act_Pic = act_Pic;
		this.act_Content = act_Content;
		this.act_Start = act_Start;
		this.act_End = act_End;
		this.act_Usecou = act_Usecou;
	}

	public ActivityVO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getAct_Usecou() {
		return act_Usecou;
	}
	public void setAct_Usecou(String act_Usecou) {
		this.act_Usecou = act_Usecou;
	}
	
	
	
	
	
}