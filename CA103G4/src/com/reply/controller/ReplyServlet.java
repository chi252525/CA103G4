package com.reply.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.post.model.PostService;
import com.reply_msg.model.ReplyService;
import com.reply_msg.model.ReplyVO;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 8*1024*1024, maxRequestSize = 5*5*1024*1024)
public class ReplyServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("跳到Servlet+action ="+action );
		 /***********************新增留言*************************/	
        if ("insert".equals(action)) {
        	List<String> errorMsgs =new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	try {
        	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
        	String mem_No = req.getParameter("mem_No");
        	System.out.println("新增留言 會員編號: "+mem_No);
        //	String rply_No = req.getParameter("rply_No");
        	String post_No = req.getParameter("post_No");
        	String rply_Cont = req.getParameter("rply_Cont");
			if (rply_Cont == null || rply_Cont.trim().length() == 0) {
				errorMsgs.add("留言內容請勿空白");
			}	
       
				ReplyVO rplyVO= new ReplyVO();
		//		rplyVO.setRply_No(rply_No);
				rplyVO.setMem_No(mem_No);
				rplyVO.setPost_No(post_No);
				
				/***************************2.開始新增資料***************************************/
				ReplyService rplySvc = new ReplyService();
//				rplyVO=rplySvc.addReply(rply_No,mem_No,post_No,rply_Cont);
				rplyVO=rplySvc.addReply(mem_No,post_No,rply_Cont);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/post/listOnepost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
        	}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/post/listOnepost.jsp");
				failureView.forward(req, res);
			}
        }
        
        
        if ("delete".equals(action)) {
//			System.out.println("delete開始");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String rply_No = req.getParameter("rply_No");
				
				ReplyVO rplyVO= new ReplyVO();
				rplyVO.setRply_No(rply_No);
				
				/*************************** 2.開始刪除 留言***************************************/
				ReplyService rplySvc = new ReplyService();
				rplySvc.delete(rply_No);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/post/listOnepost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listOnepost.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("updateStatus".equals(action)) {
//			System.out.println("delete開始");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				System.out.println("updateStatus in ");
				String rply_No = req.getParameter("rply_No");
				System.out.println("rply_No"+rply_No);
				String rply_Status = req.getParameter("rply_Status");
				System.out.println("rply_Status"+rply_Status);
				/*************************** 2.開始隱藏或顯示留言***************************************/
				ReplyService rplySvc = new ReplyService();
				if(rply_Status=="RS1") {
					rplySvc.updateStatus("RS0",rply_No);
					System.out.println("RS1的情況");
				}else {
					rplySvc.updateStatus("RS1",rply_No);
					System.out.println("RS0的情況");
				}
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/post/listOnepost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listOnepost.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
        

        
        	
	}
	}