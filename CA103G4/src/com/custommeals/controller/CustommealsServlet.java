package com.custommeals.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.custommeals.model.*;


public class CustommealsServlet extends HttpServlet{

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
					String custom_No = req.getParameter("custom_No").trim();
					//無輸入
					if (custom_No == null || custom_No.length() == 0) {
						errorMsgs.add("請輸入自訂餐點編號");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					//格式錯誤
					if(!(custom_No.matches("C\\d{10}")))
						errorMsgs.add("自訂餐點編號格式不正確");
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/select_page.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					CustommealsService custommealsSvc = new CustommealsService();
					CustommealsVO custommealsVO = custommealsSvc.getOneCustommeals(custom_No);
					if(custommealsVO == null)
						errorMsgs.add("查無資料");
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/select_page.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("custommealsVO", custommealsVO);
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/custommeals/listOneCustommeals.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				}catch(Exception e) {
					errorMsgs.add("無法取得資料" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/select_page.jsp");
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
					String custom_No = req.getParameter("custom_No").trim();
					
					/***************************2.開始查詢資料****************************************/
					CustommealsService custommealsSvc = new CustommealsService();
					CustommealsVO custommealsVO = custommealsSvc.getOneCustommeals(custom_No);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("custommealsVO", custommealsVO);
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/custommeals/update_custommeals_input.jsp");
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch(Exception e) {
					errorMsgs.add("無法取得要修改的資料" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/listAllCustommeals.jsp");
					failureView.forward(req, res);
				}
			}
			
			if("insert".equals(action)){  // 來自addCustommeals.jsp的請求  
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.接收請求參數****************************************/
					
					//無輸入OR長度超出範圍
					String mem_No = req.getParameter("mem_No").trim();
					if(mem_No == null || mem_No.length() == 0) {
						errorMsgs.add("會員編號:請勿空白");
					}  else if(mem_No.length() >= 10) {
						errorMsgs.add("會員編號格式不符");
					}
					
										
					//無輸入OR長度超出範圍
					String custom_Name = req.getParameter("custom_Name").trim();
					if(custom_Name == null || custom_Name.length() == 0) {
						errorMsgs.add("自訂餐點名稱:請勿空白");
					} else if(custom_Name.length() >= 20) {
						errorMsgs.add("自訂餐點名稱長度過長");
					}
					//無輸入OR格式不正確
					String str = req.getParameter("custom_Price");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入自訂餐點價格");
					}
					Integer custom_Price = null;
					try {
						custom_Price = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("自訂餐點價格格式不正確");
					}
					
				
				
					
	//******************* img要存入Database需先取得part物件，轉成inputstream後再放入byte[] *******************
					
					Part part = req.getPart("custom_Photo");
					InputStream in = part.getInputStream();
					byte[] custom_Photo = new byte[in.available()];
					in.read(custom_Photo);
					in.close();
					
	//***********************************************************************************************
					
					
					CustommealsVO custommealsVO = new CustommealsVO();
					custommealsVO.setMem_No(mem_No);
					custommealsVO.setCustom_Name(custom_Name);
					custommealsVO.setCustom_Price(custom_Price);
					custommealsVO.setCustom_Photo(custom_Photo);
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("custommealsVO", custommealsVO);  // 含有輸入格式錯誤的custommealsVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/addCustommeals.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始新增資料****************************************/
					CustommealsService custommealsSvc = new CustommealsService();
					custommealsVO = custommealsSvc.addCustommeals(mem_No, custom_Name, custom_Price, custom_Photo);
									
					/***************************3.新增完成,準備轉交(Send the Success view)************/
					req.setAttribute("custommealsVO", custommealsVO);  // 資料庫新增成功後,正確的custommealsVO物件,存入req
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/custommeals/listAllCustommeals.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch(Exception e) {
					errorMsgs.add("資料新增失敗"+e.getMessage());
					RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/custommeals/addCustommeals.jsp");
					failuerView.forward(req, res);
				}
			}
			
			if("update".equals(action)) {  // 來自update_menu_input.jsp的請求
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.接收請求參數****************************************/
					String custom_No = req.getParameter("custom_No").trim();
					
					//無輸入OR長度超出範圍
					String mem_No = req.getParameter("mem_No").trim();
					if(mem_No == null || mem_No.length() == 0) {
						errorMsgs.add("會員編號:請勿空白");
					}  else if(mem_No.length() >= 10) {
						errorMsgs.add("會員編號格式不符");
					}
					
					//無輸入OR長度超出範圍
					String custom_Name = req.getParameter("custom_Name").trim();
					if(custom_Name == null || custom_Name.length() == 0) {
						errorMsgs.add("自訂餐點名稱:請勿空白");
					} else if(custom_Name.length() >= 20) {
						errorMsgs.add("自訂餐點名稱長度過長");
					}
					//無輸入OR格式不正確
					String str = req.getParameter("custom_Price");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入自訂餐點價格");
					}
					Integer custom_Price = null;
					try {
						custom_Price = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("自訂餐點價格格式不正確");
					}
					

	//******************* img要存入Database需先取得part物件，轉成inputstream後再放入byte[] *******************
					
					Part part = req.getPart("custom_Photo");
					InputStream in = part.getInputStream();
					byte[] custom_Photo = new byte[in.available()];
					in.read(custom_Photo);
					in.close();
					
	//***********************************************************************************************
					
					
					CustommealsVO custommealsVO = new CustommealsVO();
					custommealsVO.setCustom_No(custom_No);
					custommealsVO.setMem_No(mem_No);
					custommealsVO.setCustom_Name(custom_Name);
					custommealsVO.setCustom_Price(custom_Price);
					custommealsVO.setCustom_Photo(custom_Photo);
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("custommealsVO", custommealsVO);  // 含有輸入格式錯誤的custommealsVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/addCustommeals.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料****************************************/
					CustommealsService custommealsSvc = new CustommealsService();
					custommealsVO = custommealsSvc.updateCustommeals(custom_No, mem_No, custom_Name, custom_Price, custom_Photo);
									
					/***************************3.修改完成,準備轉交(Send the Success view)************/
					req.setAttribute("custommealsVO", custommealsVO);  // 資料庫修改成功後,正確的的menuVO物件,存入req
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/custommeals/listOneCustommeals.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch(Exception e) {
					errorMsgs.add("資料修改失敗"+e.getMessage());
					RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/custommeals/addCustommeals.jsp");
					failuerView.forward(req, res);
				}
			}
			
			if("delete".equals(action)) {  // 來自listAllMenu.jsp
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String custom_No = req.getParameter("custom_No");
					String whichPage = req.getParameter("whichPage");
					
					/***************************2.開始刪除資料****************************************/
					CustommealsService custommealsSvc = new CustommealsService();
					custommealsSvc.deleteCustommeals(custom_No);
									
					/***************************3.刪除完成,準備轉交(Send the Success view)************/
					req.setAttribute("custom_No", custom_No);
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/custommeals/listAllCustommeals.jsp?"+whichPage);
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
					
				} catch(Exception e) {
					errorMsgs.add("刪除資料失敗" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/custommeals/listAllCustommeals.jsp");
					failureView.forward(req, res);
				}
			}
			
		}

	}
