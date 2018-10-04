package com.orderform.model;

import java.sql.Date;
import java.util.List;

import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformVO {
	private String order_no;
	private String dek_no;
	private String mem_no;
	private String branch_no;
	private String deliv_no;
	private Integer order_type;
	private Integer order_price;
	private Integer order_status;
	private String deliv_addres;
	private Integer order_pstatus;
	private Date order_date;
	private List<OrderinvoiceVO> orderInvoiceList;
	
	
	
	
	public OrderformVO(String order_no, String dek_no, String mem_no, String branch_no, String deliv_no,
			Integer order_type, Integer order_price, Integer order_status, String deliv_addres, Integer order_pstatus,
			Date order_date) {
		super();
		this.order_no = order_no;
		this.dek_no = dek_no;
		this.mem_no = mem_no;
		this.branch_no = branch_no;
		this.deliv_no = deliv_no;
		this.order_type = order_type;
		this.order_price = order_price;
		this.order_status = order_status;
		this.deliv_addres = deliv_addres;
		this.order_pstatus = order_pstatus;
		this.order_date = order_date;
	}
	public OrderformVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getDek_no() {
		return dek_no;
	}
	public void setDek_no(String dek_no) {
		this.dek_no = dek_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getBranch_no() {
		return branch_no;
	}
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
	public String getDeliv_no() {
		return deliv_no;
	}
	public void setDeliv_no(String deliv_no) {
		this.deliv_no = deliv_no;
	}
	public Integer getOrder_type() {
		return order_type;
	}
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	public Integer getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Integer order_price) {
		this.order_price = order_price;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public String getDeliv_addres() {
		return deliv_addres;
	}
	public void setDeliv_addres(String deliv_addres) {
		this.deliv_addres = deliv_addres;
	}
	public Integer getOrder_pstatus() {
		return order_pstatus;
	}
	public void setOrder_pstatus(Integer order_pstatus) {
		this.order_pstatus = order_pstatus;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public List<OrderinvoiceVO> getOrderInvoiceList() {
		return orderInvoiceList;
	}
	public void setOrderInvoiceList(List<OrderinvoiceVO> orderInvoiceList) {
		this.orderInvoiceList = orderInvoiceList;
	}


}
