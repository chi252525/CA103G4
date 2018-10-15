package com.generalmanager.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.generalmanager.model.GeneralmanagerService;
import com.generalmanager.model.GeneralmanagerVO;
@MultipartConfig(fileSizeThreshold=1024*1024)
public class GeneralmanagerServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if("loginHQ".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String mger_Acnum = req.getParameter("mger_Acnum");
				System.out.println(mger_Acnum);
				String mger_Psw = req.getParameter("mger_Psw");
				System.out.println(mger_Psw);
				
				GeneralmanagerService mgerSvc = new GeneralmanagerService();
				GeneralmanagerVO mgerVO = new GeneralmanagerVO();
				mgerVO = mgerSvc.getOneByAcnum(mger_Acnum);
				
				if(mger_Acnum.trim().isEmpty() || mger_Psw.trim().isEmpty()) {
					errorMsgs.add("尚未輸入帳號或密碼");
				}else if(mgerVO != null){
					System.out.println(mgerVO.getMger_Psw());
					if(!mgerVO.getMger_Psw().equals(mger_Psw)) {
						errorMsgs.add("密碼錯誤");
					}
				}else {
					errorMsgs.add("無此帳號");
				}
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("mgerVO", mgerVO);  // 含有輸入格式錯誤的mgerVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/employee/empLogin_HQ.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
					
				HttpSession session = req.getSession();
				session.setAttribute("mgerVO", mgerVO);
				
				RequestDispatcher sussesful = req.getRequestDispatcher("/back_end/HQback_index.jsp");
				sussesful.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("登入失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/back_end/employee/empLogin_HQ.jsp");
				failuerView.forward(req, res);
			}
			
		
		}
		
	}

}
