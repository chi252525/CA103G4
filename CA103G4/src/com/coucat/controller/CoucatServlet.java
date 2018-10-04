package com.coucat.controller;
import java.io.ByteArrayOutputStream;
import java.io.File;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import com.coucat.model.CoucatService;

import android.com.coucat.model.CoucatVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 8 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CoucatServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 判斷做什麼動作	
		String PicReg  = "^(jpeg|jpg|bmp|png|gif|ico)$";//圖檔的格式
		
		
		if ("insert".equals(action)) { 
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {
				String coucat_Name = req.getParameter("coucat_Name");
				System.out.println(coucat_Name);
				if(coucat_Name == null || coucat_Name.trim().length() == 0) {
					errorMsgs.add("標題：請勿空白。");
				}
				String coucat_Cata= req.getParameter("coucat_Cata");
				if(coucat_Cata==null) {
					errorMsgs.add("請選擇對應優惠卷類別");
				}

				byte[] coucat_Pic = null;
				Part part = req.getPart("coucat_Pic");
				try {
					String filename = getFileName(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						coucat_Pic = new byte[in.available()];
						in.read(coucat_Pic);
						in.close();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}
				
				
				String str1 = req.getParameter("coucat_Amo");
				Integer coucat_Amo = null;
				try {
					coucat_Amo = new Integer(str1.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("數量請填數字整數");
				}
				
				String str2 = req.getParameter("coucat_Value");
				Integer coucat_Value = null;
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("coucat_Value 請輸入折扣價格");
				}else {
				try {
					coucat_Value = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("coucat_Value 折扣價格格式不正確");
				}}

				java.sql.Timestamp coucat_Valid = new Timestamp(System.currentTimeMillis()); 
				String coucat_Validstr=req.getParameter("coucat_Valid").trim();
				System.out.println("coucat_Validstr"+coucat_Validstr);
				try {  
					coucat_Valid = Timestamp.valueOf(coucat_Validstr);  
		            System.out.println(coucat_Valid);  
		        } catch (IllegalArgumentException e) {  
		        	coucat_Valid=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入生效日期!");
		        }  
		
				java.sql.Timestamp coucat_Invalid = new Timestamp(System.currentTimeMillis()); 
				String coucat_Invalidstr=req.getParameter("coucat_Invalid").trim();
				System.out.println("coucat_Invalidstr"+coucat_Invalidstr);
				try {  
					coucat_Invalid = Timestamp.valueOf(coucat_Invalidstr);  
		            System.out.println(coucat_Invalid);  
		        } catch (IllegalArgumentException e) {  
		        	coucat_Invalid=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入生效日期!");
		        }  
				
				
				//若預定生效時間與失效時間不符合的情況
				if(coucat_Valid != null && coucat_Invalid !=null) {
					if(coucat_Valid.getTime() >= coucat_Invalid.getTime()) {
						errorMsgs.add("請修改生效時間：不得大於等於失效時間");
					}
				}
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/addCoupon.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Double coucat_Discount=0.0;
				Integer coucat_Freep= 0;
				CoucatVO coucatVO = new CoucatVO();
				coucatVO.setCoucat_Name(coucat_Name);
				coucatVO.setCoucat_Cata(coucat_Cata);
				coucatVO.setCoucat_Value(coucat_Value);
				coucatVO.setCoucat_Valid(coucat_Valid);
				coucatVO.setCoucat_Pic(coucat_Pic);
				coucatVO.setCoucat_Invalid(coucat_Invalid);
				coucatVO.setCoucat_Amo(coucat_Amo);
				/*************************** 2.開始新增資料 ***************************************/
				CoucatService couSvc = new CoucatService();
				couSvc.addCoucat(coucat_Name,coucat_Cata,coucat_Value,coucat_Discount,
						coucat_Freep,coucat_Valid,coucat_Invalid,coucat_Amo,coucat_Pic);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/activity/listAllActivity.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/activity/addAct.jsp");
				failureView.forward(req, res);
			}
		
		}
	
	
	
	
	}
	
	public String getFileName(Part part) {
		
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
//		System.out.println(header);
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename);  //測試用
		//取出副檔名
		String fnameExt = filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
//		System.out.println("fnameExt=" + fnameExt);  //測試用
		if (filename.length() == 0) {
			return null;
		}
		return fnameExt;
	}
	}
