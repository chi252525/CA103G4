package com.orderform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.branch.model.BranchService;
import com.custommeals.model.CustommealsService;
import com.custommeals.model.CustommealsVO;
import com.menu.model.MenuVO;
import com.orderform.model.*;
import com.orderform.smsservice.Send;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String iord;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			
			//取餐資訊處理(使用存取訂單主鍵)(暫時失敗)
//			if (iord != null) {
//				String brano = (String)req.getAttribute("branch_no");
//				
//				BranchService brSvc = new BranchService();
//				String brn = (brSvc.findByBranch_No(brano)).getBranch_Name();
//				req.setAttribute("braName", brn);
//				//取餐用，取得分店電話
//				String tel = (brSvc.findByBranch_No(brano)).getBranch_Tel();
//				req.setAttribute("braTel", tel);
//				//取餐地址
//				String adr = (brSvc.findByBranch_No(brano)).getBranch_Addr();
//				req.setAttribute("braAdr", adr);
//				
//				req.setAttribute("ordNo", iord);
//				
//				String url = "/front_end/orderinvoice/seeorderinvoice.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//				return;
//			}
			//接受參數
			//訂單參數			
			//內用有桌號，否則空值
			String dekno;
			if (("0").equals((String)req.getAttribute("order_type"))) {
				dekno = req.getParameter("dek_no");
			} else {
				dekno = null;
			}
			
			//從session取得會員的編號
			String memno = (String) req.getSession().getAttribute("memNo");
			
			//取得當前分店編號
			String brano = (String)req.getAttribute("branch_no");
			//取餐用，取得分店名稱
			BranchService brSvc = new BranchService();
			String brn = (brSvc.findByBranch_No(brano)).getBranch_Name();
			req.getSession().setAttribute("braName", brn);
			//取餐用，取得分店電話
			String tel = (brSvc.findByBranch_No(brano)).getBranch_Tel();
			req.getSession().setAttribute("braTel", tel);
			//取餐地址
			String adr = (brSvc.findByBranch_No(brano)).getBranch_Addr();
			req.getSession().setAttribute("braAdr", adr);
			
			//取得訂單金額
			Double orderpri = Double.parseDouble((String) req.getAttribute("amount"));
			req.getSession().setAttribute("amount", orderpri);
			
			//看訂單類型，若是外送則收到外送地址
			Integer ordertype = null;
			String addres;
			if (("delivery").equals((String)req.getAttribute("order_type"))) {
				ordertype = 2;
				addres = (String) req.getAttribute("deliv_addres");	
				req.getSession().setAttribute("deliv_addres", addres);
			} else if(("takeaway").equals((String)req.getAttribute("order_type"))) {
				ordertype = 1;
				addres = null;
				req.getSession().setAttribute("deliv_addres", null);
			} else {
				ordertype = 0;
				addres = null;
				req.getSession().setAttribute("deliv_addres", null);
			}
			
			//看付款類，若是使用信用卡則預設為已支付，不是則否，並取得明細資訊//分店、信用卡末四碼、備註、時間、外送地址
			Integer orderpa;
			if (req.getAttribute("card_number") != null) {
				orderpa = 2;
				req.getSession().setAttribute("card_number",req.getAttribute("card_number"));
			} else {
				orderpa = 1;
			} //信用卡表已付款2; 現金表1未付款
			req.getSession().setAttribute("time", req.getAttribute("time"));
			req.getSession().setAttribute("ps", req.getAttribute("ps"));
			
			
			//明細參數
			List<OrderinvoiceVO> list = new ArrayList<>();//等前端 更改 

			Vector<MenuVO> inv = new Vector<>();
			inv = (Vector<MenuVO>) req.getSession().getAttribute("shoppingcart");//取得送來的餐點參數
			String[] oinlist = new String[inv.size()];
			
			for (int z = 0 ; z < inv.size() ; z++){
                String eat = String.valueOf(inv.get(z).getMenu_No());
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
			orderformVO.setOrder_price(orderpri);
			orderformVO.setDeliv_addres(addres);
			orderformVO.setOrder_pstatus(orderpa);
			//開始新增
			OrderformService ordSvc = new OrderformService();
			orderformVO = ordSvc.addOrd(orderformVO, list);
			System.out.println("selvet:"+orderformVO.getOrder_no());
			req.getSession().setAttribute("ordNo",orderformVO.getOrder_no());
			
			iord = (String) req.getAttribute("ordNo");
			
			//發送簡訊
			Send se = new Send();
			String[] tels ={"0933628324"};//測試用
//			String[] tels ={tel};//上線用
			
//			String message = "排程訊息測試";//測試用
			
			String message = null; 
			if (ordertype == 1) {
				message= "\t取餐資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n分店位址:"+req.getSession().getAttribute("braAdr")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");			
			} else if (ordertype == 2) {
				message= "\t取餐資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n取餐位址:"+req.getSession().getAttribute("braAdr")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");	
			}
			
		 	se.sendMessage(tels , message);

			//準備轉交
//			String url = "/front_end/orderinvoice/seeorderinvoice.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		 	
		 	String url = req.getContextPath() + "/front_end/orderinvoice/seeorderinvoice.jsp";
		 	res.sendRedirect(url);
			
		}
		
		if ("update".equals(action)) {
			//接受參數
			//訂單參數
			String dekno = req.getParameter("dek_no");
			String memno = req.getParameter("mem_no");
			String brano = req.getParameter("branch_no");
			Integer ordertyp = Integer.parseInt(req.getParameter("order_type"));
			Double orderpri = Double.parseDouble(req.getParameter("order_price"));	
			String addres = req.getParameter("deliv_addres");
			Integer orderpa = Integer.parseInt(req.getParameter("order_pstatus"));
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