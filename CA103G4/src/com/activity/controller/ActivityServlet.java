package com.activity.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.post.model.PostService;
import com.post.model.PostVO;

public class ActivityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("跳到Servlet");
		
		//顯示單一貼文
		 if ("getOne_For_Display".equals(action)) { 
	        	List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("跳進getOne_For_Display");
				try {
					/***************************1.接收請求參數  取得單一貼文**********************/
									
					String act_No = req.getParameter("act_No");
					if (act_No == null || (act_No.trim()).length() == 0) {
						errorMsgs.add("沒取到");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/post/listAllActivity.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					/***************************2.開始查詢資料*****************************************/
					ActivityService actSvc =new ActivityService();
					ActivityVO activityVO= actSvc.getOneActivity(act_No);
					if (activityVO==null){
						errorMsgs.add("查無資料");
					}
					
					if(!errorMsgs.isEmpty()){
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/activity/listAllActivity.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("activityVO", activityVO);
					String url = "/front_end/activity/listOnepost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/activity/listAllActivity.jsp");
					failureView.forward(req, res);
				}
	        	
	        }
		
		
	}
	
	public String getFileName(Part part) {
		Collection headername = part.getHeaderNames();
		Iterator it = headername.iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			String header = part.getHeader(name);
//			System.out.println(name + ":" + header);

		}
		String header = part.getHeader("content-disposition");
//		System.out.println(header);
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.isEmpty())
			return null;
		return filename;
	}

}
