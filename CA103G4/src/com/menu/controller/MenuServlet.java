package com.menu.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menu.model.*;

@WebServlet("/front_end/testimgupload/menu.do")
public class MenuServlet extends HttpServlet{

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
				String menu_No  = req.getParameter("menu_No").trim();
				//無輸入
				if(menu_No == null || menu_No.length() == 0) {
					errorMsgs.add("請輸入餐點編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//格式錯誤
				if(!(menu_No.matches("M\\d{3}")))
					errorMsgs.add("餐點編號格式不正確");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MenuService menuSvc = new MenuService();
				MenuVO menuVO = menuSvc.getOneMenu(menu_No);
				if(menuVO == null)
					errorMsgs.add("查無資料");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("menuVO", menuVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/listOneMenu.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getOne_For_Update".equals(action)){  // 來自listAllMenu.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String menu_No = req.getParameter("menu_No").trim();
				
				/***************************2.開始查詢資料****************************************/
				MenuService menuSvc = new MenuService();
				MenuVO menuVO = menuSvc.getOneMenu(menu_No);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("menuVO", menuVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/update_menu_input.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){  // 來自addMenu.jsp的請求  
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			try {
				/***************************1.接收請求參數****************************************/
				
				//無輸入OR長度超出範圍
				String menu_Id = req.getParameter("menu_Id").trim();
				if(menu_Id == null || menu_Id.length() == 0) {
					errorMsgs.add("餐點名稱:請勿空白");
				}  else if(menu_Id.length() >= 50) {
					errorMsgs.add("餐點名稱:長度需小於50個字元");
				}
				
				//無輸入OR長度超出範圍
				String menu_Type = req.getParameter("menu_Type").trim();
				if(menu_Type == null || menu_Type.length() == 0) {
					errorMsgs.add("餐點類型:請勿空白");
				} else if(menu_Type.length() >= 20) {
					errorMsgs.add("餐點類型:長度需小於20個字元 ");
				}
				//無輸入OR格式不正確
				String str = req.getParameter("menu_Price");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐點價格");
				}
				Integer menu_Price = null;
				try {
					menu_Price = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐點價格格式不正確");
				}
				
				//無輸入OR長度超出範圍
				String menu_Intro = req.getParameter("menu_Intro").trim();
				if(menu_Intro == null || menu_Intro.length() == 0) {
					errorMsgs.add("通知內容:請勿空白");
				} else if(menu_Intro.length() >= 100) {
					errorMsgs.add("通知內容:長度需小於100個字元");
				}
//imgtest				
				byte[] menu_Photo = null;
				
				
				//無輸入OR格式不正確
				str = req.getParameter("menu_Status");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐點狀態");
				}
				Integer menu_Status = null;
				try {
					menu_Status = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐點狀態格式不正確");
				}
				
				MenuVO menuVO = new MenuVO();
				menuVO.setMenu_Id(menu_Id);
				menuVO.setMenu_Type(menu_Type);
				menuVO.setMenu_Price(menu_Price);
				menuVO.setMenu_Intro(menu_Intro);
				menuVO.setMenu_Photo(menu_Photo);
				menuVO.setMenu_Status(menu_Status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO);  // 含有輸入格式錯誤的perntdVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/addMenu.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料****************************************/
				MenuService menuSvc = new MenuService();
				menuVO = menuSvc.addMenu(menu_Id, menu_Type, menu_Price, menu_Intro, menu_Photo, menu_Status);
								
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				req.setAttribute("menuVO", menuVO);  // 資料庫新增成功後,正確的的perntdVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/listAllMenu.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("資料新增失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/testimgupload/addMenu.jsp");
				failuerView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {  // 來自update_perntd_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String menu_No = req.getParameter("menu_No").trim();
				
				//無輸入OR格式錯誤
				String mem_No = req.getParameter("mem_No").trim();
				if(mem_No == null || mem_No.length() == 0) {
					errorMsgs.add("會員編號:請勿空白");
				} else if(!(mem_No.matches("M\\d{6}"))) {
					errorMsgs.add("會員編號:格式不正確 ");
				}
				
				//無輸入OR格式錯誤
				String nt_No = req.getParameter("nt_No").trim();
				if(nt_No == null || nt_No.length() == 0) {
					errorMsgs.add("系統通知編號:請勿空白");
				} else if(!(nt_No.matches("NT\\d{2}"))) {
					errorMsgs.add("系統通知編號:格式不正確 ");
				}
				
				//長度超出範圍
				String perntd_Cont = req.getParameter("perntd_Cont").trim();
				if(perntd_Cont == null || perntd_Cont.length() == 0) {
					errorMsgs.add("通知內容:請勿空白");
				} else if(perntd_Cont.length() >= 1000) {
					errorMsgs.add("通知內容:長度需小於1000個字元");
				}
				
				//日期格式錯誤
				String perntd_Date = req.getParameter("perntd_Date");
				
				MenuVO menuVO = new MenuVO();
//				MenuVO.setPerntd_No(perntd_No);
//				MenuVO.setMem_No(mem_No);
//				MenuVO.setNt_No(nt_No);
//				MenuVO.setPerntd_Cont(perntd_Cont);
//				MenuVO.setPerntd_Date(perntd_Date);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO);  // 含有輸入格式錯誤的perntdVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/update_menu_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料****************************************/
				MenuService menuSvc = new MenuService();
//				menuVO = menuSvc.updateMenu(menu_No, mem_No, nt_No, perntd_Cont, perntd_Date);
								
				/***************************3.修改完成,準備轉交(Send the Success view)************/
				req.setAttribute("menuVO", menuVO);  // 資料庫update成功後,正確的的perntdVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/listOneMenu.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/update_menu_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("delete".equals(action)) {  // 來自listAllPerntd.jsp
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String menu_No = req.getParameter("menu_No");
				String whichPage = req.getParameter("whichPage");
				
				/***************************2.開始刪除資料****************************************/
				MenuService menuSvc = new MenuService();
				menuSvc.deleteMenu(menu_No);
								
				/***************************3.刪除完成,準備轉交(Send the Success view)************/
				req.setAttribute("menu_No", menu_No);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/testimgupload/listAllMenu.jsp?"+whichPage);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
				
			} catch(Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/testimgupload/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
