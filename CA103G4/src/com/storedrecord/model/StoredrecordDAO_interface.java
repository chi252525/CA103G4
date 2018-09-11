package com.storedrecord.model;
import java.util.List;

public interface StoredrecordDAO_interface {
	void insert(StoredrecordVO storedrecordVO);
	void update(StoredrecordVO storedrecordVO);
	void delete(String Storedrecord_No);
	StoredrecordVO findByPrimaryKey(String Storedrecord_Num);
	List<StoredrecordVO> getAll();
	StoredrecordVO findByMem_no(String mem_No);
	StoredrecordVO findByMon(String Mon);
}


