package com.activity.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ActivityDVOTest {

	public static void main(String[] args) {
//		insert的執行 done
//		ActivityJDBCDAO dao=new ActivityJDBCDAO();
//		ActivityVO vo1=new ActivityVO();
//		vo1.setCoucat_No("20181001-000001");
//		vo1.setAct_Cat("a1");
//		vo1.setAct_Name("gfdgfd");
		
//		try {
//			byte[] pic = getPictureByteArray("items/Bing3.jpeg");
//			byte[] pic2 = getPictureByteArray("items/Bing3.jpeg");
//			vo1.setAct_Carousel(pic);
//			vo1.setAct_Pic(pic2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
//		 dao.insert(vo1);

		//  update的執行 done
//		 ActivityVO vo2=new ActivityVO();
//		 vo2.setAct_No("201810-0001");
//		 vo2.setCoucat_No("20181001-000001");
//		 vo2.setAct_Name("rrrrrrrrrrrrrrrrrrrr");
//		 vo2.setAct_Cat("a2");
//		 vo2.setAct_Content("gTEEEEEEEEEE");
//		 vo2.setAct_Start(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
//		 vo2.setAct_End(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));

//			try {
//				byte[] pic3 = getPictureByteArray("items/Bing3.jpeg");
//				byte[] pic4 = getPictureByteArray("items/Bing3.jpeg");
//				vo2.setAct_Carousel(pic3);
//				vo2.setAct_Pic(pic4);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 dao.update(vo2);
		 
//		 findByDate_between的執行 done
////		 
//		 List<ActivityVO>  licv=dao.findByDate_between(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"),java.sql.Timestamp.valueOf("2018-03-03 13:35:57"),
//				 java.sql.Timestamp.valueOf("2018-03-03 13:35:57"),java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
//		 SimpleDateFormat fmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
//		 for(ActivityVO vo3:licv) {
//		 System.out.println(vo3.getAct_No());
//		 System.out.println(vo3.getCoucat_No());
//		 System.out.println(vo3.getAct_Cat());
//		 System.out.println(vo3.getAct_Name());
//		 System.out.println(vo3.getAct_Content());
//		 System.out.println( fmt1.format(vo3.getAct_Start()));
//		 System.out.println(fmt1.format(vo3.getAct_End()));
//		 }
//		 
//		 getAll的執行done
		ActivityJDBCDAO dao =new ActivityJDBCDAO();
		 List<ActivityVO> licv1=dao.getAll();
		 SimpleDateFormat fmt4=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
		 for(ActivityVO vo4:licv1) {
		 System.out.println(vo4.getAct_No());
		 System.out.println(vo4.getCoucat_No());
		 System.out.println(vo4.getAct_Cat());
		 System.out.println(vo4.getAct_Name());
		 System.out.println(fmt4.format(vo4.getAct_Start()));
		 System.out.println(fmt4.format(vo4.getAct_End()));
		 System.out.println("========================="); 	
		 }
		 
//		 findByAct_Cata的執行 done
//		 SimpleDateFormat fmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
//		 List<ActivityVO>  list=dao.findByAct_Cata("AC2");
//		 for(ActivityVO vo4:list) {
//		 System.out.println(vo4.getAct_No());
//		 System.out.println(vo4.getCoucat_No());
//		 System.out.println(vo4.getAct_Cat());
//		 System.out.println(vo4.getAct_Name());
//		 System.out.println(vo4.getAct_Content());
//		 System.out.println(fmt3.format(vo4.getAct_Start()));
//		 System.out.println(fmt3.format(vo4.getAct_End()));
//		 System.out.println("========================="); 	
//		 }
	}
	// 使用byte[]方式
				public static byte[] getPictureByteArray(String path) throws IOException {
					File file = new File(path);
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[fis.available()];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
						//write(byte[] b, int off, int len) 
				        //?指定 byte ??中?偏移量 off ?始的 len ?字??入此 byte ???出流。
					}
					baos.close();
					fis.close();

					return baos.toByteArray();
					//  toByteArray() 獲取數據。
				}
}