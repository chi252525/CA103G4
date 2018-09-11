package com.storedrecord.model;

import java.util.List;
import java.util.Scanner;

public class StoredrecordDVOTest {

	public static void main(String[] args) {
		//insert
		StoredrecordVO mealaddr = new StoredrecordVO();
		mealaddr.setMaddr_No("A000003");
		mealaddr.setMem_No("M000002");
		mealaddr.setMaddr_cname("桃園市");
		mealaddr.setMaddr_addr("338桃園市蘆竹區奉化路23號3樓之7");
		mealaddr.setMaddr_rname("胖子");
		StoredrecordDAO dao = new StoredrecordDAO();
		dao.insert(mealaddr);
		System.out.println("新增成功!");

		// update
		StoredrecordVO mealaddr2 = new StoredrecordVO();
		mealaddr2.setMaddr_No("A000003");
		mealaddr2.setMem_No("M000002");
		mealaddr2.setMaddr_cname("嘉義市");
		mealaddr2.setMaddr_addr("600嘉義市文雅街140巷25弄21號");
		mealaddr2.setMaddr_rname("蔡凱文");
		dao.update(mealaddr2);


		//getAll的執行done
		List<StoredrecordVO> Maddrlist = dao.getAll();
		int i=1;
		System.out.println("查maddr資料為:");
		for (StoredrecordVO mealaddr3 : Maddrlist) {
			System.out.println("第"+i+"筆資料");
			System.out.println(mealaddr3.getMaddr_No());
			System.out.println(mealaddr3.getMem_No());
			System.out.println(mealaddr3.getMaddr_addr());
			System.out.println(mealaddr3.getMaddr_cname());
			System.out.println(mealaddr3.getMaddr_rname());
			System.out.println("=========================");
			i++;
		}

//		 findByAct_Cata的執行 done
//		 
		StoredrecordVO mealaddr4 = dao.findByMem_no("M000002");
		System.out.println("mem_no為M000002的資料為: ");
		System.out.println(mealaddr4.getMaddr_No());
//		System.out.println(mealaddr4.getMem_No());
		System.out.println(mealaddr4.getMaddr_addr());
		System.out.println(mealaddr4.getMaddr_cname());
		System.out.println(mealaddr4.getMaddr_rname());
		System.out.println("=========================");

//		 }
//		 
		StoredrecordVO mealaddr5 = dao.findByPrimaryKey("A000001");
		System.out.println("Maddr_No為A000001的資料為:");
//		System.out.println(mealaddr5.getMaddr_No());
		System.out.println(mealaddr5.getMem_No());
		System.out.println(mealaddr5.getMaddr_addr());
		System.out.println(mealaddr5.getMaddr_cname());
		System.out.println(mealaddr5.getMaddr_rname());
		System.out.println("=========================");
		
		//delete
		System.out.println("刪除Maddr_No為A000003的資料");
		dao.delete("A000003");
		
	}
		
		
		
}