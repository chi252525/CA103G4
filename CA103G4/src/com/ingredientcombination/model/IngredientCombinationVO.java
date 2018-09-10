package com.ingredientcombination.model;

public class IngredientCombinationVO implements java.io.Serializable{
	
	private String Custom_No;
	private String Ingdt_Id;
	
	public IngredientCombinationVO() {
		super();
	}
	
	public IngredientCombinationVO(String Custom_No, String Ingdt_Id) {
		super();
		this.Custom_No = Custom_No;
		this.Ingdt_Id = Ingdt_Id;
	}
	
	public String getCustom_No() {
		// TODO Auto-generated method stub
		return Custom_No;
	}
	
	public void setCustom_No(String Custom_No) {
		this.Custom_No = Custom_No;
	}
	
	public String getIngdt_Id() {
		// TODO Auto-generated method stub
		return Ingdt_Id;
	}
	
	public void setIngdt_Id(String Ingdt_Id) {
		this.Ingdt_Id = Ingdt_Id;
	}
	
}
