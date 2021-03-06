package com.coucat.model;

import java.util.List;

public interface CoucatDAO_interface {
	void insert(CoucatVO coucatVO);
	void update(CoucatVO coucatVO);
	public List<CoucatVO> findByCata(String coucat_Cata);
	public List<CoucatVO> getAll();
	public byte[] getPic(String coucat_No);
	public CoucatVO getOneCoucat(String coucat_No);

}
