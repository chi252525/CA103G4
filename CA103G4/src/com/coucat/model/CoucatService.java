package com.coucat.model;

import java.sql.Timestamp;
import java.util.List;

import com.activity.model.ActivityVO;

public class CoucatService {
private CoucatDAO_interface dao;
	
	public CoucatService() {
		dao=new CoucatDAO();
	}
	
	public CoucatVO addCoucat(String coucat_Name,String coucat_Cata,Integer coucat_Value,
			Double coucat_Discount,Integer coucat_Freep,Timestamp coucat_Valid,Timestamp coucat_Invalid
			,Integer coucat_Amo,byte[] coucat_Pic) {
		CoucatVO coucatVO= new CoucatVO();
		coucatVO.setCoucat_Name(coucat_Name);
		coucatVO.setCoucat_Cata(coucat_Cata);
		coucatVO.setCoucat_Value(coucat_Value);
		coucatVO.setCoucat_Discount(coucat_Discount);
		coucatVO.setCoucat_Freep(coucat_Freep);
		coucatVO.setCoucat_Valid(coucat_Valid);
		coucatVO.setCoucat_Invalid(coucat_Invalid);
		coucatVO.setCoucat_Amo(coucat_Amo);
		coucatVO.setCoucat_Pic(coucat_Pic);
		dao.insert(coucatVO);
		return coucatVO;
	}
	
	public CoucatVO updateCoucat(String coucat_Name,String coucat_Cata,Integer coucat_Value,
			Double coucat_Discount,Integer coucat_Freep,Timestamp coucat_Valid,Timestamp coucat_Invalid
			,Integer coucat_Amo,byte[] coucat_Pic,String coucat_No) {
		CoucatVO coucatVO= new CoucatVO();
		coucatVO.setCoucat_Name(coucat_Name);
		coucatVO.setCoucat_Cata(coucat_Cata);
		coucatVO.setCoucat_Value(coucat_Value);
		coucatVO.setCoucat_Discount(coucat_Discount);
		coucatVO.setCoucat_Freep(coucat_Freep);
		coucatVO.setCoucat_Valid(coucat_Valid);
		coucatVO.setCoucat_Invalid(coucat_Invalid);
		coucatVO.setCoucat_Amo(coucat_Amo);
		coucatVO.setCoucat_Pic(coucat_Pic);
		dao.update(coucatVO);
		return coucatVO;
		
	}
	public List<CoucatVO> getAll(){
		return dao.getAll();
				
	}
	
	public List<CoucatVO> getCoucatByCata(String coucat_Cata){
		
		return dao.findByCata(coucat_Cata);
	}
	
	public byte[] getPic(String coucat_No) {
		return dao.getPic(coucat_No);
		
	}
	
	public CoucatVO getOneCoucat(String coucat_No) {
		return dao.getOneCoucat(coucat_No);
	}
	
}
