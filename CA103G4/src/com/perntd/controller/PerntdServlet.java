package com.perntd.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perntd.model.*;

public class PerntdServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//無輸入
				String perntd_No  = req.getParameter("perntd_No");
				if(perntd_No == null || (perntd_No.trim()).length() == 0) {
					errorMsgs.add("請輸入個人通知流水號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//格式錯誤
				if(!(perntd_No.matches("P\\d{6}")))
					errorMsgs.add("個人通知流水號格式不正確");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				PerntdService perntdSvc = new PerntdService();
				PerntdVO perntdVO = perntdSvc.getOnePerntd(perntd_No);
				if(perntdVO == null)
					errorMsgs.add("查無資料");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("perntdVO", perntdVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/perntd/listOnePerntd.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getOne_For_Update".equals(action)){  // 來自listAllPerntd.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String perntd_No = req.getParameter("perntd_No");
				
				/***************************2.開始查詢資料****************************************/
				PerntdService perntdSvc = new PerntdService();
				PerntdVO perntdVO = perntdSvc.getOnePerntd(perntd_No);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("perntdVO", perntdVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/perntd/update_perntd_input.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/listAllPerntd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){  // 來自addPerntd.jsp的請求  
			
		}
		
		if("update".equals(action)) {  // 來自update_perntd_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String perntd_No = req.getParameter("perntd_No");
				
				//無輸入OR格式錯誤
				String mem_No = req.getParameter("mem_No").trim();
				if(mem_No == null || (mem_No.trim()).length() == 0) {
					errorMsgs.add("會員編號:請勿空白");
				} else if(!(mem_No.matches("NT\\d{2}"))) {
					errorMsgs.add("會員編號:格式不正確 ");
				}
				
				//無輸入OR格式錯誤
				String nt_No = req.getParameter("nt_No").trim();
				if(nt_No == null || (nt_No.trim()).length() == 0) {
					errorMsgs.add("系統通知編號:請勿空白");
				} else if(!(mem_No.matches("NT\\d{2}"))) {
					errorMsgs.add("系統通知編號:格式不正確 ");
				}
				
				//長度超出範圍
				String perntd_Cont = req.getParameter("perntd_Cont").trim();
				if(perntd_Cont.length() >= 1000)
					errorMsgs.add("通知內容:長度需小於1000個字元");
				
				//日期格式錯誤
				java.sql.Date perntd_Date = null;
				try {
					perntd_Date = java.sql.Date.valueOf(req.getParameter("perntd_Date").trim());
				} catch (IllegalArgumentException e) {
					perntd_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				PerntdVO perntdVO = new PerntdVO();
				perntdVO.setPerntd_No(perntd_No);
				perntdVO.setMem_No(mem_No);
				perntdVO.setNt_No(nt_No);
				perntdVO.setPerntd_Cont(perntd_Cont);
//				perntdVO.setPerntd_Date(perntd_Date);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("perntdVO", perntdVO);  // 含有輸入格式錯誤的perntdVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/update_perntd_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料****************************************/
				PerntdService perntdSvc = new PerntdService();
//				perntdVO = perntdSvc.updatePerntd(perntd_No, mem_No, nt_No, perntd_Cont, perntd_Date);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("perntdVO", perntdVO);  // 資料庫update成功後,正確的的perntdVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/update_perntd_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("delete".equals(action)) {  // 來自listAllPerntd.jsp
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String perntd_No = req.getParameter("perntd_No");
				
				/***************************2.開始刪除資料****************************************/
				PerntdService perntdSvc = new PerntdService();
				perntdSvc.deletePerntd(perntd_No);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("perntd_No", perntd_No);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/perntd/listAllPerntd.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
				
			} catch(Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/perntd/listAllPerntd.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
