package com.coucat.model;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class CoucatDVOTest {

	public static void main(String[] args) {
//		insert done
		CoucatVO cv1=new CoucatVO();
		cv1.setCoucat_Name("Test");
		cv1.setCoucat_Cata("CC2");
		cv1.setCoucat_Cont("fff");
		cv1.setCoucat_Value(100);
		cv1.setCoucat_Valid(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
		cv1.setCoucat_Invalid(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
		cv1.setCoucat_Amo(10000);
		 byte[] pic;
		try {
			pic = getPictureByteArray("items/Bing3.jpeg");
			 cv1.setCoucat_Pic(pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		 CoucatDAO dao =new CoucatDAO();
		 dao.insert(cv1);
		
//   	update done
//		CoucatVO cv2=new CoucatVO();
//		cv2.setCoucat_No("20180911-000002");
//		cv2.setCoucat_Name("Test");
//		cv2.setCoucat_Cata("CC2");
//		cv2.setCoucat_Cont("fff");
//		cv2.setCoucat_Value(100);
//		cv2.setCoucat_Valid(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
//		cv2.setCoucat_Invalid(java.sql.Timestamp.valueOf("2018-03-03 13:35:57"));
//		cv2.setCoucat_Amo(10000);
//		dao.update(cv2);
		 
//		 getAll done
		
//		 List<CoucatVO> licv=dao.getAll();
		 SimpleDateFormat fmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
//		 for(CoucatVO cv4:licv) {
//			 System.out.println(cv4.getCoucat_No());
//			 System.out.println(cv4.getCoucat_Name());
//			 System.out.println(cv4.getCoucat_Cata());
//			 System.out.println(cv4.getCoucat_Cont());
//			 System.out.println(cv4.getCoucat_Value());
//			 System.out.println(fmt1.format(cv4.getCoucat_Valid()));
//			 System.out.println(fmt1.format(cv4.getCoucat_Invalid()));
//			 System.out.println(cv4.getCoucat_Amo());
//			 System.out.println("=========================");
//		 }
//		 findbyCATADONE
		 SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
		 CoucatVO cv5=dao.findByCata("CC1");
		 System.out.println(cv5.getCoucat_No());
		 System.out.println(cv5.getCoucat_Name());
		 System.out.println(cv5.getCoucat_Cata());
		 System.out.println(cv5.getCoucat_Cont());
		 System.out.println(cv5.getCoucat_Value());
		 System.out.println(fmt2.format(cv5.getCoucat_Valid()));
		 System.out.println(fmt2.format(cv5.getCoucat_Invalid()));
		 System.out.println(cv5.getCoucat_Amo());
		 System.out.println("=========================");
		 
		 
	}
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
