package com.report_msg.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
				String rply_No=req.getParameter("rply_No");
				String rpt_Rsm=req.getParameter("rpt_Rsm");
				String rpt_Status=req.getParameter("rpt_Status");
				
				
				
			
			}/***************************其他可能的錯誤處理**********************************/
		 catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req.getRequestDispatcher("/front_end/reply/listOnepost.jsp");
		failureView.forward(req, res);
	}
		
		}
	}
}
