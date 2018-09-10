package com.ingclass.model;

public class IngclassVO implements java.io.Serializable{

	private String Ingdtc_Id;
	private String Ingdtc_Name;
	
	public IngclassVO() {
		super();
	}
	
	public IngclassVO(String Ingdtc_Id, String Ingdtc_Name) {
		super();
		this.Ingdtc_Id = Ingdtc_Id;
		this.Ingdtc_Name = Ingdtc_Name;
	}
	
	public String getIngdtc_Id() {
		return Ingdtc_Id;
	}
	
	public void setIngdtc_Id(String Ingdtc_Id) {
		this.Ingdtc_Id = Ingdtc_Id;
	}
	
	
	public String getIngdtc_Name() {
		return Ingdtc_Name;
	}
	
	public void setIngdtc_Name(String Ingdtc_Name) {
		this.Ingdtc_Name = Ingdtc_Name;
	}
	
	
}
