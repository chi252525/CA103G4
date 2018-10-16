package com.desk.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.websocket.Session;

import com.desk.model.DeskService;
import com.desk.model.DeskVO;
import com.reservation.model.*;

public class DeskServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action");
		
		
		if("addDesk".equals(action)) {  //座位推播
			
				
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
		   try {
			   
				String date = req.getParameter("date");
				if(date == null || date.length() == 0) {
					errorMsgs.add("請選擇日期");
				}  
				
				String bgtime = req.getParameter("bgtime");
				if(bgtime == null || bgtime.length() == 0) {
					errorMsgs.add("請選擇開始時間");
				}  
				
				String fntime = req.getParameter("fntime");
				if(date == null || date.length() == 0) {
					errorMsgs.add("結束時間尚未填寫，請先選擇開始時間");
				}  
				//-----------------------------------------------------
				Timestamp res_timebg = java.sql.Timestamp.valueOf(date + " " + bgtime + ":00");
				Timestamp res_timefn = java.sql.Timestamp.valueOf(date + " " + fntime + ":00");
				Integer res_people = Integer.valueOf(req.getParameter("res_people"));
				if(res_people == null || res_people == 0) {
					errorMsgs.add("人數尚未填寫");
				} 
				
				String branch_no = req.getParameter("branch_no");
				if(branch_no == null || branch_no.length() == 0) {
					errorMsgs.add("分店不可為空白請先選擇縣市欄位");
				}  
				
				String mem_no = req.getParameter("mem_no");
				
				System.out.println("date:"+ date);
				System.out.println("bgtime:"+ bgtime);
				System.out.println("fntime:"+ fntime);
				System.out.println("res_timebg:"+ res_timebg);
				System.out.println("res_timefn:"+ res_timefn);
				System.out.println("res_people:"+ res_people);
				System.out.println("branch_no:"+ branch_no);
				System.out.println("mem_no="+mem_no);
				
				ResVO resVO = new ResVO();
				resVO.setMem_no(mem_no);
				resVO.setRes_timebg(res_timebg);
				resVO.setRes_timefn(res_timefn);
				resVO.setRes_people(res_people);
				
				DeskVO deskVO = new DeskVO();
				deskVO.setBranch_no(branch_no);
				
				HttpSession session = req.getSession();
				session.setAttribute("resVO", resVO);
				session.setAttribute("deskVO", deskVO);
				session.setAttribute("date", date);
				session.setAttribute("bgtime", bgtime);
				session.setAttribute("fntime", fntime);
				
				RequestDispatcher selectSeats = req.getRequestDispatcher("/front_end/reservation/seat.jsp");
				selectSeats.forward(req, res);
				return;
				
			}catch(Exception e) {
//				errorMsgs.add("資料新增失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/reservation/reservation.jsp");
				failuerView.forward(req, res);
			}
			
		}
		
		if("Seats".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			String seat = req.getParameter("finalSeat");
			System.out.println("controler get:" + seat);
			HttpSession session = req.getSession();
			DeskVO deskVO = (DeskVO)session.getAttribute("deskVO");
			deskVO.setDek_id(seat);
			deskVO.setDek_set(0);
			deskVO.setDek_status(2);
			ResVO resVO = (ResVO)session.getAttribute("resVO");
			DeskService dskS = new DeskService();
			dskS.desk_res(deskVO, resVO);
			System.out.println("done!!");
			
			RequestDispatcher selectSeats = req.getRequestDispatcher("/front_end/reservation/last.jsp");
			selectSeats.forward(req, res);
			return;
			}catch(Exception e) {
//				errorMsgs.add("資料新增失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/reservation/reservation.jsp");
				failuerView.forward(req, res);
		  }
		}
		
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("res_no");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("請輸入訂位流水號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String res_no = null;
				try {
					res_no = new String(str);
				}catch(Exception e) {
					errorMsgs.add("訂位流水號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.getOneRes(res_no);
				if(resVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("resVO", resVO);
				String url ="/res/listOneRes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/res/research_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			String res_no = new String(req.getParameter("res_no"));
			
			ResService resSvc = new ResService();
			ResVO resVO = resSvc.getOneRes(res_no);
			
			req.setAttribute("resVO", resVO);
			String url = "/111/000.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:"+ e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/000/012.JSP");
			failureView.forward(req, res);
			
		}
	  }
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String res_no = new String(req.getParameter("res_no"));
				
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.getOneRes(res_no);
				
				req.setAttribute("resVO", resVO);
				String url = "/150/222.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改得資料"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/456/156.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String res_no = req.getParameter("res_no");
				String res_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(res_no == null || res_no.trim().length() == 0) {
					errorMsgs.add("定位流水編號: 請勿空白");
				}else if(!res_no.trim().matches(res_noReg)) {
					errorMsgs.add("定位流水編號: 只能是英文大寫'R' + 9位數字, 且長度為10");
				}
				
				String mem_no = req.getParameter("mem_no");
				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}else if(!mem_no.trim().matches(mem_noReg)) {
					errorMsgs.add("會員編號:只能是英文大寫'M' + 6位數字, 且長度為7");
				}
				
				String dek_no = req.getParameter("dek_no");
				String dek_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(dek_no == null || dek_no.trim().length() == 0) {
					errorMsgs.add("桌位流水號: 請勿空白");
				}else if(!dek_no.trim().matches(dek_noReg)) {
					errorMsgs.add("桌位流水號: 只能是英文大寫'D' + 9位數字, 且長度為10");
				}
					
				java.sql.Timestamp res_timebg = null;
				try {
					res_timebg = java.sql.Timestamp.valueOf(req.getParameter("res_timebg").trim());
				}catch(IllegalArgumentException e) {
					//預設顯示
					res_timebg = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入:訂位日期時段起始時間");
				}
				
				java.sql.Timestamp res_timefn = null;
				try {
					res_timefn = java.sql.Timestamp.valueOf(req.getParameter("res_timefn").trim());
				}catch(IllegalArgumentException e) {
					//預設顯示
					res_timefn = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入:訂位日期時段結束時間");
				}
				
				Integer res_people = null;
				try {
					res_people = new Integer(req.getParameter("res_people").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("訂位人數:請填數字且不得為空白");
				}
				
				Integer res_status = null; 
				try {
					res_status = new Integer(req.getParameter("res_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("訂位狀態:請填數字且不得為空白");
				}
				
				ResVO resVO = new ResVO();
				
				resVO.setRes_no(res_no);
				resVO.setMem_no(mem_no);
				resVO.setDek_no(dek_no);
				resVO.setRes_timebg(res_timebg);
				resVO.setRes_timefn(res_timefn);
				resVO.setRes_people(res_people);
				resVO.setRes_status(res_status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resVO", resVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/00/200.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				ResService resSvc = new ResService();
				resVO = resSvc.updateRes(mem_no, dek_no, res_timebg,
						res_timefn, res_people, res_status, res_no);
				
				req.setAttribute("resVO", resVO);
				String url = "/555/684.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/987/159.JSP");
				failureView.forward(req, res);
			}
		}
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String mem_no = req.getParameter("mem_no");
				String mem_noReg = "^[M]\\d{6}$";
				if(mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}else if(!mem_no.trim().matches(mem_noReg)) {
					errorMsgs.add("會員編號:只能是英文大寫'M' + 6位數字, 且長度為7");
				}
				
				String dek_no = req.getParameter("dek_no");
				String dek_noReg = "^[D]\\d{9}$";
				if(dek_no == null || dek_no.trim().length() == 0) {
					errorMsgs.add("桌位流水號: 請勿空白");
				}else if(!dek_no.trim().matches(dek_noReg)) {
					errorMsgs.add("桌位流水號: 只能是英文大寫'D' + 9位數字, 且長度為10");
				}

				java.sql.Timestamp res_timebg = null;
				try {
					res_timebg = java.sql.Timestamp.valueOf(req.getParameter("res_timebg").trim());
				}catch(IllegalArgumentException e) {
					//預設顯示
					res_timebg = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入:訂位日期時段起始時間");
				}
				
				java.sql.Timestamp res_timefn = null;
				try {
					res_timefn = java.sql.Timestamp.valueOf(req.getParameter("res_timefn").trim());
				}catch(IllegalArgumentException e) {
					//預設顯示
					res_timefn = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入:訂位日期時段結束時間");
				}

				Integer res_people = null;
				try {
					res_people = new Integer(req.getParameter("res_people").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("訂位人數:請填數字且不得為空白");
				}
				
				Integer res_status = null; 
				try {
					res_status = new Integer(req.getParameter("res_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("訂位狀態:請填數字且不得為空白");
				}
				
				
				ResVO resVO = new ResVO();
			
				resVO.setMem_no(mem_no);
				resVO.setDek_no(dek_no);
				resVO.setRes_timebg(res_timebg);
				resVO.setRes_timefn(res_timefn);
				resVO.setRes_people(res_people);
				resVO.setRes_status(res_status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resVO", resVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/res/addRes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ResService resSvc = new ResService();
				resVO = resSvc.addRes(mem_no, dek_no, res_timebg,
						res_timefn, res_people, res_status);
				
				String url = "/res/listAllRes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/res/addRes.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}

		