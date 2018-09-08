package com.report_msg.model;

import java.text.SimpleDateFormat;

public class Report_MsgTest {

	public static void main(String[] args) {
		

		Report_MsgVO rm1=new Report_MsgVO();
		rm1.setRpt_Status("RS2");
		rm1.setRpt_Rsm("RR2");
		rm1.setRpt_No("201809081437-01");
		Report_MsgDAO dao= new Report_MsgDAO();
		dao.updateStatus(rm1);
		
		//findbystatus
		SimpleDateFormat fmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
		Report_MsgVO rm2 =dao.findbyStatus("RS1");
//		 System.out.println(rm2.getRpt_No());
//		 System.out.println(rm2.getMem_No());
//		 System.out.println(rm2.getPost_No());
//		 System.out.println(rm2.getRpt_Cont());
//		 System.out.println(rm2.getRpt_Rsm());
//		 System.out.println(rm2.getRpt_Status());
//		 System.out.println(fmt2.format(rm2.getRpt_Time()));
		 System.out.println("=========================");
		
		
//		 getAll done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//		 List<Report_MsgVO> licv=dao.getAll();
//		 for(ActivityVO rm3:licv) {
//			 System.out.println(rm3.getRpt_No());
//			 System.out.println(rm3.getMem_No());
//			 System.out.println(rm3.getPost_No());
//			 System.out.println(rm3.getRpt_Cont());
//			 System.out.println(rm3.getRpt_Rsm());
//			 System.out.println(rm3.getRpt_Status());
//			 System.out.println(fmt2.format(rm3.getRpt_Time()));
//			 System.out.println("=========================");
//		 }
	}

}
