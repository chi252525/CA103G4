package com.coucat.model;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class CoucatDVOTest {

	public static void main(String[] args) {
//		insert done
		try {
			byte[] pic = getPictureByteArray("items/Bing3.jpeg");
			CoucatVO cv1=new CoucatVO("CCCCCCCC",
					 "CC2","CCCCCCC",100,"2018-09-05","2018-09-08",5,pic);
			 CoucatDAO dao =new CoucatDAO();
			 dao.insert(cv1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
//   	update done
//		 CoucatVO cv2=new CoucatVO("20180908-000002","CCCCCCCCC",
//				 "CC2","CCCCCC",80,"2018-09-05 10:00","2018-09-08 12:00",100);
//		 
//		 dao.update(cv2);
		 
//		 getAll done
		
//		 List<CoucatVO> licv=dao.getAll();
//		 for(CoucatVO cv4:licv) {
//			 System.out.println(cv4.getCoucat_No());
//			 System.out.println(cv4.getCoucat_Name());
//			 System.out.println(cv4.getCoucat_Cata());
//			 System.out.println(cv4.getCoucat_Cont());
//			 System.out.println(cv4.getCoucat_Value());
//			 System.out.println(cv4.getCoucat_Valid());
//			 System.out.println(cv4.getCoucat_Invalid());
//			 System.out.println(cv4.getCoucat_Amo());
//			 System.out.println("=========================");
//		 }
//		 findbyCATADONE
//		 CoucatVO cv5=dao.findByCata("CC1");
//		 System.out.println(cv5.getCoucat_No());
//		 System.out.println(cv5.getCoucat_Name());
//		 System.out.println(cv5.getCoucat_Cata());
//		 System.out.println(cv5.getCoucat_Cont());
//		 System.out.println(cv5.getCoucat_Value());
//		 System.out.println(cv5.getCoucat_Valid());
//		 System.out.println(cv5.getCoucat_Invalid());
//		 System.out.println(cv5.getCoucat_Amo());
//		 System.out.println("=========================");
		 
		 
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
