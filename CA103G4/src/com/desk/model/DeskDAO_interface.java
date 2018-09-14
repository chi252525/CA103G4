package com.desk.model;

import java.util.*;

public interface DeskDAO_interface {
	public void insert(DeskVO deskVO);
    public void update(DeskVO deskVO);
    public DeskVO findByPrimaryKey(String dek_no);
    public List<DeskVO> getAll();
}
