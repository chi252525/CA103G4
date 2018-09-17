package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memVO);
	public void update(MemberVO memVO);
	public void changeStatus(MemberVO memVO);
	public List<MemberVO> getAll();
	
}
