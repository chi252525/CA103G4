package com.custommeals.model;

public class CustommealsVO implements java.io.Serializable{

	private String Custom_No;
	private String Mem_No;
	private String Custom_Name;
	private Integer Custom_Price;
	private byte[] Custom_Photo;

	public CustommealsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustommealsVO(String Custom_No, String Mem_No, String Custom_Name, Integer Custom_Price, byte[] Custom_Photo) {
		super();
		this.Custom_No = Custom_No;
		this.Mem_No = Mem_No;
		this.Custom_Name = Custom_Name;
		this.Custom_Price = Custom_Price;
		this.Custom_Photo = Custom_Photo;
	}

	
	public String getCustom_No() {
		// TODO Auto-generated method stub
		return Custom_No;
	}
	
	public void setCustom_No(String Custom_No) {
		this.Custom_No = Custom_No;
	}

	public String getMem_No() {
		// TODO Auto-generated method stub
		return Mem_No;
	}
	
	public void setMem_No(String Mem_No) {
		this.Mem_No = Mem_No;
	}
	
	public String getCustom_Name() {
		// TODO Auto-generated method stub
		return Custom_Name;
	}
	
	public void setCustom_Name(String Custom_Name) {
		this.Custom_Name = Custom_Name;
	}

	public int getCustom_Price() {
		// TODO Auto-generated method stub
		return Custom_Price;
	}
	
	public void setCustom_Price(Integer Custom_Price) {
		this.Custom_Price = Custom_Price;
	}
	

	public byte[] getCustom_Photo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCustom_Photo(byte[] Custom_Photo) {
		this.Custom_Photo = Custom_Photo;
	}
	

}
