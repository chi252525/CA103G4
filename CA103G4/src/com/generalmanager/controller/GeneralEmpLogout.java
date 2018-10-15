package com.generalmanager.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GeneralEmpLogout extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("mgerVO");
			System.out.println("登出囉");
			res.sendRedirect(req.getContextPath()+"/back_end/employee/empLogin_HQ.jsp");
		}catch(Exception e) {
			
		}
		
	}
}
