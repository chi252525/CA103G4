package com.feature.model;

import java.io.Serializable;

public class FeatureVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fea_No;
	private String fea_Name;
	public FeatureVO() {
		super();
	}
	public FeatureVO(String fea_No, String fea_Name) {
		super();
		this.fea_No = fea_No;
		this.fea_Name = fea_Name;
	}
	public String getFea_No() {
		return fea_No;
	}
	public void setFea_No(String fea_No) {
		this.fea_No = fea_No;
	}
	public String getFea_Name() {
		return fea_Name;
	}
	public void setFea_Name(String fea_Name) {
		this.fea_Name = fea_Name;
	}		
}
