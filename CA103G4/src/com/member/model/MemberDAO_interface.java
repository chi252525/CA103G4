package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memVO);
	public void update(MemberVO memVO);
	public void changeStatus(MemberVO memVO);
	public MemberVO findById(String mem_Id);
	public List<MemberVO> getAll();
	public MemberVO findByPrimaryKey(String mem_No);
//	public MemberVO compareMemId(String mem_Id);
	
}
	