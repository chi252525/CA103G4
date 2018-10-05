package com.couponhistory.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
				
				String mem_No = req.getParameter("mem_No");
				if (mem_No == null || mem_No.trim().length() == 0) {
					errorMsgs.add("請先登入會員才能取得優惠卷");
				}
				String coucat_No= req.getParameter("coucat_No");
				CouponService Csvc= new CouponService();
				
				/*******************取出某一類未發送的Coupon*********************/
				List<CouponVO> couponlist = Csvc.getCouponByCata_Not_Sended(coucat_No,"CP0");
				if(couponlist.size()==0) {
					errorMsgs.add("優惠卷已取完");
				}
				CouponVO oneCouponNotSendedVO = couponlist.get(0);//取出第一個
				String oneCoupon_Sn= oneCouponNotSendedVO.getCoup_Sn();
				
				/*******************開始設定資料*********************/
				CouponhistoryService chSvc= new CouponhistoryService();
				CouponhistoryVO chVO= new CouponhistoryVO();
				chVO.setCoup_sn(oneCoupon_Sn);
				chVO.setMem_no(mem_No);
				chVO.setCoup_state(0);//未使用的狀態
				chSvc.insertOneCouponRecord(oneCoupon_Sn, mem_No, 0);
				/**********************取完後更新狀態為已使用*****************************/
				Csvc.updateCouoponStatus(oneCoupon_Sn);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");

				PrintWriter out = res.getWriter();
				out.print("已取得!!");
				
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/front_end/activity/listAllActivity.jsp";
//
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPost.jsp
//				successView.forward(req, res);
		
			}catch (Exception e) {
				errorMsgs.add("取得優惠卷失敗:" + e.getMessage());
//				req.getRequestDispatcher("/front_end/activity/listAllActivity.jsp").forward(req, res);
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.print("未取得!!");
			}
			
		}
	
	
	}
	}