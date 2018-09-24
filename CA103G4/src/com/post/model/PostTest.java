package com.post.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


public class PostTest {


	public static void main(String[] args)  {
//      insert  done
//		PostVO pv1=new PostVO();
//		pv1.setMem_No("M000001");
//		pv1.setCustom_No("C0000000001");
//		pv1.setPost_Cont("totototototoot");
//		pv1.setPost_Eva(1);
//		byte[] post_Photo;
//		try {
//			post_Photo = getPictureByteArray("items/Bing2.jpg");
//			pv1.setPost_Photo(post_Photo);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		pv1.setPost_Time(new Timestamp(System.currentTimeMillis()));
		PostJDBCDAO dao =new PostJDBCDAO();
//		dao.insert(pv1);	
//		//  update done
//		PostVO pv2=new PostVO();
//		pv2.setMem_No("M000001");
//		pv2.setCustom_No("C0000000001");
//		pv2.setPost_Cont("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//		try {
//			byte[] post_Photo2 = getPictureByteArray("items/Bing2.jpg");
//			pv2.setPost_Photo(post_Photo2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		pv2.setPost_Eva(4);
//		pv2.setPost_Time(new Timestamp(System.currentTimeMillis()));
//		pv2.setPost_No("20180917-000001");
//		 dao.update(pv2);
//		
		 
		 
		 
		
		//delete  done
//		dao.delete("20180916-000010");
		
		//findbymemno done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//		List<PostVO> licv=dao.findbyMem_No("M000001");
//		 for(PostVO pv31:licv) {
//			 System.out.println(pv31.getPost_No());
//			 System.out.println(pv31.getMem_No());
//			 System.out.println(pv31.getCustom_No());
//			 System.out.println(pv31.getPost_Cont() );
//			 System.out.println(pv31.getPost_Eva());
//			System.out.println(fmt2.format(pv31.getPost_Time()));
//			 System.out.println("=========================");
//		 }
		 
		//findbycustomno
//			SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//			List<PostVO> licv =dao.findbyCustom_No("C0000000001");
//			for(PostVO pv5:licv) {
//				 System.out.println(pv5.getPost_No());
//				 System.out.println(pv5.getMem_No());
//				 System.out.println(pv5.getCustom_No());
//				 System.out.println(pv5.getPost_Cont() );
//				 System.out.println(pv5.getPost_Eva());
//				System.out.println(fmt2.format(pv5.getPost_Time()));
//				 System.out.println("=========================");
//			 }		
		
			SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			List<PostVO> licv =dao.findbyYearandMonth("2018","9");
			for(PostVO pv5:licv) {
				 System.out.println(pv5.getPost_No());
				 System.out.println(pv5.getMem_No());
				 System.out.println(pv5.getCustom_No());
				 System.out.println(pv5.getPost_Cont() );
				 System.out.println(pv5.getPost_Eva());
				System.out.println(fmt2.format(pv5.getPost_Time()));
				 System.out.println("=========================");
			 }
		 
		 
//		 getAll done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
////	
//		 List<PostVO> licv=dao.getAll();
//		 for(PostVO pv6:licv) {
//			 System.out.println(pv6.getPost_No());
//			 System.out.println(pv6.getMem_No());
//			 System.out.println(pv6.getCustom_No());
//			 System.out.println(pv6.getPost_Cont() );
//			 System.out.println(pv6.getPost_Eva());
//			System.out.println(fmt2.format(pv6.getPost_Time()));
//			 System.out.println("=========================");
//		 }		
//		 SimpleDateFormat fmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//			PostVO pv3 =dao.findByPrimaryKey("20180916-000001");
//			System.out.println(pv3.getPost_No());
//			 System.out.println(pv3.getMem_No());
//			 System.out.println(pv3.getCustom_No());
//			 System.out.println(pv3.getPost_Cont() );
//			 System.out.println(pv3.getPost_Eva());
//			System.out.println(fmt3.format(pv3.getPost_Time()));
//			 System.out.println("=========================");
		 
		 
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			//write(byte[] b, int off, int len) 
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
		//  toByteArray() 獲取數據。
	}

}
