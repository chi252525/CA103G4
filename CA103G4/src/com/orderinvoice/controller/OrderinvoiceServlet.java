package com.orderinvoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.branch.model.BranchService;
import com.custommeals.model.CustommealsService;
import com.custommeals.model.CustommealsVO;
import com.menu.model.MenuVO;
import com.orderform.model.OrderformService;
import com.orderform.model.OrderformVO;
import com.orderinvoice.model.OrderinvoiceService;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderinvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("updateForOut".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			if ( req.getParameterValues("out") == null) {
				errorMsgs.add("請選擇完成的餐點");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/forout/forOut.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			
			OrderinvoiceService orSvc = new OrderinvoiceService();
			
			String[] eatS = req.getParameterValues("out");	
			
			String ordno = req.getParameter("odNo");
			
			for (int i = 0; i < eatS.length; i++) {
				
				if (("M").equals(String.valueOf(eatS[i].charAt(0)))) {
					
					orSvc.forUpdate(ordno, eatS[i], null);
				} else {
					orSvc.forUpdate(ordno, null, eatS[i]);
				}
				
			}
			
			if (orSvc.getByOrder_no(ordno) == 0) {
				OrderformService ordSvc = new OrderformService();
				ordSvc.updateForSta(ordno,2);
				req.getSession().setAttribute("OK",ordno);
			}
			
			String url = req.getContextPath() + "/back_end/forout/forOut.jsp";
		 	res.sendRedirect(url);	
	
		}
		
		
		
	}

}