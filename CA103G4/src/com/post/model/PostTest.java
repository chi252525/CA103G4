package com.post.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


public class PostTest {

	public static void main(String[] args) {
//      insert  done
//		PostVO pv1=new PostVO();
//		pv1.setMem_No("M000001");
//		pv1.setCustom_No("C0000000001");
//		pv1.setPost_Cont("totototototoot");
//		pv1.setPost_Eva(1);
//		pv1.setPost_Time(new Timestamp(System.currentTimeMillis()));
		PostDAO dao =new PostDAO();
//		dao.insert(pv1);
			
		//  update done
//		PostVO pv2=new PostVO();
//		pv2.setMem_No("M000001");
//		pv2.setCustom_No("C0000000001");
//		pv2.setPost_Cont("fdsgfsagfga");
//		pv2.setPost_Eva(4);
//		pv2.setPost_Time(new Timestamp(System.currentTimeMillis()));
//		pv2.setPost_No("20180908-000002");
//		 dao.update(pv2);
		
		
		//delete  done
//		dao.delete("20180908-000001");
		
		//findbymemno done
//		SimpleDateFormat fmt1=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//		PostVO pv3 =dao.findbyMem_No("M000001");
//		System.out.println(pv3.getPost_No());
//		 System.out.println(pv3.getMem_No());
//		 System.out.println(pv3.getCustom_No());
//		 System.out.println(pv3.getPost_Cont() );
//		 System.out.println(pv3.getPost_Eva());
//		System.out.println(fmt1.format(pv3.getPost_Time()));
//		 System.out.println("=========================");
		 
		//findbycustomno
			SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			PostVO pv3 =dao.findbyCustom_No("C0000000001");
			System.out.println(pv3.getPost_No());
			 System.out.println(pv3.getMem_No());
			 System.out.println(pv3.getCustom_No());
			 System.out.println(pv3.getPost_Cont() );
			 System.out.println(pv3.getPost_Eva());
			System.out.println(fmt2.format(pv3.getPost_Time()));
			 System.out.println("=========================");
		
//		 getAll done
//		SimpleDateFormat fmt2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
//	
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
	}

}
