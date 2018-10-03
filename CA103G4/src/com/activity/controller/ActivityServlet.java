package com.activity.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 8 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActivityServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 判斷做什麼動作

		System.out.println("跳到Servlet"+action);
		Enumeration en=req.getAttributeNames();
		while(en.hasMoreElements()) {
			System.out.println(en.nextElement());
		}
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
		   if ("insert".equals(action)) { 
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("OK");
			try {
				String act_Name = req.getParameter("act_Name");
				System.out.println(act_Name);
				if (act_Name == null || act_Name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				}
				String coucat_No= req.getParameter("coucat_No");
				if(coucat_No==null) {
					errorMsgs.add("請選擇對應優惠卷類別");
				}
				String act_Cat= req.getParameter("act_Cat");
				if(act_Cat==null) {
					errorMsgs.add("請選擇廣告類別");
				}
	
				byte[] act_Carousel = null;
				Part part1 = req.getPart("act_Carousel");
				try {
					String filename = getFileName(part1);
					if (filename != null && part1.getContentType() != null) {
						InputStream in = part1.getInputStream();
						act_Carousel = new byte[in.available()];
						in.read(act_Carousel);
						in.close();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}
				byte[] act_Pic = null;
				Part part2 = req.getPart("act_Pic");
				try {
					String filename = getFileName(part2);
					if (filename != null && part2.getContentType() != null) {
						InputStream in = part2.getInputStream();
						act_Pic = new byte[in.available()];
						in.read(act_Pic);
						in.close();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}
				String act_Content = req.getParameter("act_Content");
				if (act_Content == null || act_Content.trim().length() == 0) {
					errorMsgs.add("活動內容: 請勿空白");
				}
				
				java.sql.Timestamp act_PreAddTime = null;
				try {
					act_PreAddTime = java.sql.Timestamp.valueOf(req.getParameter("act_PreAddTime").trim());
				} catch (IllegalArgumentException e) {
					act_PreAddTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				java.sql.Timestamp act_PreOffTime = null;
				try {
					act_PreOffTime = java.sql.Timestamp.valueOf(req.getParameter("act_PreOffTime").trim());
				} catch (IllegalArgumentException e) {
					act_PreOffTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp act_Start = new java.sql.Timestamp(System.currentTimeMillis());
				java.sql.Timestamp act_End = act_PreOffTime;
						
				ActivityVO activityVO = new ActivityVO();
				activityVO.setCoucat_No(coucat_No);
				activityVO.setAct_Cat(act_Cat);
				activityVO.setAct_Name(act_Name);
				activityVO.setAct_Carousel(act_Carousel);
				activityVO.setAct_Pic(act_Pic);
				activityVO.setAct_Content(act_Content);
				activityVO.setAct_PreAddTime(act_PreAddTime);
				activityVO.setAct_PreOffTime(act_PreOffTime);
				activityVO.setAct_Start(act_Start);
		
				/*************************** 2.開始新增資料 ***************************************/
				ActivityService actSvc = new ActivityService();
				actSvc.addActivity(coucat_No,act_Cat,
						act_Name,act_Carousel,act_Pic,act_Content,act_PreAddTime,act_PreOffTime ,
						act_Start,act_End);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/activity/listAllActivity.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPost.jsp
				successView.forward(req, res);
				
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/activity/addAct.jsp");
				failureView.forward(req, res);
			}
			 
			 
			 
		 }
		   
		   
		   if ("listActs_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
					@SuppressWarnings("unchecked")
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					System.out.println("map"+map);
					if (req.getParameter("whichPage") == null){
						HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
						session.setAttribute("map",map1);
						map = map1;
					} 
					
					/***************************2.開始複合查詢***************************************/
					ActivityService actSvc = new ActivityService();
					List<ActivityVO> list  = actSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listActs_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/back_end/activity/listActs_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
					failureView.forward(req, res);
				}
			}
		   
		   
		   
		   if("RightNow_UpdateStat".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/*****************第一步：接受請求參數****************************/
					String act_No = req.getParameter("act_No");
					String act_Status =req.getParameter("act_Status");
					
					if(act_No == null || act_No.trim().length() == 0) {
						errorMsgs.add("未接受到廣告No參數");
					}
					if(act_Status == null || act_Status.trim().length() == 0) {
						errorMsgs.add("未接受到廣告狀態要上架還是下架的參數");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
						failureView.forward(req, res);
						return;
					}
					/*****************第二步：立即更動廣告狀態****************************/
					ActivityService actSvc = new ActivityService();
					ActivityVO activityVO=actSvc.getOneActivity(act_No);
					actSvc.updateAct(act_No, Integer.valueOf(act_Status),activityVO);
					
					/*****************第三步：更新完成，準備轉交**************************/
					if(act_Status.equals("1")) {
						req.setAttribute("display_tabs", "display");
					}
					
					RequestDispatcher successView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
					successView.forward(req, res);
					
				}catch(Exception e ) {
					errorMsgs.add("發生錯誤:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
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
