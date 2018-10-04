package com.couponhistory.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.couponhistory.model.CouponhistoryService;
import com.couponhistory.model.CouponhistoryVO;
import com.member.model.MemberVO;

public class CouponhistoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		
		
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String mem_No = req.getParameter("memNo");
				if (mem_No == null || mem_No.trim().length() == 0) {
					errorMsgs.add("請先登入會員才能取得優惠卷");
				}
				String coucat_No= req.getParameter("coucat_No");
				CouponService Csvc= new CouponService();
				List<CouponVO> couponlist = Csvc.getCouponByCata(coucat_No);
				
				CouponhistoryService chSvc = new CouponhistoryService();
				List<CouponhistoryVO> cplist_memhaved = chSvc.getCouponByMem(mem_No);
				
	
				
				
				//當點籍伊次發放數減一
				
				
				//
				
				
				
			}catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/storedrecord/addStoredrecord.jsp").forward(req, res);
			}
			
		}
	
	
	}
	}