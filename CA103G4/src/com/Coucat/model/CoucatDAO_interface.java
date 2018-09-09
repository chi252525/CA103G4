package com.coucat.model;

import java.util.List;

public interface CoucatDAO_interface {
	void insert(CoucatVO coucatVO);
	void update(CoucatVO coucatVO);
	CoucatVO findByCata(String coucat_Cata);
	List<CoucatVO> getAll();
}
