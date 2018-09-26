package com.reservation.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.reservation.model.*;

public class ResServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("res_no");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("請輸入訂位流水號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/front_end/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String res_no = null;
				try {
					res_no = new String(str);
				}catch(Exception e) {
					errorMsgs.add("訂位流水號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.getOneRes(res_no);
				if(resVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/front_end/res/research_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("resVO", resVO);
				String url ="/front_end/res/listOneRes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/res/research_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			String res_no = new String(req.getParameter("res_no"));
			
			ResService resSvc = new ResService();
			ResVO resVO = resSvc.getOneRes(res_no);
			
			req.setAttribute("resVO", resVO);
			String url = "/111/000.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:"+ e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/000/012.JSP");
			failureView.forward(req, res);
			
		}
	  }
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String res_no = new String(req.getParameter("res_no"));
				
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.getOneRes(res_no);
				
				req.setAttribute("resVO", resVO);
				String url = "/150/222.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("�L�k���o�n�ק�o���"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/456/156.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String res_no = req.getParameter("res_no");
				String res_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(res_no == null || res_no.trim().length() == 0) {
					errorMsgs.add("�w��y���s��: �ФŪť�");
				}else if(!res_no.trim().matches(res_noReg)) {
					errorMsgs.add("�w��y���s��: �u��O�^��j�g'R' + 9��Ʀr, �B���׬�10");
				}
				
				String mem_no = req.getParameter("mem_no");
				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				}else if(!mem_no.trim().matches(mem_noReg)) {
					errorMsgs.add("�|���s��:�u��O�^��j�g'M' + 6��Ʀr, �B���׬�7");
				}
				
				String dek_no = req.getParameter("dek_no");
				String dek_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(dek_no == null || dek_no.trim().length() == 0) {
					errorMsgs.add("���y����: �ФŪť�");
				}else if(!dek_no.trim().matches(dek_noReg)) {
					errorMsgs.add("���y����: �u��O�^��j�g'D' + 9��Ʀr, �B���׬�10");
				}
					
				java.sql.Timestamp res_timebg = null;
				try {
					res_timebg = java.sql.Timestamp.valueOf(req.getParameter("res_timebg").trim());
				}catch(IllegalArgumentException e) {
					//�w�]���
					res_timebg = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J:�q�����ɬq�_�l�ɶ�");
				}
				
				java.sql.Timestamp res_timefn = null;
				try {
					res_timefn = java.sql.Timestamp.valueOf(req.getParameter("res_timefn").trim());
				}catch(IllegalArgumentException e) {
					//�w�]���
					res_timefn = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J:�q�����ɬq�����ɶ�");
				}
				
				Integer res_people = null;
				try {
					res_people = new Integer(req.getParameter("res_people").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�q��H��:�ж�Ʀr�B���o���ť�");
				}
				
				Integer res_status = null; 
				try {
					res_status = new Integer(req.getParameter("res_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�q�쪬�A:�ж�Ʀr�B���o���ť�");
				}
				
				ResVO resVO = new ResVO();
				
				resVO.setRes_no(res_no);
				resVO.setMem_no(mem_no);
				resVO.setDek_no(dek_no);
				resVO.setRes_timebg(res_timebg);
				resVO.setRes_timefn(res_timefn);
				resVO.setRes_people(res_people);
				resVO.setRes_status(res_status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resVO", resVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/00/200.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				ResService resSvc = new ResService();
				resVO = resSvc.updateRes(mem_no, dek_no, res_timebg,
						res_timefn, res_people, res_status, res_no);
				
				req.setAttribute("resVO", resVO);
				String url = "/555/684.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("�ק��ƥ���"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/987/159.JSP");
				failureView.forward(req, res);
			}
		}
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String mem_no = req.getParameter("mem_no");
				String mem_noReg = "^[M]\\d{6}$";
				if(mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				}else if(!mem_no.trim().matches(mem_noReg)) {
					errorMsgs.add("�|���s��:�u��O�^��j�g'M' + 6��Ʀr, �B���׬�7");
				}
				
				String dek_no = req.getParameter("dek_no");
				String dek_noReg = "^[D]\\d{9}$";
				if(dek_no == null || dek_no.trim().length() == 0) {
					errorMsgs.add("���y����: �ФŪť�");
				}else if(!dek_no.trim().matches(dek_noReg)) {
					errorMsgs.add("���y����: �u��O�^��j�g'D' + 9��Ʀr, �B���׬�10");
				}

				java.sql.Timestamp res_timebg = null;
				try {
					res_timebg = java.sql.Timestamp.valueOf(req.getParameter("res_timebg").trim());
				}catch(IllegalArgumentException e) {
					//�w�]���
					res_timebg = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J:�q�����ɬq�_�l�ɶ�");
				}
				
				java.sql.Timestamp res_timefn = null;
				try {
					res_timefn = java.sql.Timestamp.valueOf(req.getParameter("res_timefn").trim());
				}catch(IllegalArgumentException e) {
					//�w�]���
					res_timefn = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J:�q�����ɬq�����ɶ�");
				}

				Integer res_people = null;
				try {
					res_people = new Integer(req.getParameter("res_people").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�q��H��:�ж�Ʀr�B���o���ť�");
				}
				
				Integer res_status = null; 
				try {
					res_status = new Integer(req.getParameter("res_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�q�쪬�A:�ж�Ʀr�B���o���ť�");
				}
				
				
				ResVO resVO = new ResVO();
			
				resVO.setMem_no(mem_no);
				resVO.setDek_no(dek_no);
				resVO.setRes_timebg(res_timebg);
				resVO.setRes_timefn(res_timefn);
				resVO.setRes_people(res_people);
				resVO.setRes_status(res_status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resVO", resVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/res/addRes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ResService resSvc = new ResService();
				resVO = resSvc.addRes(mem_no, dek_no, res_timebg,
						res_timefn, res_people, res_status);
				
				String url = "/res/listAllRes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/res/addRes.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}

		