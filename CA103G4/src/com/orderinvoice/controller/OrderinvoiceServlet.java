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
import com.orderform.model.OrderformVO;
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
			
			List<OrderinvoiceVO> list = new ArrayList<>();
			
			String[] eatS = req.getParameterValues("out");
			OrderinvoiceVO orVO = null;
			
			String ordno = req.getParameter("odNo");
			
			for (int i = 0; i < eatS.length; i++) {
				    orVO = new OrderinvoiceVO();
				
				if (("M").equals(String.valueOf(eatS[i].charAt(0)))) {
					orVO.setMenu_no(eatS[i]);
					orVO.setOrder_no(ordno);
					orVO.setCustom_no(null);
					orVO.forUpdate();
				} else {
					orVO.setCustom_no(eatS[i]);
					orVO.setOrder_no(ordno);
					orVO.setMenu_no(null);
				}
				
				list.add(orVO);
			}
			
			
			
			
			
		}
		
	}

}