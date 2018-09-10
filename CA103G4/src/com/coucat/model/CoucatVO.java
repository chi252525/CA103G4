package com.coucat.model;

import java.io.Serializable;


public class CoucatVO implements java.io.Serializable{
	private String coucat_No;
	private String coucat_Name;
	private String coucat_Cata;
	private String coucat_Cont;
	private Integer coucat_Value;
	private Double coucat_Discount;
	private Integer coucat_Freep;
	private String coucat_Valid;
	private String coucat_Invalid;
	private Integer coucat_Amo;
	private byte[] coucat_Pic;
	public CoucatVO(String coucat_Name, String coucat_Cata, String coucat_Cont, Integer coucat_Value,
		 String coucat_Valid, String coucat_Invalid,
			Integer coucat_Amo, byte[] coucat_Pic) {
		super();
		this.coucat_Name = coucat_Name;
		this.coucat_Cata = coucat_Cata;
		this.coucat_Cont = coucat_Cont;
		this.coucat_Value = coucat_Value;
		this.coucat_Valid = coucat_Valid;
		this.coucat_Invalid = coucat_Invalid;
		this.coucat_Amo = coucat_Amo;
		this.coucat_Pic = coucat_Pic;
	}
	
	public CoucatVO(String coucat_Name, String coucat_Cata, String coucat_Cont, Integer coucat_Value,
			 String coucat_Valid, String coucat_Invalid,
			Integer coucat_Amo) {
		super();
		this.coucat_Name = coucat_Name;
		this.coucat_Cata = coucat_Cata;
		this.coucat_Cont = coucat_Cont;
		this.coucat_Value = coucat_Value;
		this.coucat_Valid = coucat_Valid;
		this.coucat_Invalid = coucat_Invalid;
		this.coucat_Amo = coucat_Amo;
	}

	public CoucatVO() {
		// TODO Auto-generated constructor stub
		super();
	}


	public String getCoucat_No() {
		return coucat_No;
	}
	public void setCoucat_No(String coucat_No) {
		this.coucat_No = coucat_No;
	}
	public String getCoucat_Name() {
		return coucat_Name;
	}
	public void setCoucat_Name(String coucat_Name) {
		this.coucat_Name = coucat_Name;
	}
	public String  getCoucat_Cata() {
		return coucat_Cata;
	}
	public void setCoucat_Cata(String  coucat_Cata) {
		this.coucat_Cata = coucat_Cata;
	}
	public String getCoucat_Cont() {
		return coucat_Cont;
	}
	public void setCoucat_Cont(String coucat_Cont) {
		this.coucat_Cont = coucat_Cont;
	}
	public Integer getCoucat_Value() {
		return coucat_Value;
	}
	public void setCoucat_Value(Integer coucat_Value) {
		this.coucat_Value = coucat_Value;
	}
	public Double getCoucat_Discount() {
		return coucat_Discount;
	}
	public void setCoucat_Discount(Double coucat_Discount) {
		this.coucat_Discount = coucat_Discount;
	}
	public Integer getCoucat_Freep() {
		return coucat_Freep;
	}
	public void setCoucat_Freep(Integer coucat_Freep) {
		this.coucat_Freep = coucat_Freep;
	}
	public String getCoucat_Valid() {
		return coucat_Valid;
	}
	public void setCoucat_Valid(String coucat_Valid) {
		this.coucat_Valid = coucat_Valid;
	}
	public String getCoucat_Invalid() {
		return coucat_Invalid;
	}
	public void setCoucat_Invalid(String coucat_Invalid) {
		this.coucat_Invalid = coucat_Invalid;
	}
	public Integer getCoucat_Amo() {
		return coucat_Amo;
	}
	public void setCoucat_Amo(Integer coucat_Amo) {
		this.coucat_Amo = coucat_Amo;
	}
	public byte[] getCoucat_Pic() {
		return coucat_Pic;
	}
	public void setCoucat_Pic(byte[] coucat_Pic) {
		this.coucat_Pic = coucat_Pic;
	}
	
	



}
	