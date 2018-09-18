package com.ingredients.model;

public class IngredientsVO implements java.io.Serializable{

	private String ingdt_Id;
	private String ingdtc_Id;
	private String ingdt_Name;
	private Integer ingdt_Status;
	private String ingdt_Point;
	private String ingdt_Unit;
	private Integer ingdt_Price;
	private byte[] ingdt_Photo;
	
	public IngredientsVO() {
		super();
	}
	
	public IngredientsVO(String ingdt_Id, String ingdtc_Id, String ingdt_Name, Integer ingdt_Status, String ingdt_Point,String ingdt_Unit, Integer ingdt_Price, byte[] ingdt_Photo) {
		super();
		this.ingdt_Id = ingdt_Id;
		this.ingdtc_Id = ingdtc_Id;
		this.ingdt_Name = ingdt_Name;
		this.ingdt_Status = ingdt_Status;
		this.ingdt_Point = ingdt_Point;
		this.ingdt_Unit = ingdt_Unit;
		this.ingdt_Price = ingdt_Price;
		this.ingdt_Photo = ingdt_Photo;
	}
	
	
	
	public String getingdt_Id() {
		return ingdt_Id;
	}

	public void setingdt_Id(String ingdt_Id) {
		this.ingdt_Id = ingdt_Id;
	}

	public String getingdtc_Id() {
		return ingdtc_Id;
	}

	public void setingdtc_Id(String ingdtc_Id) {
		this.ingdtc_Id = ingdtc_Id;
	}

	public String getingdt_Name() {
		return ingdt_Name;
	}

	public void setingdt_Name(String ingdt_Name) {
		this.ingdt_Name = ingdt_Name;
	}

	public Integer getingdt_Status() {
		return ingdt_Status;
	}

	public void setingdt_Status(Integer ingdt_Status) {
		this.ingdt_Status = ingdt_Status;
	}

	public String getingdt_Point() {
		return ingdt_Point;
	}

	public void setingdt_Point(String ingdt_Point) {
		this.ingdt_Point = ingdt_Point;
	}

	public String getingdt_Unit() {
		return ingdt_Unit;
	}

	public void setingdt_Unit(String ingdt_Unit) {
		this.ingdt_Unit = ingdt_Unit;
	}

	public Integer getingdt_Price() {
		return ingdt_Price;
	}

	public void setingdt_Price(Integer ingdt_Price) {
		this.ingdt_Price = ingdt_Price;
	}
	
	public byte[] getingdt_Photo() {
		return ingdt_Photo;
	}
	
	public void setingdt_Photo(byte[] ingdt_Photo) {
		this.ingdt_Photo = ingdt_Photo;
	}


	
}
