package com.storedrecord.model;

import java.sql.Timestamp;
import java.util.List;

public class StoredrecordDVOTest {

	public static void main(String[] args) {
		//insert
		StoredrecordVO storedrecordvo = new StoredrecordVO();
//		storedrecordvo.setStor_No("B000000001");
		storedrecordvo.setMem_No("M000002");
		storedrecordvo.setStor_Date(new Timestamp(2018,5,20,50,20,0, 0));
		storedrecordvo.setStor_Point(500);
		storedrecordvo.setDrew_Point(2);
		storedrecordvo.setStor_Status(1);
		StoredrecordDAO dao = new StoredrecordDAO();
		dao.insert(storedrecordvo);
		System.out.println("新增成功!");

		// update
		StoredrecordVO storedrecordvo2 = new StoredrecordVO();
		storedrecordvo2.setStor_No("B000000001");
		storedrecordvo2.setMem_No("M000002");
		storedrecordvo2.setStor_Date(new Timestamp(System.currentTimeMillis()));
		storedrecordvo2.setStor_Point(1000);
		storedrecordvo2.setDrew_Point(5);
		storedrecordvo2.setStor_Status(0);
		dao.update(storedrecordvo2);
		System.out.println("修改完畢!");

		//getAll的執行done
		List<StoredrecordVO> Storlist = dao.getAll();
		int i=1;
		System.out.println("查Stor資料為:");
		for (StoredrecordVO storedrecordvo3 : Storlist) {
			System.out.println("第"+i+"筆資料");
			System.out.println("儲值編號 : "+storedrecordvo3.getStor_No());
			System.out.println("會員編號 : "+storedrecordvo3.getMem_No());
			System.out.println("儲值日期 : "+storedrecordvo3.getStor_Date());
			System.out.println("點數 : "+storedrecordvo3.getStor_Point());
			System.out.println("竹幣 : "+storedrecordvo3.getDrew_Point());
			System.out.println("狀態 : "+storedrecordvo3.getStor_Status());
			System.out.println("=========================");
			i++;
		}

//		 findByMem_No的執行 done 
		StoredrecordVO storedrecordvo4 = dao.findByMem_no("M000002");
		System.out.println("mem_no為M000002的資料為: ");
		System.out.println("儲值編號 : "+storedrecordvo4.getStor_No());
		System.out.println("會員編號 : "+storedrecordvo4.getMem_No());
		System.out.println("儲值日期 : "+storedrecordvo4.getStor_Date());
		System.out.println("點數 : "+storedrecordvo4.getStor_Point());
		System.out.println("竹幣 : "+storedrecordvo4.getDrew_Point());
		System.out.println("狀態 : "+storedrecordvo4.getStor_Status());
		System.out.println("=========================");
		
//		 }
//		 依主鍵查詢
		StoredrecordVO storedrecordvo5 = dao.findByPrimaryKey("B000000003");
		System.out.println("Stor_No = B000000003的資料為:");
//		System.out.println(storedrecordvo5.getStor_No());
		System.out.println("會員編號: "+storedrecordvo5.getMem_No());
		System.out.println("儲值日期: "+storedrecordvo5.getStor_Date());
		System.out.println("點數: "+storedrecordvo5.getStor_Point());
		System.out.println("竹幣: "+storedrecordvo5.getDrew_Point());
		System.out.println("狀態: "+storedrecordvo5.getStor_Status());
		System.out.println("=========================");
		
		//delete
		System.out.println("刪除Stor_No = B000000001的資料");
		dao.delete("B000000001");
		System.out.println("刪除完畢");
		
		//依年月查詢
		System.out.println("查詢2018年9月份資料:");
		List<StoredrecordVO> storedrecordlist = dao.findByMon_Year(9, 2018);
		for(StoredrecordVO sRd : storedrecordlist) {
			System.out.println("儲值編號: "+sRd.getStor_No());
			System.out.println("會員編號: "+sRd.getMem_No());
			System.out.println("儲值日期: "+sRd.getStor_Date());
			System.out.println("點數: "+sRd.getStor_Point());
			System.out.println("竹幣: "+sRd.getDrew_Point());
			System.out.println("狀態: "+sRd.getStor_Status());
			System.out.println("=========================");			
		}	
	}						
}