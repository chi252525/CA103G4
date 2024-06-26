package com.member.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.mailservice.MailService;
import com.member.model.*;


import redis.clients.jedis.Jedis;

@MultipartConfig(fileSizeThreshold=1024*1024)
public class MemServlet extends HttpServlet{
	Connection con;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
						
		//登入顯示大頭照
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("image/gif");
			try {

				String mem_No = req.getParameter("mem_No").trim();
				System.out.println(mem_No);		
				Statement stmt = con.createStatement();
	
				ResultSet rs = stmt.executeQuery(
					"SELECT MEM_PHOTO FROM MEMBER WHERE MEM_NO='"+mem_No+"'");
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("MEM_PHOTO"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/front_end/img/no-photo.png");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
					
			}catch(Exception e){
				System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/front_end/img/LOGO-04.png");
				byte[] b = new byte[in.available()]; 
				in.read(b);
				out.write(b);
				in.close();
			}
							
		doPost(req,res);
	}
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//註冊區塊-chiapao
		if("insert".equals(action)){  // 來自register.jsp的請求  
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				//註冊開始
				String mem_Id = req.getParameter("mem_Id").trim();
				String mem_IdReg = "^[(a-zA-Z0-9_)]{1,15}$";
				
				MemberService memSvc = new MemberService();
				MemberVO checkId = memSvc.getOneMem_Id(mem_Id);
				
				//帳號是否重複檢查
				if(checkId !=null) {
					errorMsgs.add("帳號重複");
				}
				//帳號驗證
				if(mem_Id == null || mem_Id.length() == 0) {
					errorMsgs.add("尚未填寫帳號");
				}  else if(!mem_Id.trim().matches(mem_IdReg)) {
					errorMsgs.add("帳號須為英文或數字並可接受底線");
				}
				
				//密碼驗證
				String mem_Pw = req.getParameter("mem_Pw").trim();
				String mem_PwReg = "^[(a-zA-Z0-9_)]{1,15}$";
				if(mem_Pw == null || mem_Pw.length() == 0) {
					errorMsgs.add("尚未填寫密碼");
				} else if(!mem_Pw.trim().matches(mem_PwReg)) {
					errorMsgs.add("密碼須為英文或數字並可接受底線");					
				}
				//姓名驗證
				String mem_Name = req.getParameter("mem_Name");
				String mem_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]+$";
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
				Date mem_Bir = java.sql.Date.valueOf(req.getParameter("mem_Bir"));
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
				String mem_PhoneReg = "^\\d{0,10}$";
				if(mem_Phone == null || mem_Phone.trim().isEmpty()) {
					errorMsgs.add("尚未填寫電話");			
				}else if (!(mem_Phone.trim().matches(mem_PhoneReg))) {
					errorMsgs.add("電話僅能輸入數字且10碼內");
				}
				//預設收件人驗證
				String mem_Receiver = req.getParameter("mem_Receiver");
				String mem_ReceiverReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]+$";
				if(mem_Receiver.isEmpty()) {				
				}
				else if(!(mem_Receiver.trim().matches(mem_ReceiverReg))) {
					errorMsgs.add("收件人僅能填寫中文與英文");
				}
				
				//預設收件郵遞區號驗證
				String mem_Repno = req.getParameter("mem_Repno");
				String mem_RepnoReg = "^\\d{5}$";
				if(mem_Repno.isEmpty()) {
					
				}else if(!(mem_Repno.trim().matches(mem_RepnoReg))) {
					errorMsgs.add("僅能輸入數字(eg.33344)");
				}
				
				//預設收件人縣市
				String mem_Recounty = req.getParameter("mem_Recounty");

				
				//預設收件人鄉鎮區
				String mem_Retown = req.getParameter("mem_Retown");
				
				//預設收件人地址
				String mem_Readdr = req.getParameter("mem_Readdr");
				String mem_ReaddrReq = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]+$";
				
				if(mem_Readdr.isEmpty()) {
					
				}else if(!mem_Readdr.trim().matches(mem_ReaddrReq)){
					errorMsgs.add("地址僅接受中文、英文、數字");
				}
				
				//預設收件人信用卡
				String mem_Cardnum = req.getParameter("mem_Cardnum");
				String mem_CardnumReq = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";
				if(mem_Cardnum.isEmpty()) {
					
				}else if(!mem_Cardnum.trim().matches(mem_CardnumReq)) {
					errorMsgs.add("信用卡號須為16碼(eg.1234-1234-1234-1234)");
				}
				
				
				//預設信用卡截止日
				String mem_Carddue = req.getParameter("mem_Carddue");
				String mem_CarddueReq = "^(0[1-9]|1[0-2])/\\d{2}$";
				if(mem_Carddue.isEmpty()) {
					
				}else if(!mem_Carddue.trim().matches(mem_CarddueReq)) {
					errorMsgs.add("信用卡截止日格式為MM/YY");
				}
				
				//照片處理
				Part part = req.getPart("mem_Photo");
				InputStream in = part.getInputStream();
				byte[] mem_Photo = new byte[in.available()];
				in.read(mem_Photo);
				in.close();
				
				
				
				MemberVO memVOreg = new MemberVO();
				memVOreg.setMem_Id(mem_Id);
				//System.out.println("memID="+memVO.getMem_Id());
				memVOreg.setMem_Name(mem_Name);
				//System.out.println("memName="+memVO.getMem_Name());
				memVOreg.setMem_Pw(mem_Pw);
				//System.out.println("memPw="+memVO.getMem_Pw());
				memVOreg.setMem_Bir(mem_Bir);
				//System.out.println("memBir="+memVO.getMem_Bir());
				memVOreg.setMem_Gender(mem_Gender);
				//System.out.println("memGender="+memVO.getMem_Gender());
				memVOreg.setMem_Mail(mem_Mail);
				//System.out.println("memMail="+memVO.getMem_Mail());
				memVOreg.setMem_Phone(mem_Phone);
				//System.out.println("memPhone="+memVO.getMem_Phone());
				memVOreg.setMem_Receiver(mem_Receiver);
				//System.out.println("memReceiver="+memVO.getMem_Receiver());
				memVOreg.setMem_Repno(mem_Repno);
				//System.out.println("memRepno="+memVO.getMem_Repno());
				memVOreg.setMem_Recounty(mem_Recounty);
				//System.out.println("memRecounty="+memVO.getMem_Recounty());
				memVOreg.setMem_Retown(mem_Retown);
				//System.out.println("memRetown="+memVO.getMem_Retown());
				memVOreg.setMem_Readdr(mem_Readdr);
				//System.out.println("memRaddr="+memVO.getMem_Readdr());
				memVOreg.setMem_Cardnum(mem_Cardnum);
				//System.out.println("memCardnum="+memVO.getMem_Cardnum());
				memVOreg.setMem_Carddue(mem_Carddue);
				//System.out.println("memCarddue="+memVO.getMem_Carddue());
				memVOreg.setMem_Photo(mem_Photo);
				//System.out.println("memPhoto="+memVO.getMem_Photo());
								
							
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVOreg", memVOreg);  // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/register.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************2.開始新增資料****************************************/

				memSvc.addMem(mem_Id, mem_Pw, mem_Name, mem_Gender, mem_Bir, mem_Mail, mem_Phone, mem_Receiver, mem_Repno, mem_Recounty, mem_Retown, mem_Readdr, mem_Cardnum, mem_Carddue, mem_Photo);
						
				/***************************3.寄發驗證信****************************************/
				
				memVOreg = memSvc.getOneMem_Id(memVOreg.getMem_Id());
				String status = memVOreg.getMem_Status();
				
				if("m0".equals(status)) {
					MailService ms = new MailService();
					//取得驗證碼
					String authCode = MemberRedis.returnAuthCode();
					System.out.println("authCode="+authCode);
					
					String to = memVOreg.getMem_Mail();
					String subject = "竹風堂認證信";
					String messageText = "HI！ " +memVOreg.getMem_Name()+" 歡迎你加入竹風堂會員以下為驗證碼，請於網頁中輸入並完成註冊。"+ "\n驗證碼："+authCode  ;
					ms.sendMail(to, subject, messageText);		
					
					//連線Jedis
					Jedis jedis = new Jedis("localhost", 6379);
					jedis.auth("123456");

					//存驗證碼以及時間

					jedis.set(memVOreg.getMem_No(), authCode);
					
					jedis.expire(memVOreg.getMem_No(), 300);
							
				/***************************4.信已寄出,準備轉交檢查頁面(Send the check view)************/
					jedis.close();
					
					
					HttpSession session = req.getSession();
					session.setAttribute("mem_No", memVOreg.getMem_No());
					session.setAttribute("mem_Name", memVOreg.getMem_Name());
					session.setAttribute("mem_Id", mem_Id);
					
					RequestDispatcher checkAuth = req.getRequestDispatcher("/front_end/member/checkstatus.jsp");
					checkAuth.forward(req, res);
					return;
				}
	
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("資料新增失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/member/register.jsp");
				failuerView.forward(req, res);
			}
		}
		
		
		//修改區塊-chiapao
		if("update".equals(action)){  
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				//修改開始
				String mem_Id = req.getParameter("mem_Id").trim();
				System.out.println("update");

				
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
				Date mem_Bir = java.sql.Date.valueOf(req.getParameter("mem_Bir"));
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
				
				//預設收件人郵遞區號驗證
				String mem_Repno = req.getParameter("mem_Repno").trim();
				String mem_RepnoReg = "^[0-9]+$";
				if(mem_Repno.isEmpty()) {
					
				}else if(!(mem_Repno.trim().matches(mem_RepnoReg))) {
					errorMsgs.add("郵遞區號僅能輸入數字");
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
				byte[] mem_Photo = null;
				
				try {
					String filename = getFileName(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						mem_Photo = new byte[in.available()];
						in.read(mem_Photo);
						in.close();
					} else {
						MemberService memsvc = new MemberService();
						MemberVO memVO = memsvc.getOneMem_Id(mem_Id);
						mem_Photo = memVO.getMem_Photo();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}

			
								
				MemberVO memVO = new MemberVO();

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
				memVO.setMem_Id(mem_Id);
				
								
							
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);  // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/updateMem.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************2.開始修改資料****************************************/
				MemberService memSvc = new MemberService();
				memSvc.updateMem(mem_Id, mem_Pw, mem_Name, mem_Gender, mem_Bir, mem_Mail, mem_Phone, mem_Receiver, mem_Repno, mem_Recounty, mem_Retown, mem_Readdr, mem_Cardnum, mem_Carddue, mem_Photo);
													
				/***************************3.修改完成(Send the check view)************/
									
				//取得會員帳號的全部資料
				memVO = memSvc.getOneMem_Id(mem_Id);							
				HttpSession session = req.getSession();
					session.setAttribute("memVO", memVO);					
					session.setAttribute("mem_Id", mem_Id);
					
					RequestDispatcher checkAuth = req.getRequestDispatcher("/front_end/member/memberinfo.jsp");
					checkAuth.forward(req, res);
					return;
//				}
	
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("資料修改失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/member/memberinfo.jsp");
				failuerView.forward(req, res);
			}
		}
		
		
		
		//更改驗證狀態區塊
		if("checkstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			//取得網頁輸入的驗證碼
			String authCode = req.getParameter("authCode").trim();


			//取得連線
			Jedis jedis = new Jedis("localhost", 6379);	
			jedis.auth("123456");
						
			HttpSession session = req.getSession();
			
			String mem_No = (String) session.getAttribute("mem_No");
			String mem_Name = (String) session.getAttribute("mem_Name");	
			String mem_Id = (String) session.getAttribute("mem_Id");
			
			//取得jedis的驗證碼

			String jedisAuthCode = jedis.get(mem_No);
System.out.println("jedisAuthCode="+jedisAuthCode);

			

			if(jedisAuthCode == null) {		
				errorMsgs.add("驗證已失效");				
			}else if(authCode.equals(jedisAuthCode)) {
				MemberService ms = new MemberService();
				ms.memChangeStatus(mem_Id, "m1");
System.out.println("我已經改完囉");
				
			}else {
				errorMsgs.add("輸入錯誤");
				}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/checkstatus.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
						
			jedis.close();
			session.invalidate();
			
			RequestDispatcher login = req.getRequestDispatcher("/front_end/member/login.jsp");
			login.forward(req, res);
			
			}catch(Exception e){
				errorMsgs.add("驗證失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/member/checkstatus.jsp");
				failuerView.forward(req, res);			
			}
			
		}
		
		
		//登入區塊-chiapao
		
		if("loginhandler".equals(action)){  // 來自register.jsp的請求  
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				//取得輸入帳號密碼				
				String mem_Id = req.getParameter("mem_Id").trim();
				String mem_Pw = req.getParameter("mem_Pw").trim();
				
				MemberVO memVO;
				MemberService memSvc = new MemberService();
				memVO = memSvc.getOneMem_Id(mem_Id); //找輸入帳號的資料，若無此帳號memVO為空值;
				
				//帳號密碼判斷				
				if(mem_Id.trim().isEmpty() || mem_Pw.trim().isEmpty()) {
					errorMsgs.add("尚未輸入帳號或密碼");
				}else if(memVO != null){
					System.out.println(memVO.getMem_Pw());
					if(!memVO.getMem_Pw().equals(mem_Pw)) {
						errorMsgs.add("密碼錯誤");
					}
				}else {
					errorMsgs.add("無此帳號");
				}
				//檢查狀態	
				String status = memVO.getMem_Status();				
			
//				if("m0".equals(status)) {
//					
//					errorMsgs.add("帳號尚未完成驗證");
//
//				}
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);  // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.帳號密碼皆正確****************************************/
				System.out.println("帳密都沒錯");

				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				session.setAttribute("mem_Id", mem_Id);
				session.setAttribute("memNo", memVO.getMem_No());//拜託不要刪我QQ
				
				
					try {
						String location = (String) session.getAttribute("location");
							if(location != null) {
								session.removeAttribute("location");
								res.sendRedirect(location);            
						        return;//程式中斷
							}
						
					}catch(Exception ignored) {}
				
				
								
				/***************************3.登入完成************/

				res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("登入失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failuerView.forward(req, res);
			}
		
		
		
		}
	}
	
	
	public String getFileName(Part part) {
		
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
//		System.out.println(header);
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename);  //測試用
		//取出副檔名
		String fnameExt = filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
		System.out.println("fnameExt=" + fnameExt);  //測試用
		if (filename.length() == 0) {
			return null;
		}
		return fnameExt;
	}
}
