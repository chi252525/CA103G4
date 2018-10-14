package com.desk.model;

import java.util.*;

import com.reservation.model.ResVO;

public interface DeskDAO_interface {
	public void insert(DeskVO deskVO);
    public void update(DeskVO deskVO);
    public DeskVO findByPrimaryKey(String dek_no);
    public List<DeskVO> getAll();
    public void insertAutoGK(DeskVO deskVO, ResVO resVO);
    public List<DeskVO> getByBr(String branch_no);
}
