package com.member.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class MemberDAOJDBC_test {

	public static void main(String args[]) {
//新增會員		
		MemberVO memVO = new MemberVO();
		memVO.setMem_Id("ccabba");
		memVO.setMem_Pw("123456");
		memVO.setMem_Name("富察傅恆");
		memVO.setMem_Gender("ms1");
		memVO.setMem_Bir("1991-03-29");
		memVO.setMem_Mail("bbbbbb@gmail.com");
		memVO.setMem_Phone("0923333330");
		memVO.setMem_Receiver("魏瓔珞");
		memVO.setMem_Repno("326");
		memVO.setMem_Recounty("桃園市");
		memVO.setMem_Retown("楊梅區");
		memVO.setMem_Cardnum("4312-2222-1111-5555");
		memVO.setMem_Carddue("07/26");
		memVO.setMem_Bonus(1000);
		
		MemberDAO dao = new MemberDAO();
		dao.insert(memVO);	
	
//修改會員資料
//		MemberVO memVO = new MemberVO();
//		memVO.setMem_Pw("123456");
//		memVO.setMem_Name("海藍察");
//		memVO.setMem_Gender("ms1");
//		memVO.setMem_Bir("1990/12/20");
//		memVO.setMem_Mail("kaohi@gmail.com");
//		memVO.setMem_Phone("0933111333");
//		memVO.setMem_Receiver("明玉");
//		memVO.setMem_Repno("332");
//		memVO.setMem_Recounty("台南市");
//		memVO.setMem_Retown("永康區");
//		memVO.setMem_Readdr("西門一路40號");
//		memVO.setMem_Cardnum("1111-2222-3333-4444");
//		memVO.setMem_Carddue("05/22");
//		memVO.setMem_Bonus(600);
//		memVO.setMem_Id("chiapao");
//		
//		MemberDAO dao = new MemberDAO();
//		dao.update(memVO);

		
//更改會員狀態
//		MemberVO memVO = new MemberVO();
//		memVO.setMem_Id("chiapao");
//		memVO.setMem_Status("m1");
//		
//		MemberDAO dao = new MemberDAO();
//		dao.changeStatus(memVO);
		
//查詢會員
//		MemberDAO dao = new MemberDAO();
//		List<MemberVO> memberlist = dao.getAll();
//		for(MemberVO memVO:memberlist) {
//			System.out.println("會員資料");
//			System.out.println("編號："+memVO.getMem_No());
//			System.out.println("姓名："+memVO.getMem_Name());
//			System.out.println("電話："+memVO.getMem_Phone());
//			System.out.println("信箱："+memVO.getMem_Mail());
//			System.out.println("=============================");		
//		}
	}
	
}
