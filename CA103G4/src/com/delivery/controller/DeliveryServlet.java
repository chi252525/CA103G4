package com.delivery.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.model.DeliveryService;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("deliv_no");
				
				//if (str == null) {
				
				if ((str.trim()).length() == 0) {
					errorMsgs.add("請輸入派送單編號，並檢查格式是否正確。");	
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/delivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String strReg = "^[(A-Z0-9-)]{11,11}$";
				
					if (str != strReg) {
						errorMsgs.add("請檢查格式是否正確。");	
					}
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/delivery/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				//開始查詢
				
				
			}
		}
		
		
	}

}
