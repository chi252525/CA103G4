package com.orderform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.orderform.model.*;
import com.orderinvoice.model.OrderinvoiceVO;

public class OrderformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			//接受參數
			//訂單參數
			String dekno = req.getParameter("dek_no");
			String memno = req.getParameter("mem_no");
			String brano = req.getParameter("branch_no");
			Integer ordertyp = new Integer(req.getParameter("order_type").trim());
			Integer orderpri = new Integer(req.getParameter("order_price").trim());			
			Integer ordersts = new Integer(req.getParameter("order_status").trim());
			String addres = req.getParameter("deliv_addres");
			Integer orderpa = new Integer(req.getParameter("order_pstatus").trim());
			//明細參數
			List<OrderinvoiceVO> list = (List)req.getAttribute("invoice");//等前端 更改
			//還沒做，在此處取得需要的參數!!!!!!!!!!!!!
			//範例
			//想法>>用迴圈跑出送進來的參數並加入list
//			EmpVO empXX = new EmpVO();   // 員工POJO1
//			empXX.setEname("吳x");
//			empXX.setJob("MANAGER");
//			empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//			empXX.setSal(new Double(15000));
//			empXX.setComm(new Double(150));
//			list.add(empXX);
			
			OrderformVO orderformVO = new OrderformVO();
			orderformVO.setDek_no(dekno);
			orderformVO.setMem_no(memno);
			orderformVO.setBranch_no(brano);
			orderformVO.setDeliv_no(null);
			orderformVO.setOrder_type(ordertyp);
			orderformVO.setOrder_price(orderpri);
			orderformVO.setOrder_status(ordersts);
			orderformVO.setDeliv_addres(addres);
			orderformVO.setOrder_pstatus(orderpa);
			//開始新增
			OrderformService ordSvc = new OrderformService();
			ordSvc.addOrd(orderformVO, list);
			//準備轉交
			//尚未寫
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}