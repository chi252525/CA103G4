package com.report_msg.model;
 import java.text.SimpleDateFormat;
import java.util.List;
 public class ReportTest {
 	public static void main(String[] args) {
		
 
		ReportJDBCDAO dao= new ReportJDBCDAO();
		dao.updateReportStatus("201809081437-01");
		
		//findbystatus
		SimpleDateFormat fmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
		List<ReportVO> rm2 =dao.findbyStatus("RS1");
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