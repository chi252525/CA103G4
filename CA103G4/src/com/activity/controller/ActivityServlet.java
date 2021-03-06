package com.activity.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.member.model.MemberVO;
import com.post.model.PostService;
import com.post.model.PostVO;

import android.com.main.Send;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;


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
		String ad_PicReg  = "^(jpeg|jpg|bmp|png|gif|ico)$";
		System.out.println("跳到Servlet"+action);
//		Enumeration en=req.getAttributeNames();
//		while(en.hasMoreElements()) {
//			System.out.println(en.nextElement());
//		}
		//顯示單一貼文front_end
		 if ("getOne_For_Display".equals(action)) { 
	        	List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數  取得單一貼文**********************/
									
					String act_No = req.getParameter("act_No");
					if (act_No == null || (act_No.trim()).length() == 0) {
						errorMsgs.add("沒取到");
					}
				
					/***************************2.開始查詢資料*****************************************/
					ActivityService actSvc =new ActivityService();
					actSvc.updateAct_Views(act_No);
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
					String url = "/front_end/activity/listOneActivity.jsp";
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
		 //back_end
		   if ("insert".equals(action)) { 
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {
				String act_Name = req.getParameter("act_Name");
//				System.out.println(act_Name);
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
					String filename = getFileNameFromPart(part1);
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
					String filename = getFileNameFromPart(part2);
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
				
				java.sql.Timestamp act_PreAddTime = new Timestamp(System.currentTimeMillis()); 
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
			    try {
			    java.util.Date act_PreAddTimestr = dateFormat.parse(req.getParameter("act_PreAddTime").trim());
			    act_PreAddTime = new java.sql.Timestamp(act_PreAddTimestr.getTime());
//			    System.out.println("act_PreAddTime"+act_PreAddTime);
			    }catch(ParseException e) {
			    	e.printStackTrace();

			    }
				
				
			    java.sql.Timestamp act_PreOffTime = new Timestamp(System.currentTimeMillis()); 
				
			    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd kk:mm");
			    try {
			    java.util.Date act_PreOffTimestr = dateFormat1.parse(req.getParameter("act_PreOffTime").trim());
//			    System.out.println("act_PreOffTimestr"+act_PreOffTimestr);
			    act_PreOffTime = new java.sql.Timestamp(act_PreOffTimestr.getTime());
			    }catch(ParseException e) {
			    	e.printStackTrace();

			    }
				
				java.sql.Timestamp act_Start = act_PreAddTime;
//				System.out.println("act_PreAddTime"+act_PreAddTime);
				java.sql.Timestamp act_End = act_PreOffTime;
//				System.out.println("act_PreOffTime"+act_PreOffTime);
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
				activityVO.setAct_End(act_End);
				/*************************** 2.開始新增資料 ***************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO=actSvc.addActivity(coucat_No,act_Cat,
						act_Name,act_Carousel,act_Pic,act_Content,act_PreAddTime,act_PreOffTime,
						act_Start,act_End);
			
				/***************************把新增一筆的廣告加入context中***********************************************/
				ServletContext context = getServletContext();
				List<ActivityVO> actloadlist=(List<ActivityVO>)context.getAttribute("actloadlist");
				actloadlist.add(actVO);
				context.setAttribute("actloadlist", actloadlist);
				System.out.println("actloadlist size()"+actloadlist.size());;
				
				// 活動新增成功時發送簡訊給所有會員
				Send se = new Send();
				MemberDAO_interface mdao = new MemberDAO();
				List<MemberVO> memlist= mdao.getAll();
				System.out.println("gggg");
				for(MemberVO memVO:memlist){
					String[] tel = {memVO.getMem_Phone()};
					StringBuilder message = new StringBuilder()
							.append("親愛的會員")
							.append(memVO.getMem_Name().toString())
							.append("，您好:")
				 			.append("竹風堂最新活動預告，從")
				 			.append(activityVO.getAct_Start().toString().substring(0, 16))
				 			.append("到")
				 			.append(activityVO.getAct_End().toString().substring(0, 16))
				 			.append(activityVO.getAct_Name().toString())
				 			.append("別錯過囉!");
				 			
				 	se.sendMessage(tel , message.toString());
				 
				}
				System.out.println("gggg");
			
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/activity/listAllActivity.jsp";
				req.setAttribute("display", "display");
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
			   System.out.println("listActs_ByCompositeQuery in");
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					Map<String, String[]> map = req.getParameterMap();
//					System.out.println("map"+map);
					/***************************2.開始複合查詢***************************************/
					ActivityService actSvc = new ActivityService();
					List<ActivityVO> list  = actSvc.getAll(map);
					System.out.println("list"+list);
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					HttpSession session= req.getSession();
					session.setAttribute("listActs_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					//Bootstrap_modal
					boolean openModal=true;
					System.out.println("openModal"+openModal);
					req.setAttribute("openModal",openModal);
					RequestDispatcher successView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp"); 
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
					failureView.forward(req, res);
				}
			}
		   
		   
		   //馬上上架/下架
		   if("RightNow_UpdateStat".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
//				System.out.println("RightNow_UpdateStat in");
			
				try {
					/*****************第一步：接受請求參數****************************/
					String act_No = req.getParameter("act_No");
//					System.out.println("act_No="+act_No);
					String act_Status =req.getParameter("act_Status");
					System.out.println("act_Status="+act_Status);
					System.out.println();
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
					
					
					/*********************如果是上架改下架就從客廳移除掉************************/
					ActivityService actSvc =new ActivityService();
					if(act_Status.equals("1")) {
					ServletContext context = getServletContext();
					ActivityVO oldactVO=actSvc.getOneActivity(act_No);
					@SuppressWarnings("unchecked")
					List<ActivityVO> actloadlist=(List<ActivityVO>)context.getAttribute("actloadlist");
					Iterator<ActivityVO> actVOs=actloadlist.iterator();
					System.out.println("RightNow_UpdateStat 裡的 actloadlist remove前"+actloadlist.size());
					ActivityVO removeElem=null;
					while (actVOs.hasNext()) {
//						ActivityVO actVO=(ActivityVO) actVOs.next();
						removeElem=oldactVO;
						 if(removeElem.equals(actVOs.next())){
							 actVOs.remove();
				            }
						
					}
					System.out.println("RightNow_UpdateStat 裡的 actloadlist remove後"+actloadlist.size());
					}
					
					/*****************第二步：立即更動廣告狀態****************************/
					ActivityVO activityVO=actSvc.getOneActivity(act_No);
					actSvc.updateAct(act_No, Integer.valueOf(act_Status),activityVO);
					
					
					
					
					/*****************第三步：更新完成，準備轉交**************************/
					if(act_Status.equals("1")) {
						req.setAttribute("display", "display");
						
					}
					
					RequestDispatcher successView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
					successView.forward(req, res);
					
				}catch(Exception e ) {
					errorMsgs.add("發生錯誤:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
					failureView.forward(req, res);
				}
			}
		   
		 //修改廣告資訊時，會跳轉到修改頁面
			if("getOne_For_Update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL");
				
				try {
					//*******************第一步：接受請求參數*******************
					String act_No = req.getParameter("act_No");
					/*****************第二步：取出廣告資料****************************/
					ActivityService actSvc = new ActivityService();
					ActivityVO activityVO = actSvc.getOneActivity(act_No);
					/*****************第三步：查詢完成，準備轉交****************************/
					
					req.setAttribute("activityVO", activityVO);
					String url="/back_end/activity/back_activity_updated.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					
				}catch(Exception e){
					errorMsgs.add(e.getMessage());
					System.out.println("error");
					RequestDispatcher filureView = req.getRequestDispatcher(requestURL);
					filureView.forward(req, res);
				}
				
			}
			
			if("update".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
//				System.out.println("update in");
				Timestamp act_PreAddTime = null ;
				Timestamp act_PreOffTime = null ;
				String act_No =null;
				try {
					String act_Status = req.getParameter("act_Status");
					act_No = req.getParameter("act_No");
					System.out.println("act_No"+act_No);
					if(act_No == null || act_No.trim().length() == 0) {
						errorMsgs.add("未取得貼文");
					}
					String act_Name = req.getParameter("act_Name");
					if(act_Name == null || act_Name.trim().length() == 0) {
						errorMsgs.add("標題：請勿空白");
					}
					
					String act_Cat = req.getParameter("act_Cat");
					if(act_Cat == null || act_Cat.trim().length() == 0) {
						errorMsgs.add("請選擇廣告分類");
					}
					
					String coucat_No = req.getParameter("coucat_No");
					if(coucat_No == null || coucat_No.trim().length() == 0) {
						errorMsgs.add("請選擇對應宣傳的優惠卷");
					}
					
					String act_Content = req.getParameter("act_Content");
					if(act_Content == null || act_Content.trim().length() == 0) {
						errorMsgs.add("內容請勿空白");
					}
					
					
					
					byte[] act_Carousel = null;
					Part part = req.getPart("act_Carousel");
				try {
						String filename = getFileNameFromPart(part);
						if (filename != null && part.getContentType() != null) {
							InputStream in = part.getInputStream();
							act_Carousel = new byte[in.available()];
							in.read(act_Carousel);
							in.close();
						} else {
							ActivityService actSvc = new ActivityService();
							ActivityVO advo_DB = actSvc.getOneActivity(act_No);
							act_Carousel = advo_DB.getAct_Carousel();
						}
					} catch (FileNotFoundException fe) {
						fe.printStackTrace();
					}
					
					byte[] act_Pic = null;
					Part part2 = req.getPart("act_Pic");
					try {
						String filename2 = getFileNameFromPart(part2);
						if (filename2 != null && part2.getContentType() != null) {
							InputStream in = part2.getInputStream();
							act_Pic = new byte[in.available()];
							in.read(act_Pic);
							in.close();
						} else {
							ActivityService actSvc = new ActivityService();
							ActivityVO advo_DB = actSvc.getOneActivity(act_No);
							act_Pic = advo_DB.getAct_Pic();
						}
					} catch (FileNotFoundException fe) {
						fe.printStackTrace();
					}

					
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				    try {
				    String	act_PreAddTimestr=req.getParameter("act_PreAddTime");
				    if(act_PreAddTimestr == null ||act_PreAddTimestr.trim().length() == 0){
						errorMsgs.add("預計上架時間：請勿空白。");
					}else {
				    java.util.Date act_PreAddTimedate = dateFormat.parse(act_PreAddTimestr.trim());
				    act_PreAddTime = new java.sql.Timestamp(act_PreAddTimedate.getTime());
					}
				    System.out.println("act_PreAddTime"+act_PreAddTime);
				    }catch(ParseException e) {
				    	e.printStackTrace();
				    }
					
				

				    try {
				    String	act_PreOffTimestr=req.getParameter("act_PreOffTime");
				    if(act_PreOffTimestr == null ||act_PreOffTimestr.trim().length() == 0){
						errorMsgs.add("預計下架時間：請勿空白。");
					}else {
				    java.util.Date act_PreOffTimedate = dateFormat.parse(act_PreOffTimestr.trim());
				    act_PreOffTime = new java.sql.Timestamp(act_PreOffTimedate.getTime());
					}
				    System.out.println("act_PreOffTimestr"+act_PreOffTimestr);
				    }catch(ParseException e) {
				    	e.printStackTrace();
				    }

//					//預定上架時間與下架時間有輸入的判斷
//					if(preAdd != null && preOff !=null) {
//						if(preAdd.getTime() >= preOff.getTime()) {
//							errorMsgs.add("請修改上架時間：不得大於等於下架時間");
//						}
//					}
				    
				    
				    
					ActivityVO activityVO= new ActivityVO();
					activityVO.setAct_No(act_No);
					activityVO.setAct_Cat(act_Cat);
					activityVO.setAct_Name(act_Name);
					activityVO.setCoucat_No(coucat_No);
					activityVO.setAct_Content(act_Content);
					activityVO.setAct_Carousel(act_Carousel);
					activityVO.setAct_Pic(act_Pic);
					activityVO.setAct_PreAddTime(act_PreAddTime);
					activityVO.setAct_PreOffTime(act_PreOffTime);
					//以上驗證有錯誤訊息的判斷
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("activityVO", activityVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/activity/back_activity_updated.jsp");
						failureView.forward(req, res);
						return;
					}
					/*********************先從客廳移掉舊的VO*************************/
					ActivityService actSvc =new ActivityService();
					ServletContext context = getServletContext();
					ActivityVO oldactVO=actSvc.getOneActivity(act_No);
					@SuppressWarnings("unchecked")
					List<ActivityVO> actloadlist=(List<ActivityVO>)context.getAttribute("actloadlist");
					Iterator<ActivityVO> actVOs=actloadlist.iterator();
					System.out.println("update 裡的 actloadlist remove前"+actloadlist.size());
					ActivityVO removeElem=null;
					while (actVOs.hasNext()) {
//						ActivityVO actVO=(ActivityVO) actVOs.next();
						removeElem=oldactVO;
						 if(removeElem.equals(actVOs.next())){
							 actVOs.remove();
				            }
					
						
					}
			
				
					context.setAttribute("actloadlist", actloadlist);
					System.out.println("update 裡的 actloadlist remove後"+actloadlist.size());
					//************************第二步：再修改資料**************************
					ActivityVO actVO= actSvc.updateActivity(act_No,coucat_No,act_Cat,
							act_Name,act_Carousel,act_Pic,act_Content,act_PreAddTime,
							act_PreOffTime);
					if(act_Status.equals("0")) {
						req.setAttribute("display", "display");
					}
					
					/***************************再把修改一筆的廣告加入context中***********************************************/
					
					actloadlist.add(actVO);
					context.setAttribute("actloadlist", actloadlist);
					System.out.println("actloadlist size()"+actloadlist.size());;
					
					//************************第三步：新增完成，準備提交**************************
					//Bootstrap_modal
					boolean openModal_forupdate=true;
					System.out.println("openModal_forupdate"+openModal_forupdate);
					req.setAttribute("openModal_forupdate",openModal_forupdate);
					req.setAttribute("activityVO", activityVO); 
					String url = "/back_end/activity/listAllActivity.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				}catch(Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher filureView = req.getRequestDispatcher("/back_end/activity/back_activity_updated.jsp");
					filureView.forward(req, res);
				}			
			}
			
			
		if("findbyactcata".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("跳進findbyactcata");
			try {
				/***********************1.接收請求參數***********************************************/
				String act_Cat= req.getParameter("act_Cat");
//				System.out.println("act_Cat"+act_Cat);
				if(act_Cat == null || act_Cat.trim().length() == 0) {
					errorMsgs.add("未接受到優惠卷類別參數");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/activity/listAllActivity.jsp");
					failureView.forward(req, res);
					return;
				}
				/*********************2取出資料**********************************************************/
				ActivityService actSvc= new ActivityService();
				List<ActivityVO> actlist=actSvc.getByAct_Cata(act_Cat);
//				System.out.println("actlist"+actlist);
				/*******************3.查詢完成轉被轉交**************************************************/
				req.setAttribute("actlist", actlist);
				String url="/front_end/activity/listAllActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher filureView = req.getRequestDispatcher("/front_end/activity/listAllActivity.jsp");
				filureView.forward(req, res);
			}
			
			
			
			
		}
		
		
		if ("delete".equals(action)) {
			System.out.println("delete開始");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
			try {
				/*************************** 1.接收請求參數 ***************************************/
				
				String act_No = req.getParameter("act_No");
			
				/*************************** 2.開始刪除 貼文***************************************/
				ActivityService actSvc= new ActivityService();
				actSvc.deleteAct(act_No);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//取出副檔名
		String fnameExt = filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
		if (filename.length() == 0) {
			return null;
		}
		return fnameExt;
	}
	

}
