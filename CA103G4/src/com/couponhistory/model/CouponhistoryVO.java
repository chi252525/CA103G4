package com.couponhistory.model;

import java.io.Serializable;

import com.activity.model.ActivityVO;

public class CouponhistoryVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String coup_sn;
	private String mem_no;
	private String order_no;
	private Integer coup_state;

	public String getCoup_sn() {
		return coup_sn;
	}

	public void setCoup_sn(String coup_sn) {
		this.coup_sn = coup_sn;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Integer getCoup_state() {
		return coup_state;
	}

	public void setCoup_state(Integer coup_state) {
		this.coup_state = coup_state;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CouponhistoryVO) {
			CouponhistoryVO chvo = (CouponhistoryVO)obj;
			return this.coup_sn.equals(chvo.coup_sn);
		}
		return super.equals(obj);
	}
	
}
