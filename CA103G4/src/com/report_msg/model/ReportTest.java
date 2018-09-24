package com.report_msg.model;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReportTest {

	public static void main(String[] args) {
		

		ReportVO rm1=new ReportVO();
		rm1.setRpt_Status("RS2");
		rm1.setRpt_Rsm("RR2");
		ReportJDBCDAO dao= new ReportJDBCDAO();
		dao.updateStatus(rm1);
		
		//findbystatus done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//		 List<ReportVO> licv=dao.findbyStatus("RS1");
//		 for(ReportVO rm2:licv) {
//			 System.out.println(rm2.getMem_No());
//			 System.out.println(rm2.getPost_No());
//			 System.out.println(rm2.getRpt_Cont());
//			 System.out.println(rm2.getRpt_Rsm());
//			 System.out.println(rm2.getRpt_Status());
//			 System.out.println(fmt2.format(rm2.getRpt_Time()));
//			 System.out.println("=========================");
//		 }
		
		
//		 getAll done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//		 List<ReportVO> licv=dao.getAll();
//		 for(ReportVO rm3:licv) {
//			 System.out.println(rm3.getRpt_No());
//			 System.out.println(rm3.getMem_No());
//			 System.out.println(rm3.getPost_No());
//			 System.out.println(rm3.getRpt_Cont());
//			 System.out.println(rm3.getRpt_Rsm());
//			 System.out.println(rm3.getRpt_Status());
//			 System.out.println(fmt2.format(rm3.getRpt_Time()));
//			 System.out.println("=========================");
//		 }
		//findbyPK done
//		 SimpleDateFormat fmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//			ReportVO rm3 =dao.findByPrimaryKey("201809171624-01");
//			 System.out.println(rm3.getRpt_No());
//		 System.out.println(rm3.getMem_No());
//		 System.out.println(rm3.getPost_No());
//		 System.out.println(rm3.getRpt_Cont());
//		 System.out.println(rm3.getRpt_Rsm());
//		 System.out.println(rm3.getRpt_Status());
//		 System.out.println(fmt3.format(rm3.getRpt_Time()));
//			 System.out.println("=========================");
	}

}
