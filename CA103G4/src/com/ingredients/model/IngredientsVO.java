package com.ingredients.model;

public class IngredientsVO implements java.io.Serializable{

	private String Ingdt_Id;
	private String Ingdtc_Id;
	private String Ingdt_Name;
	private Integer Ingdt_Status;
	private String Ingdt_Point;
	private String Ingdt_Unit;
	private Integer Ingdt_Price;
	
	public IngredientsVO() {
		super();
	}
	
	public IngredientsVO(String Ingdt_Id, String Ingdtc_Id, String Ingdt_Name, Integer Ingdt_Status, String Ingdt_Point,String Ingdt_Unit, Integer Ingdt_Price) {
		super();
		this.Ingdt_Id = Ingdt_Id;
		this.Ingdtc_Id = Ingdtc_Id;
		this.Ingdt_Name = Ingdt_Name;
		this.Ingdt_Status = Ingdt_Status;
		this.Ingdt_Point = Ingdt_Point;
		this.Ingdt_Unit = Ingdt_Unit;
		this.Ingdt_Price = Ingdt_Price;
	}
	
	
	
	public String getIngdt_Id() {
		return Ingdt_Id;
	}

	public void setIngdt_Id(String ingdt_Id) {
		Ingdt_Id = ingdt_Id;
	}

	public String getIngdtc_Id() {
		return Ingdtc_Id;
	}

	public void setIngdtc_Id(String ingdtc_Id) {
		Ingdtc_Id = ingdtc_Id;
	}

	public String getIngdt_Name() {
		return Ingdt_Name;
	}

	public void setIngdt_Name(String ingdt_Name) {
		Ingdt_Name = ingdt_Name;
	}

	public Integer getIngdt_Status() {
		return Ingdt_Status;
	}

	public void setIngdt_Status(Integer ingdt_Status) {
		Ingdt_Status = ingdt_Status;
	}

	public String getIngdt_Point() {
		return Ingdt_Point;
	}

	public void setIngdt_Point(String ingdt_Point) {
		Ingdt_Point = ingdt_Point;
	}

	public String getIngdt_Unit() {
		return Ingdt_Unit;
	}

	public void setIngdt_Unit(String ingdt_Unit) {
		Ingdt_Unit = ingdt_Unit;
	}

	public Integer getIngdt_Price() {
		return Ingdt_Price;
	}

	public void setIngdt_Price(Integer ingdt_Price) {
		Ingdt_Price = ingdt_Price;
	}


	
}
