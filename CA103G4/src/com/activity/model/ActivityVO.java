package com.activity.model;
import java.sql.*;

import com.member.model.MemberVO;

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
	private Timestamp act_PreAddTime;
	private Timestamp act_PreOffTime;
	private Timestamp act_Start;
	private Timestamp act_End;
	private Integer act_Status;
	private Integer act_Views;
	
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
	public Timestamp getAct_PreAddTime() {
		return act_PreAddTime;
	}
	public void setAct_PreAddTime(Timestamp act_PreAddTime) {
		this.act_PreAddTime = act_PreAddTime;
	}
	public Timestamp getAct_PreOffTime() {
		return act_PreOffTime;
	}
	public void setAct_PreOffTime(Timestamp act_PreOffTime) {
		this.act_PreOffTime = act_PreOffTime;
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
	public Integer getAct_Status() {
		return act_Status;
	}
	public void setAct_Status(Integer act_Status) {
		this.act_Status = act_Status;
	}
	public Integer getAct_Views() {
		return act_Views;
	}
	public void setAct_Views(Integer act_Views) {
		this.act_Views = act_Views;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ActivityVO) {
			ActivityVO actvo = (ActivityVO)obj;
			return this.act_No.equals(actvo.act_No);
		}
		return super.equals(obj);
	}
	

	
	
	
	
	
	
}