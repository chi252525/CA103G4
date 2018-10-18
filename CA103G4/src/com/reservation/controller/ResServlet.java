package com.reservation.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.websocket.Session;

import org.json.JSONArray;

import com.desk.model.DeskService;
import com.desk.model.DeskVO;
import com.google.gson.JsonObject;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.reservation.model.ResService;
import com.reservation.model.ResVO;

public class ResServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String date = req.getParameter("date");
		String zone = req.getParameter("zone");
		
		if("pushSeat".equals(action)) {  //座位推播
			System.out.println("controller" + date);
			System.out.println("controller" + zone);
//			Enumeration<String> ss = getServletContext().getAttributeNames();
//			while(ss.hasMoreElements()) {
//				System.out.println(ss.nextElement());
//			}
			
//			if(getServletContext().getAttribute("date1")==null) {
//				System.out.println("=======================================================NULL");
//			}else {
//				System.out.println("====================================================NOTNULL");
//			}
			Map<String,Map<String,Set<String>>> date1 = (Map<String,Map<String,Set<String>>>)getServletContext().getAttribute("date1");
			
			String str = "";
			
			try{
				System.out.println("size:" + date1);
				for(String i : date1.keySet()) {
					for(String j : date1.get(i).keySet()) {
						System.out.println("=============j：" + j);
					}
					System.out.println("=============i：" + i);
				}
				Iterator<String> objs = ((date1.get(date)).get(zone)).iterator();
				while (objs.hasNext()) {
//					System.out.println("controller seat" + objs.next());
					str += (String)objs.next();
		            PrintWriter out = res.getWriter();
				    out.write(str);
					System.out.println("pass Controller");
				}
			}catch(Exception e) {
				e.printStackTrace(System.err); 
			}
		}
		
		
		
		if("queryRes".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("enter controller  queryRes");
			try {
				String date2 = req.getParameter("date2");
				System.out.println("queryRes1:" + date2);
				if(date2 == null || (date2.trim()).length() == 0){
					errorMsgs.add("請輸入日期");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/reservation/listAllRes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String zone2 = req.getParameter("zone2");
				System.out.println("queryRes2:" + zone2);
				if(zone2 == null || (zone2.trim()).length() == 0){
					errorMsgs.add("請輸入時段");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/back_end/reservation/listAllRes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String res_timebg = (date2 + " " + zone2 + ":00");
				System.out.println("conbined time " + res_timebg);
				
				ResService resSvc = new ResService();
				List<ResVO> res_timebgList = resSvc.getAllByBGNO(res_timebg);
				if(res_timebgList == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/back_end/reservation/listAllRes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MemberService memSvc = new MemberService();
				DeskService dekSvc = new DeskService();
				System.out.println("pass called service");
				JSONArray jarr = new JSONArray();
				for(int i=0;i<res_timebgList.size();i++) {
					JsonObject jobj = new JsonObject();
					jobj.addProperty("Res_no",res_timebgList.get(i).getRes_no());
					jobj.addProperty("Mem_Name",memSvc.getOne_Member(res_timebgList.get(i).getMem_no()).getMem_Name());
					jobj.addProperty("Mem_Phone",memSvc.getOne_Member(res_timebgList.get(i).getMem_no()).getMem_Phone());
					jobj.addProperty("deskId",dekSvc.getOneDes(res_timebgList.get(i).getDek_no()).getDek_id());
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String Res_submit = df.format(res_timebgList.get(i).getRes_submit());
					SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String Res_timebg = fd.format(res_timebgList.get(i).getRes_timebg());
					String Res_timefn = fd.format(res_timebgList.get(i).getRes_timefn());
					jobj.addProperty("Res_submit", Res_submit);
					jobj.addProperty("Res_timebg", Res_timebg);
					jobj.addProperty("Res_timefn", Res_timefn);
					jobj.addProperty("Res_people",res_timebgList.get(i).getRes_people());
					jobj.addProperty("Res_status",res_timebgList.get(i).getRes_status());
					System.out.println("pass jason addproperties");
					jarr.put(jobj);
				}
				System.out.println("hi" + jarr);
//				List<MemberVO> memList = );
				
				
//				List<DeskVO>  dekList = dekSvc.getOneDes(dek_no);
				
				req.setAttribute("res_timebgList", res_timebgList);
				
				String jsonStr = jarr.toString();
				PrintWriter out = res.getWriter();
			    out.write(jsonStr);
				System.out.println("pass Controller Get List<ResVO>");
				
				
//				req.setAttribute("resVO", resVO);
//				String url ="/back_end/reservation/listAllRes.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/reservation/listAllRes.jsp");
				failureView.forward(req, res);
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

		