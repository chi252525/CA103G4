package com.sysnt.model;

import java.util.List;

public interface SysntDAO_interface {
	public void insert(SysntVO ntVO);
	public void update(SysntVO ntVO);
	public void delete(String nt_No);
	public SysntVO findByPrimaryKey(String nt_No);
	public List<SysntVO> getAll();
}
