package com.reply_msg.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Reply_MsgTest {
		
	public static void main(String[] args) {
//		insert
		Reply_MsgVO rm1=new Reply_MsgVO();
		rm1.setMem_No("M000001");
		rm1.setPost_No("20180908-000001");
		rm1.setRply_Cont("gogogogogogo");
		Reply_MsgDAO dao =new Reply_MsgDAO();
		dao.insert(rm1);
		
//		update_status
//		Reply_MsgVO rm2=new Reply_MsgVO();
//		rm2.setRply_Status("RM2");
//		rm2.setPost_No("20180908-000001");
//		rm2.setRply_Time(new Timestamp(System.currentTimeMillis()));
////		 dao.update(rm2);
		
		
//		getAll()
		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
		 List<Reply_MsgVO> licv=dao.getAll();
		 for(Reply_MsgVO rm3:licv) {
			 System.out.println(rm3.getRply_No());
			 System.out.println(rm3.getPost_No());
			 System.out.println(rm3.getMem_No());
			 System.out.println(rm3.getRply_Cont());
			 System.out.println(rm3.getRply_Status());
			System.out.println(fmt2.format(rm3.getRply_Time()));
			 System.out.println("=========================");
		 }		
		
		
		
		
	}

}
