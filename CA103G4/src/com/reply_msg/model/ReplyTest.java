package com.reply_msg.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReplyTest {
		
	public static void main(String[] args) {
//		insert
		ReplyVO rm1=new ReplyVO();
		rm1.setMem_No("M000001");
		rm1.setPost_No("20180917-000001");
		rm1.setRply_Cont("gogogogogogo");
		ReplyJDBCDAO dao =new ReplyJDBCDAO();
		dao.insert(rm1);
		
//		update_status done
//		ReplyVO rm2=new ReplyVO();
//		rm2.setRply_Status("RM2");
//		rm2.setRply_No("201809171006-01");
//		rm2.setRply_Cont("Test");;
//		rm2.setRply_Time(new Timestamp(System.currentTimeMillis()));
//		 dao.update(rm2);
		
		
//		getAll() done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
//		 List<ReplyVO> licv=dao.getAll();
//		 for(ReplyVO rm3:licv) {
//			 System.out.println(rm3.getRply_No());
//			 System.out.println(rm3.getPost_No());
//			 System.out.println(rm3.getMem_No());
//			 System.out.println(rm3.getRply_Cont());
//			 System.out.println(rm3.getRply_Status());
//			System.out.println(fmt2.format(rm3.getRply_Time()));
//			 System.out.println("=========================");
//		 }		
		
		
		
		
	}

}
