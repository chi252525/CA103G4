package com.employee.controller;

import javax.servlet.http.*;

public class LogoutEmp extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("empVO");
			System.out.println("登出囉");
			res.sendRedirect(req.getContextPath()+"/back_end/back_index.jsp");
		}catch(Exception e) {
			
		}
		
	}
}
