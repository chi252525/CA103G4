package com.member.controller;

import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("memVO");
			session.removeAttribute("mem_Id");
			session.removeAttribute("memNo");
			System.out.println("登出囉");
			res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
		}catch(Exception e) {
			
		}
		
	}

}
