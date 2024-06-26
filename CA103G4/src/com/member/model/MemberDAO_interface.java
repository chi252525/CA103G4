package com.member.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.couponhistory.model.CouponhistoryVO;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memVO);
	public void update(MemberVO memVO);
	public void changeStatus(MemberVO memVO);
	public MemberVO findById(String mem_Id);
	public List<MemberVO> getAll();
	public MemberVO findByPrimaryKey(String mem_No);
//	public MemberVO compareMemId(String mem_Id);
	public List<MemberVO> getCouponByMem(String mem_No);
	public void update2(String mem_NO, Integer stor_Point, Connection con) throws SQLException;
	public void update3(MemberVO memVO);
}
	