package com.member.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.*;

@MultipartConfig(fileSizeThreshold=1024*1024)
public class MemServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
				
		if("insert".equals(action)){  // 來自register.jsp的請求  
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				//帳號驗證
				String mem_Id = req.getParameter("mem_Id").trim();
				String mem_IdReg = "^[(a-zA-Z0-9_)]{2,50}$";
				if(mem_Id == null || mem_Id.length() == 0) {
					errorMsgs.add("尚未填寫帳號");
				}  else if(!mem_Id.trim().matches(mem_IdReg)) {
					errorMsgs.add("帳號須為英文或數字或底線");
				}
				
				//密碼驗證
				String mem_Pw = req.getParameter("mem_Pw").trim();
				if(mem_Pw == null || mem_Pw.length() == 0) {
					errorMsgs.add("尚未填寫密碼");
				} 
				//姓名驗證
				String mem_Name = req.getParameter("mem_Name");
				String mem_NameReg = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
				if (mem_Name == null || (mem_Name.trim()).length() == 0) {
					errorMsgs.add("尚未填寫姓名");
				}else if(!mem_Name.trim().matches(mem_NameReg)) {
					errorMsgs.add("姓名僅能填寫中文與英文");
				}
				
				//性別選擇
				String mem_Gender = req.getParameter("mem_Gender");
				if(mem_Gender == null) {
					errorMsgs.add("尚未選擇性別");
				}
				
				//生日驗證
				String mem_Bir = req.getParameter("mem_Bir");
				if(mem_Bir == null) {
					errorMsgs.add("尚未填寫生日");
				}
				
				//信箱驗證
				String mem_Mail = req.getParameter("mem_Mail");
				if(mem_Mail == null || mem_Mail.trim().isEmpty()) {
					errorMsgs.add("尚未填寫信箱");
				}
				
				//手機驗證
				String mem_Phone = req.getParameter("mem_Phone");
				String mem_PhoneReg = "^[(0-9)]+$";
				if(mem_Phone == null || mem_Phone.trim().isEmpty()) {
					errorMsgs.add("尚未填寫電話");			
				}else if (!(mem_Phone.trim().matches(mem_PhoneReg))) {
					errorMsgs.add("電話僅能輸入數字");
				}
				//預設收件人驗證
				String mem_Receiver = req.getParameter("mem_Receiver");
				String mem_ReceiverReg = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
				if(mem_Receiver.isEmpty()) {				
				}
				else if(!(mem_Receiver.trim().matches(mem_ReceiverReg))) {
					errorMsgs.add("收件人僅能填寫中文與英文");
				}
				
				//預設收件人電話驗證
				String mem_Repno = req.getParameter("mem_Repno");
				String mem_RepnoReg = "^[0-9]+$";
				if(mem_Repno.isEmpty()) {
					
				}else if(!(mem_Repno.trim().matches(mem_RepnoReg))) {
					errorMsgs.add("電話僅能輸入數字");
				}
				
				//預設收件人地址縣市
				String mem_Recounty = req.getParameter("mem_Recounty");
				
				//預設收件人鄉鎮區
				String mem_Retown = req.getParameter("mem_Retown");
				
				//預設收件人地址
				String mem_Readdr = req.getParameter("mem_Readdr");
				
				//預設收件人信用卡
				String mem_Cardnum = req.getParameter("mem_Cardnum");
				
				//預設信用卡截止日
				String mem_Carddue = req.getParameter("mem_Carddue");
				
				//照片處理
				Part part = req.getPart("mem_Photo");
				InputStream in = part.getInputStream();
				byte[] mem_Photo = new byte[in.available()];
				in.read(mem_Photo);
				in.close();
				
				
				
				MemberVO memVO = new MemberVO();
				memVO.setMem_Id(mem_Id);
				System.out.println("memID="+memVO.getMem_Id());
				memVO.setMem_Name(mem_Name);
				System.out.println("memName="+memVO.getMem_Name());
				memVO.setMem_Pw(mem_Pw);
				System.out.println("memPw="+memVO.getMem_Pw());
				memVO.setMem_Bir(mem_Bir);
				System.out.println("memBir="+memVO.getMem_Bir());
				memVO.setMem_Gender(mem_Gender);
				System.out.println("memGender="+memVO.getMem_Gender());
				memVO.setMem_Mail(mem_Mail);
				System.out.println("memMail="+memVO.getMem_Mail());
				memVO.setMem_Phone(mem_Phone);
				System.out.println("memPhone="+memVO.getMem_Phone());
				memVO.setMem_Receiver(mem_Receiver);
				System.out.println("memReceiver="+memVO.getMem_Receiver());
				memVO.setMem_Repno(mem_Repno);
				System.out.println("memRepno="+memVO.getMem_Repno());
				memVO.setMem_Recounty(mem_Recounty);
				System.out.println("memRecounty="+memVO.getMem_Recounty());
				memVO.setMem_Retown(mem_Retown);
				System.out.println("memRetown="+memVO.getMem_Retown());
				memVO.setMem_Readdr(mem_Readdr);
				System.out.println("memRaddr="+memVO.getMem_Readdr());
				memVO.setMem_Cardnum(mem_Cardnum);
				System.out.println("memCardnum="+memVO.getMem_Cardnum());
				memVO.setMem_Carddue(mem_Carddue);
				System.out.println("memCarddue="+memVO.getMem_Carddue());
				memVO.setMem_Photo(mem_Photo);
				System.out.println("memPhoto="+memVO.getMem_Photo());
								
							
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);  // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/register.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料****************************************/
				MemberService memSvc = new MemberService();
				memVO = memSvc.addMem(mem_Id, mem_Pw, mem_Name, mem_Gender, mem_Bir, mem_Mail, mem_Phone, mem_Receiver, mem_Repno, mem_Recounty, mem_Retown, mem_Readdr, mem_Cardnum, mem_Carddue, mem_Photo);
								
				/***************************3.新增完成,準備轉交(Send the Success view)************/
//				req.setAttribute("memVO", menuVO);  // 資料庫新增成功後,正確的的perntdVO物件,存入req
//				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/listAllMenu.jsp");
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("資料新增失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/register.jsp");
				failuerView.forward(req, res);
			}
		}
		
	}
	
	
	

}
