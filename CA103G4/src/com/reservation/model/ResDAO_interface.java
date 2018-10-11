package com.reservation.model;

import java.sql.Connection;
import java.util.*;



public interface ResDAO_interface {
	public void insert(ResVO resVO);
    public void update(ResVO resVO);
    public void delete(String res_no);
    public ResVO findByPrimaryKey(String res_no);
    public List<ResVO> getAll();
    public void insert2(ResVO resVO, Connection con);
    
}
