package com.orderform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.custommeals.model.CustommealsService;
import com.custommeals.model.CustommealsVO;
import com.menu.model.MenuVO;
import com.orderform.model.*;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			//接受參數
			//訂單參數
			
			String dekno;
			if (req.getParameter("dek_no") != null || req.getParameter("dek_no").trim().length() != 0) {
				dekno = req.getParameter("dek_no");
			} else {
				dekno = "D000000001";
			}
			String memno = (String) req.getSession().getAttribute("mem_no");
			String brano = req.getParameter("branch_No");
			Integer orderpri = new Integer(req.getParameter("order_price").trim());	
			
			Integer ordertype = null;
			String addres;
			if (req.getAttribute("order_type") != null || ((String)req.getAttribute("order_type")).trim().length() != 0) {
				req.setAttribute("order_type", req.getAttribute("order_type"));
				ordertype = (Integer) req.getAttribute("order_type");
				addres = req.getParameter("deliv_addres");	
			} else {
				req.setAttribute("order_type", req.getAttribute("order_type"));
				addres = null;
			}
			
			Integer orderpa;
			if (req.getAttribute("card_number") != null || ((String) req.getAttribute("card_number")).trim().length() != 0) {
				orderpa = 2;
				req.setAttribute("card_number",req.getAttribute("card_number"));
			} else {
				orderpa = 1;
			} //信用卡表已付款2; 現金表1未付款

			req.setAttribute("time", req.getAttribute("time"));
			req.setAttribute("ps", req.getAttribute("ps"));
			
			
			//明細參數
			List<OrderinvoiceVO> list = new ArrayList<>();//等前端 更改 

			Vector inv = new Vector();
			inv = (Vector) req.getSession().getAttribute("shoppingcart");//取得送來的餐點參數
			String[] oinlist = new String[inv.size()];
			
			
			for (int z = 0 ; z < inv.size() ; z++){
                String eat = (String)inv.get(z);
                oinlist[z] = eat;
           }
			
			OrderinvoiceVO oin = null;
			
			for (int i = 0; i < oinlist.length; i++) {
				oin = new OrderinvoiceVO();
				if (("M").equals(String.valueOf(oinlist[i].charAt(0)))) {
					oin.setMenu_no(oinlist[i]);
				} else {
					oin.setCustom_no(oinlist[i]);
				}
				list.add(oin);
			}
			
			OrderformVO orderformVO = new OrderformVO();
			orderformVO.setDek_no(dekno);
			orderformVO.setMem_no(memno);
			orderformVO.setBranch_no(brano);
			orderformVO.setOrder_type(ordertype);
//			orderformVO.setOrder_price(orderpri);
			orderformVO.setDeliv_addres(addres);
			orderformVO.setOrder_pstatus(orderpa);
			//開始新增
			OrderformService ordSvc = new OrderformService();
			ordSvc.addOrd(orderformVO, list);
			//準備轉交
//			String url = "select_page.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			
		}
		
		if ("update".equals(action)) {
			//接受參數
			//訂單參數
			String dekno = req.getParameter("dek_no");
			String memno = req.getParameter("mem_no");
			String brano = req.getParameter("branch_no");
			Integer ordertyp = new Integer(req.getParameter("order_type").trim());
			Integer orderpri = new Integer(req.getParameter("order_price").trim());	
			String addres = req.getParameter("deliv_addres");
			Integer orderpa = new Integer(req.getParameter("order_pstatus").trim());
			//明細參數
			List<OrderinvoiceVO> list = new ArrayList<OrderinvoiceVO>();//等前端 更改 
			list = (List<OrderinvoiceVO>)req.getAttribute("invoice");
			
			OrderformVO orderformVO = new OrderformVO();
			orderformVO.setDek_no(dekno);
			orderformVO.setMem_no(memno);
			orderformVO.setBranch_no(brano);
			orderformVO.setOrder_type(ordertyp);
			orderformVO.setOrder_price(orderpri);
			orderformVO.setDeliv_addres(addres);
			orderformVO.setOrder_pstatus(orderpa);
			//開始新增
			OrderformService ordSvc = new OrderformService();
			ordSvc.addOrd(orderformVO, list);
			//準備轉交
//			String url = "select_page.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			
		}
		
		
		if("getOne_For_Display_By_Mem_No".equals(action)) {  // 來自select_page.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_No = req.getParameter("mem_No").trim();
				//無輸入
				if (mem_No == null || mem_No.length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				
				//格式錯誤
				if(!(mem_No.matches("M\\d{6}")))
					errorMsgs.add("會員編號格式不正確");

				
				/***************************2.開始查詢資料*****************************************/
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/orderform/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				OrderformService orderformSvc = new OrderformService();
				List<OrderformVO> orderformVO = orderformSvc.getOrderNoByMemNo(mem_No);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderformVO", orderformVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/orderform/listOneOrderformByMemNo.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/orderform/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
	}

}