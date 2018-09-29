package com.report_msg.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report_msg.model.ReportService;
import com.report_msg.model.ReportVO;
public class ReportServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		System.out.println(action);
		if ("insert".equals(action)) {  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_No =req.getParameter("mem_No");
				String post_No=req.getParameter("post_No");
				String rpt_Rsm=req.getParameter("rpt_Rsm");
				if(rpt_Rsm==null||"".equals(rpt_Rsm)) {
					errorMsgs.add("請給個理由!!");
				}
			ReportVO rptVO= new ReportVO();
			rptVO.setMem_No(mem_No);
			rptVO.setPost_No(post_No);
			rptVO.setRpt_Rsm(rpt_Rsm);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rptVO", rptVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listOnePost.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}	
			/***************************2.開始新增資料***************************************/
			ReportService rptSvc= new ReportService();
			rptVO=rptSvc.addReport( post_No, mem_No , rpt_Rsm);
			req.setAttribute("rptVO", rptVO);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/post/listOnePost.jsp"); 
			successView.forward(req, res);	
			}/***************************其他可能的錯誤處理**********************************/
		 catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req.getRequestDispatcher("/front_end/reply/listOnepost.jsp");
		failureView.forward(req, res);
	}
		
		}
		
		if ("updateStatus".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String rpt_No =req.getParameter("rpt_No");
			String rpt_Status= "RS1";
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			ReportService rptSvc= new ReportService();
			rptSvc.updateStatus(rpt_Status, rpt_No);
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/report/Postreport.jsp"); 
			successView.forward(req, res);
			/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa_report/qa_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
}
