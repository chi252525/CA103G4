package com.perntd.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PerntdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		if("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//無輸入
				String perntd_No  = req.getParameter("perntd_No");
				if(perntd_No  == null || (perntd_No .trim()).length() == 0) {
					errorMsgs.add("請輸入個人通知流水號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/perntd/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//格式錯誤
				try {
					perntd_No.matches("^P[0-9]$");
					
				} catch(Exception e) {
					errorMsgs.add("個人通知流水號格式不正確");
				}
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
	}

}
