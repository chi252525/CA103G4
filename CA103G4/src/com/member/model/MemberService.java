package com.member.model;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMem(String mem_Id, String mem_Pw, String mem_Name, String mem_Gender, String mem_Bir,
			String mem_Mail, String mem_Phone, String mem_Receiver, String mem_Repno, String mem_Recounty,
			String mem_Retown, String mem_Readdr, String mem_Cardnum, String mem_Carddue,
			 byte[] mem_Photo) {
		
		MemberVO memVO = new MemberVO();
		memVO.setMem_Id(mem_Id);
		memVO.setMem_Pw(mem_Pw);
		memVO.setMem_Name(mem_Name);
		memVO.setMem_Gender(mem_Gender);
		memVO.setMem_Bir(mem_Bir);
		memVO.setMem_Mail(mem_Mail);
		memVO.setMem_Phone(mem_Phone);
		memVO.setMem_Receiver(mem_Receiver);
		memVO.setMem_Repno(mem_Repno);
		memVO.setMem_Recounty(mem_Recounty);
		memVO.setMem_Retown(mem_Retown);
		memVO.setMem_Readdr(mem_Readdr);
		memVO.setMem_Cardnum(mem_Cardnum);
		memVO.setMem_Carddue(mem_Carddue);
		memVO.setMem_Photo(mem_Photo);
		
		dao.insert(memVO);	
			
		return memVO;
	}
	
	public  MemberVO updateMem(String mem_Id, String mem_Pw, String mem_Name, String mem_Gender, String mem_Bir,
			String mem_Mail, String mem_Phone, String mem_Receiver, String mem_Repno, String mem_Recounty,
			String mem_Retown, String mem_Readdr, String mem_Cardnum, String mem_Carddue, Integer mem_Bonus,
			 byte[] mem_Photo) {
		
		MemberVO memVO = new MemberVO();
		
		memVO.setMem_Id(mem_Id);
		memVO.setMem_Pw(mem_Pw);
		memVO.setMem_Name(mem_Name);
		memVO.setMem_Gender(mem_Gender);
		memVO.setMem_Bir(mem_Bir);
		memVO.setMem_Mail(mem_Mail);
		memVO.setMem_Phone(mem_Phone);
		memVO.setMem_Receiver(mem_Receiver);
		memVO.setMem_Repno(mem_Repno);
		memVO.setMem_Recounty(mem_Recounty);
		memVO.setMem_Retown(mem_Retown);
		memVO.setMem_Retown(mem_Readdr);
		memVO.setMem_Cardnum(mem_Cardnum);
		memVO.setMem_Carddue(mem_Carddue);
		memVO.setMem_Bonus(mem_Bonus);
		
		dao.update(memVO);	
			
		return memVO;
	}
	
	public MemberVO memVO(String mem_Id,String mem_Status) {
		
		MemberVO memVO = new MemberVO();
		
		memVO.setMem_Id(mem_Id);
		memVO.setMem_Status(mem_Status);
	
		dao.changeStatus(memVO);
		
		return memVO;
	}
	
	public MemberVO memVO(String mem_No, String mem_Name, String mem_Phone, String mem_Mail) {
		
		MemberVO memVO = new MemberVO();
		memVO.setMem_No(mem_No);
		memVO.setMem_Name(mem_Name);
		
		
		return memVO;
	}
}
