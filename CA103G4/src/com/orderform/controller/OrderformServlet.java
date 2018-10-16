package com.orderform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.member.model.MemberVO;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;
import com.orderform.model.*;
import com.orderform.smsservice.Send;
import com.orderinvoice.model.OrderinvoiceService;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String iord;
	int all;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	@SuppressWarnings({ "unchecked", "null" })
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
			if (("delivery").equals(req.getParameter("eatIn&takeAway"))) {
				ordertype = 2;
				addres = req.getParameter("deliv_addres");
				req.getSession().setAttribute("deliv_addres", addres);
			} else if(("takeaway").equals(req.getParameter("eatIn&takeAway"))) {
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
			String card4 = null;
			if (Integer.parseInt((String) req.getAttribute("order_pstatus")) == 2) {
				orderpa = 3;
				
				card4 = ((String)req.getAttribute("card_number")).substring(15);
				
				req.getSession().setAttribute("card_number",card4);
			} else if (Integer.parseInt((String) req.getAttribute("order_pstatus")) == 3) {
				orderpa = 4;
				req.getSession().setAttribute("point",req.getAttribute("order_pstatus"));
			} else {
				orderpa = 1;
			} //信用卡表已付款2; 現金表1未付款
			req.getSession().setAttribute("time", req.getAttribute("time"));
			req.getSession().setAttribute("ps", req.getAttribute("ps"));
			
			
			//明細參數
			List<OrderinvoiceVO> list = new ArrayList<>();//等前端 更改 

			Vector<MenuVO> inv = new Vector<>();
			Vector<CustommealsVO> customv = new Vector<>();
			inv = (Vector<MenuVO>) req.getSession().getAttribute("shoppingcart");//取得送來的餐點參數
			customv = (Vector<CustommealsVO>) req.getSession().getAttribute("shoppingcartCustom");//取得送來的自訂餐點參數
			System.out.println("inv" + inv);
			System.out.println("customv" + customv);
			
			OrderinvoiceVO oin = null;
			
			String[] oinlist = null;
			String[] qinList = null;
			
			if (inv != null) {
				
				oinlist = new String[inv.size()];
				qinList = new String[inv.size()];
			
				for (int z = 0 ; z < inv.size() ; z++){
	                String eat = String.valueOf(inv.get(z).getMenu_No());
	                oinlist[z] = eat;
	                int quantity = inv.get(z).getMenu_quantity();
	                qinList[z] = String.valueOf(quantity);
	           }
				
				for (int i = 0; i < oinlist.length; i++) {
					oin = new OrderinvoiceVO();
					oin.setMenu_no(oinlist[i]);
					oin.setMenu_nu(qinList[i]);					
					list.add(oin);
				}
			}
			
			String[] costomOinlist = null;
			String[] costomQinList = null;
			
			
			if (customv != null) {

				costomOinlist = new String[customv.size()];
				costomQinList = new String[customv.size()];
				
				for (int z = 0 ; z < customv.size() ; z++){
	                String eat = String.valueOf(customv.get(z).getcustom_No());
	                costomOinlist[z] = eat;
	                int quantity = customv.get(z).getcustom_Quantity();
	                costomQinList[z] = String.valueOf(quantity);
	           }
				
				for (int i = 0; i < costomOinlist.length; i++) {
					oin = new OrderinvoiceVO();
					oin.setCustom_no(costomOinlist[i]);
					oin.setCustom_nu(costomQinList[i]);
					list.add(oin);
				}
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
			
			
			
			
			
			MemberVO memVO = (MemberVO) req.getSession().getAttribute("memVO");
			String memtel = memVO.getMem_Phone();//會員的手機
			//對會員發送簡訊
			Send toMem = new Send();
//			String[] tels ={memtel};//上線用
			String[] tels ={"0933628324"};//上線用
			
			String message = null; 
			if (ordertype == 1) {
				//外帶
				message= "\t取餐資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n分店地址:"+req.getSession().getAttribute("braAdr")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");			
			} else if (ordertype == 2) {
				//外送
				message= "\t取餐資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n取餐地址:"+req.getSession().getAttribute("deliv_addres")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");	
			}
			
			toMem.sendMessage(tels , message);

			//有新的外送或外帶訂單
			Send toEmp = new Send();
//			String[] tels2 ={tel};//上線用
			String[] tels2 ={"0933628324"};//上線用
			String message2 = null; 
			
			if (ordertype == 1) {
				//外帶
				message2= "\t訂單資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");			
			} else if (ordertype == 2) {
				//外送
				message2= "\t訂單資訊\n"
						+ "訂單編號:"+req.getSession().getAttribute("ordNo")
						+ "\n取餐時間:"+req.getSession().getAttribute("time");	
			}
			
			toEmp.sendMessage(tels2 , message2);
			
			
			
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
		
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				OrderformService empSvc = new OrderformService();
				List<OrderformVO> list  = empSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/orderform/orderform.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/orderform/orderform.jsp");
				failureView.forward(req, res);
			}
		}
		

	}

}