package com.Activity.model;

import java.util.List;

public class ActivityDVOTest {

	public static void main(String[] args) {
//		insert的執行 done
//		ActivityVO vo1=new ActivityVO();
//		vo1.setCoucat_No("20180908-000002");
//		vo1.setAct_Cat("a1");
//		vo1.setAct_Name("gfdgfd");
//		vo1.setAct_Content("yoyoyoy");
//		vo1.setAct_Start("2018-09-05 10:10");
//		vo1.setAct_End("2018-09-06 11:20");
//		vo1.setAct_Usecou("AU1");
		ActivityDAO dao =new ActivityDAO();
//		 dao.insert(vo1);

		//  update的執行 done
//		 ActivityVO vo2=new ActivityVO();
//		 vo2.setAct_No("201809-0001");
//		 vo2.setCoucat_No("20180908-000003");
//		 vo2.setAct_Name("totototoot");
//		 vo2.setAct_Cat("a2");
//		 vo2.setAct_Content("gghjhgjdf");
//		 vo2.setAct_Start("2018-09-05 10:10");
//		 vo2.setAct_End("2018-09-06 11:20");
//		 vo2.setAct_Usecou("AU1");
//		 dao.update(vo2);
		 
//		 findByDate_between的執行 done
////		 
//		 ActivityVO  vo3=dao.findByDate_between("2018-09-12","2018-09-22","2018-09-12","2018-12-22");
//		 System.out.println(vo3.getAct_No());
//		 System.out.println(vo3.getCoucat_No());
//		 System.out.println(vo3.getAct_Cat());
//		 System.out.println(vo3.getAct_Name());
//		 System.out.println(vo3.getAct_Content());
//		 System.out.println(vo3.getAct_Start());
//		 System.out.println(vo3.getAct_End());
		 
//		 getAll的執行done
		
//		 List<ActivityVO> licv=dao.getAll();
//		 for(ActivityVO vo3:licv) {
//			 System.out.println(vo3.getAct_No());
//			 System.out.println(vo3.getCoucat_No());
//			 System.out.println(vo3.getAct_Cat());
//			 System.out.println(vo3.getAct_Name());
//			 System.out.println(vo3.getAct_Content());
//			 System.out.println(vo3.getAct_Start());
//			 System.out.println(vo3.getAct_End());
//			 System.out.println("=========================");
//		 }
		 
//		 findByAct_Cata的執行 done
//		 
		 ActivityVO  vo4=dao.findByAct_Cata("CC2");
		 System.out.println(vo4.getAct_No());
		 System.out.println(vo4.getCoucat_No());
		 System.out.println(vo4.getAct_Cat());
		 System.out.println(vo4.getAct_Name());
		 System.out.println(vo4.getAct_Content());
		 System.out.println(vo4.getAct_Start());
		 System.out.println(vo4.getAct_End());
		 System.out.println("=========================");
		
//		 }
//		 
	}

}