package com.shopping.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/front_end/shoppingCart/checkoutServlet.do")
public class checkoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String action = req.getParameter("action"); // 取得aciton
		String eatIn_takeAway = req.getParameter("eatIn&takeAway"); // 取得用餐方式
		String branch_No = req.getParameter("branch_no"); // 取得分店編號
		String ps = req.getParameter("ps");
		
		if ("insert".equals(action)) {// 來自checkout.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String address = req.getParameter("deliv_addres");
				String addressRegx = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";

				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("deliv_addres", "外送地址 : 請勿空白");
				} else if (!address.trim().matches(addressRegx)) {
					errorMsgs.put("address", "地址: 只能是中、英文字母、數字");
				}
				
				if("請選擇".equals(branch_No)) {
					errorMsgs.put("branch_no", "請選擇取餐分店");
				}
				
				
				String county = req.getParameter("deliv_addres");
				String town = req.getParameter("mem_Retown");
				if("請選擇".equals(county)||"請選擇".equals(town)) {
					errorMsgs.put("countytwon", "請選擇鄉鎮市區");
				}
				

				String time = req.getParameter("time"); // 取得取餐時間
				if (time == null || time.trim().length() == 0) {
					errorMsgs.put("time", "請選擇取餐時間 ");
				}

				String order_pstatus = req.getParameter("order_pstatus"); // 付款方式，信用卡:2, 現金:1
				
				String card_number =null;
				String full_name = null;
				String expiry = null ;
				String cvc = null ;
				if ("2".equals(order_pstatus)) { //為2 ,檢查信用卡

					card_number = req.getParameter("number");// 取得卡號
					String card_number_regx = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";

					if (card_number == null || card_number.trim().length() == 0) {
						errorMsgs.put("card_number", "請輸入卡號");

					} else if (!card_number.matches(card_number_regx)) {
						errorMsgs.put("card_number", "卡號為16位阿拉伯數字");
					}

					full_name = req.getParameter("name");// 取得持卡人姓名
					String full_name_regx = "^[(\u4e00-\u9fa5)(a-zA-Z)]++-[(\\u4e00-\\u9fa5)(a-zA-Z)]++";
					System.out.println(full_name.trim().matches(full_name_regx));

					if (full_name == null || full_name.trim().length() == 0) {

						errorMsgs.put("full_name", "請輸入持卡人姓名");
					} else if (!full_name.trim().matches(full_name_regx)) {
						errorMsgs.put("full_name", "持卡人姓名為中英文字且不可有空格(eg.Kevin-Tsai)");
					}

					expiry = req.getParameter("expiry");// 取得卡片期限
					String expiry_regx = "^0[1-9]||1[0-2]\\s/\\s\\d{2}$";
					System.out.println(expiry.matches(expiry_regx));

					if (expiry == null || expiry.trim().length() == 0) {
						errorMsgs.put("expiry", "請輸卡限");

					} else if (!expiry.matches(expiry_regx)) {
						errorMsgs.put("expiry", "年月格式:MM/YY");
					}

					cvc = req.getParameter("cvc");// 取得安全碼
					String cvc_regx = "^\\d{3}$";
					if (cvc == null || cvc.trim().length() == 0) {

						errorMsgs.put("cvc", "請輸入安全碼");
					} else if (!cvc.matches(cvc_regx)) {
						errorMsgs.put("cvc", "安全碼須為3個數字");
					}
				}
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("eatIntakeAway", eatIn_takeAway);//剛寫的錯誤資料依然回傳
						req.setAttribute("branch_No", branch_No);
						req.setAttribute("deliv_addres", address);
						req.setAttribute("mem_Retown", town);
						req.setAttribute("mem_Recounty", county);
						req.setAttribute("time", time);
						//credit card
						req.setAttribute("card_number", card_number);
						req.setAttribute("name", full_name);
						req.setAttribute("expiry", expiry);
						req.setAttribute("cvc", cvc);
						
						req.setAttribute("order_pstatus", order_pstatus);//回給錯誤頁面判斷是否顯示信用卡 1為顯示
						RequestDispatcher failureView = req.getRequestDispatcher("Checkout.jsp");
						failureView.forward(req, res);
						return;//中斷
					}
					/*************************** 2.開始新增資料 ***************************************/
				
					/**************************** 3.新增完成,準備轉交(Send data to orderForm servlet) ***********/
					req.setAttribute("card_number", card_number);
				
					req.setAttribute("branch_no", branch_No);
					req.setAttribute("order_type", eatIn_takeAway);
					req.setAttribute("deliv_addres", address);
					req.setAttribute("order_pstatus", order_pstatus);
					req.setAttribute("time", time);
					req.setAttribute("ps", ps);
					
					req.getRequestDispatcher(req.getContextPath() + "/front_end/orderform/orderform.do").forward(req,res);
					return;
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("Checkout.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
