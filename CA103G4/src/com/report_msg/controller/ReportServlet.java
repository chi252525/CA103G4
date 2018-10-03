package com.report_msg.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.branch.model.BranchVO;
import com.post.model.PostService;
import com.post.model.PostVO;
import com.report_msg.model.ReportService;
import com.report_msg.model.ReportVO;
public class ReportServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		System.out.println("report doPost action"+action);
//		System.out.println(action);
		if ("insert".equals(action)) {  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_No =req.getParameter("mem_No");
				System.out.println("mem_No"+mem_No);
				String post_No=req.getParameter("post_No");
				System.out.println("post_No"+post_No);
				String rpt_Rsm=req.getParameter("rpt_Rsm");
				System.out.println("rpt_Rsm"+rpt_Rsm);
				if(rpt_Rsm==null||"".equals(rpt_Rsm)) {
					errorMsgs.add("請給個理由!!");
				}
			ReportVO rptVO= new ReportVO();
			rptVO.setMem_No(mem_No);
			rptVO.setPost_No(post_No);
			rptVO.setRpt_Rsm(rpt_Rsm);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rptVO", rptVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listOnepost.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}	
			/***************************2.開始新增資料***************************************/
			ReportService rptSvc= new ReportService();
			rptVO=rptSvc.addReport( post_No, mem_No , rpt_Rsm);
			req.setAttribute("rptVO", rptVO);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp"); 
			successView.forward(req, res);	
			}/***************************其他可能的錯誤處理**********************************/
		 catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req.getRequestDispatcher("/front_end/reply/listOnepost.jsp");
		failureView.forward(req, res);
	}
		
		}
		
		if ("updateReportStatus".equals(action)) { 
			System.out.println("跳進Report 的updateReportStatus 處理狀態");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL"+requestURL);
			String whichPage = req.getParameter("whichPage");
			System.out.println("whichPage"+whichPage);
			try {
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String rpt_No =req.getParameter("rpt_No");
			System.out.println("rpt_No"+rpt_No);
//			String rpt_Status= "RS1";
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			ReportService rptSvc= new ReportService();
			rptSvc.updateReportStatus(rpt_No);
			
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			if(requestURL.equals("/back_end/report/Postreport.jsp")||requestURL.equals("/back_end/report/Postreport.jsp?"+whichPage)){
				req.setAttribute("rpt_No",rpt_No);
			}
			
			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
		
			successView.forward(req, res);
			System.out.println("success");
			/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
			}
		}  
		
		
		if ("getReportByStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			 System.out.println("跳進getReportByStatus");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String rpt_Status = req.getParameter("rpt_Status");
				System.out.println("rpt_Status"+rpt_Status);
				if (rpt_Status == null ) {
					errorMsgs.add("請選正確狀態");
				}
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ReportService rptSvc = new ReportService();
				List<ReportVO> getPostByStatuslist = rptSvc.getReplybyStatus(rpt_Status);
				if (getPostByStatuslist == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
					System.out.println("查詢失敗");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
//				HttpSession session = req.getSession();
				req.setAttribute("getPostByStatuslist", getPostByStatuslist);
				System.out.println("req.setAttribute" + getPostByStatuslist);

				RequestDispatcher successView = req.getRequestDispatcher("/back_end/report/ReportQuery.jsp");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
			}

		}
		
		
		if ("getReportByStatus2".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			 System.out.println("跳進getReportByStatus");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String rpt_Status = req.getParameter("rpt_Status");
				System.out.println("rpt_Status"+rpt_Status);
				if (rpt_Status == null ) {
					errorMsgs.add("請選正確狀態");
				}
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ReportService rptSvc = new ReportService();
				List<ReportVO> getPostByStatuslist = rptSvc.getReplybyStatus(rpt_Status);
				if (getPostByStatuslist == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/ReportQuery.jsp");
					System.out.println("查詢失敗");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("getPostByStatuslist", getPostByStatuslist);
				// =============print to web page=====================
				JSONArray jsonarr = new JSONArray();

				for (ReportVO rptVO : getPostByStatuslist) {
					JSONObject jso = new JSONObject();
					jso.put("rpt_No", rptVO.getRpt_No());
					jso.put("post_No", rptVO.getPost_No());
					jso.put("mem_No", rptVO.getMem_No());
					jso.put("rpt_Rsm", rptVO.getRpt_Rsm());
					jso.put("rpt_Status", rptVO.getRpt_Status());
					jso.put("rpt_Time", rptVO.getRpt_Time());
					jsonarr.put(jso);
				}


				
				PrintWriter out = res.getWriter();
				out.print(jsonarr);
				System.out.println(jsonarr);
				out.flush();
				out.close();
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
			}

		}
		
		
	}
}
