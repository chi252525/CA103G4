package com.storedrecord.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class StoredrecordService {
	private StoredrecordDAO_interface dao;
	
	public StoredrecordService(){
		
		dao =new StoredrecordDAO();
	}
	
	public StoredrecordVO addStoredrecord(String mem_No, Timestamp stor_Date_No, Integer stor_Point, Integer drew_Point,Integer stor_Status) throws SQLException {
		
		StoredrecordVO srVO  = new StoredrecordVO();
		
		srVO.setMem_No(mem_No);
		srVO.setStor_Date(stor_Date_No);
		srVO.setDrew_Point(drew_Point);
		srVO.setStor_Point(stor_Point);
		srVO.setStor_Status(stor_Status);
		String stor_No = dao.insert(srVO);
		srVO.setStor_No(stor_No);
		return srVO;	
	}
	
	public StoredrecordVO updateStoredrecord(String stor_No,String mem_No, Timestamp stor_Date, Integer stor_Point, Integer drew_Point,Integer stor_Status) {
		StoredrecordVO srVO  = new StoredrecordVO();
		srVO.setStor_No(stor_No);
		srVO.setMem_No(mem_No);
		srVO.setStor_Date(stor_Date);
		srVO.setDrew_Point(drew_Point);
		srVO.setStor_Point(stor_Point);
		srVO.setStor_Status(stor_Status);
		dao.update(srVO);
		return srVO;
	}
	
	public StoredrecordVO getOneStoredrecord(String stor_No) {
		return dao.findByPrimaryKey(stor_No);
	}
	
	public List<StoredrecordVO> findByMem_no(String mem_No) {
		return dao.findByMem_no(mem_No);
	}
	
	public List<StoredrecordVO> getAll(){
		return dao.getAll();
	}	
	
	public void delete(String stor_No) {
		dao.delete(stor_No);
	}
	
	public List<StoredrecordVO> findByMon_Year(int Mon, int Year){
		return dao.findByMon_Year(Mon, Year);
	}
	
	public List<StoredrecordVO> findByMon_Year_memNo(int Mon, int Year, String mem_No){
		return dao.findByMon_Year_MemNo(Mon, Year, mem_No);
	}
	
}
