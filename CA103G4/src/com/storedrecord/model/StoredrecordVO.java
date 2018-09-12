package com.storedrecord.model;
import java.io.Serializable;
import java.sql.Timestamp;

import oracle.sql.TIMESTAMP;

public class StoredrecordVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6737951239643813019L;
	private String stor_No;
	private String mem_No;
	private Timestamp stor_Date;
	private Integer stor_Point;
	private Integer drew_Point;
	private Integer stor_Status;
	
	public String getStor_No() {
		return stor_No;
	}
	public void setStor_No(String stor_No) {
		this.stor_No = stor_No;
	}
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public Timestamp getStor_Date() {
		return stor_Date;
	}
	public void setStor_Date(Timestamp timestamp) {
		this.stor_Date = timestamp;
	}
	public Integer getStor_Point() {
		return stor_Point;
	}
	public void setStor_Point(Integer stor_Point) {
		this.stor_Point = stor_Point;
	}
	public Integer getDrew_Point() {
		return drew_Point;
	}
	public void setDrew_Point(Integer drew_Point) {
		this.drew_Point = drew_Point;
	}
	public Integer getStor_Status() {
		return stor_Status;
	}
	public void setStor_Status(Integer stor_Status) {
		this.stor_Status = stor_Status;
	}
	
	
	
	
	
	
	
}