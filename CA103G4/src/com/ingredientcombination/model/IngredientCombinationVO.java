package com.ingredientcombination.model;

public class IngredientCombinationVO implements java.io.Serializable{
	
	private String Custom_No;
	private String Ingdt_No;
	
	public IngredientCombinationVO() {
		super();
	}
	
	public IngredientCombinationVO(String Custom_No, String Ingdt_No) {
		super();
		this.Custom_No = Custom_No;
		this.Ingdt_No = Ingdt_No;
	}
	
	public String getCustom_No() {
		// TODO Auto-generated method stub
		return Custom_No;
	}
	
	public void setCustom_No(String Custom_No) {
		this.Custom_No = Custom_No;
	}
	
	public String getIngdt_No() {
		// TODO Auto-generated method stub
		return Ingdt_No;
	}
	
	public void setIngdt_No(String Ingdt_No) {
		this.Ingdt_No = Ingdt_No;
	}
	
}
