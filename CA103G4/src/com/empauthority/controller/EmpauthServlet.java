package com.empauthority.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empauthority.model.*;
import com.employee.model.*;


public class EmpauthServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("updateAuthor".equals(action)) {

			try {
				// Retrieve form parameters.
				String emp_No = req.getParameter("emp_No");
				
				EmpVO empVOauth = new EmpVO();
				EmpService empsvc = new EmpService();
				empVOauth = empsvc.findOnebyEmpNo(emp_No);
				EmpauthorityService empauthsvc = new EmpauthorityService();				
				List<EmpauthorityVO> empauthList = new ArrayList();
				empauthList = empauthsvc.oneEmpFealist(emp_No);
				
				req.setAttribute("empVOauth", empVOauth); // 依照前頁來的emp_No取到該會員的值
				req.setAttribute("empauthList", empauthList);
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				

				// 送出的empVOauth、empauthList 送給OneEmpAuth.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/empAuth.jsp");
//				RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/OneEmpAuth.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
				
	}
}
