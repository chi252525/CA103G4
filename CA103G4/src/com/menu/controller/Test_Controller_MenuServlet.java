package com.menu.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.menu.model.MenuDAO;
import com.menu.model.MenuVO;

public class Test_Controller_MenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		
		if ("getOneMenu".equals(action)) {

			try {
				// Retrieve form parameters.
				String menu_No = new String(req.getParameter("menu_No"));

				MenuDAO dao = new MenuDAO();
				MenuVO menuVO = dao.findByPrimaryKey(menu_No);

				req.setAttribute("menuVO", menuVO); // 資料庫取出的menuVO物件,存入req
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal);
				
				// 取出的menuVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/menu/listAllMenu3.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
	}
}