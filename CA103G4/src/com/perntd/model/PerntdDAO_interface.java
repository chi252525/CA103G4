package com.perntd.model;

import java.util.List;

public interface PerntdDAO_interface {
	public void insert(PerntdVO perntdVO);
	public void update(PerntdVO perntdVO);
	public void delete(String perntd_No);
	public PerntdVO findByPrimaryKey(String perntd_No);
	public List<PerntdVO> getAll();
}
