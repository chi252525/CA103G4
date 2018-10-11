package com.storedrecord.model;
import java.util.List;

public interface StoredrecordDAO_interface {
	String insert(StoredrecordVO storedrecordVO);
	void update(StoredrecordVO storedrecordVO);
	void delete(String Storedrecord_No);
	StoredrecordVO findByPrimaryKey(String Storedrecord_Num);
	List<StoredrecordVO> getAll();
	List<StoredrecordVO> findByMem_no(String mem_No);
	List<StoredrecordVO> findByMon_Year(int Mon, int Year);
	List<StoredrecordVO> findByMon_Year_MemNo(int Mon, int Year, String mem_No);

}


