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
//				RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/empAuth.jsp");
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/OneEmpAuth.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if("changeAuth".equals(action)) {
		try {	
			String emp_No = req.getParameter("emp_No");
			System.out.println("員工編號："+emp_No);
			List<EmpauthorityVO> empauthorlist = new ArrayList();
			String valuse[] =req.getParameterValues("fea_No");
			if(valuse!=null) {
				for(int i=0 ; i< valuse.length ; i++ ) {
				System.out.println("選取功能"+valuse[i]);	
				EmpauthorityVO empauthVO = new EmpauthorityVO();
				empauthVO.setEmp_No(emp_No);
				empauthVO.setFea_No(valuse[i]);
				empauthorlist.add(empauthVO);
				}			
			}
			
			EmpauthorityService empauthsvc = new EmpauthorityService();
			//呼叫刪除需兩個參數1.String emp_No 2.empauthorlist;
			empauthsvc.DeletrbyEmpNo(emp_No, empauthorlist);
			
			
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/empAuth.jsp");
			successView.forward(req, res);
			return;

		} catch (Exception e) {
			throw new ServletException(e);
		}	
		}
	}
}
