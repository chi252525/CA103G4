package com.post.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.custommeals.model.CustommealsService;
import com.custommeals.model.CustommealsVO;
import com.post.model.*;
import com.reply_msg.model.ReplyService;
import com.reply_msg.model.ReplyVO;
import com.report_msg.model.ReportService;
import com.report_msg.model.ReportVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 8 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PostServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 判斷做什麼動作

		// 顯示單一貼文
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//				System.out.println("跳進getOne_For_Display");
			try {
				/*************************** 1.接收請求參數 取得單一貼文 **********************/

				String post_No = req.getParameter("post_No");
				if (post_No == null || (post_No.trim()).length() == 0) {
					errorMsgs.add("請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				PostService postSvc = new PostService();
				/*************************** 開始增加瀏覽次數 ***************************************/
				postSvc.updatePostViews(post_No);
				PostVO postVO = postSvc.getOne_Post(post_No);
				if (postVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				
			
				HttpSession session = req.getSession();
				session.setAttribute("postVO", postVO);
				String url = "/front_end/post/listOnepost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllPost.jsp");
				failureView.forward(req, res);
			}

		}

		/*************************** 修改貼文的編輯頁面 **********************************/
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String post_No = req.getParameter("post_No");
				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOne_Post(post_No);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("postVO", postVO);
				String url = "/front_end/post/update_post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************** 修改貼文 **********************************/
		if ("update".equals(action)) { // 來自update__input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// System.out.println("跳進update區塊");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String post_No = req.getParameter("post_No");
				System.out.println("post_No=" + post_No);
				String custom_Name = req.getParameter("custom_Name");
				System.out.println("custom_Name=" + custom_Name);
				
				if (post_No == null || (post_No.trim()).length() == 0) {
					errorMsgs.add("請輸入貼文編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String mem_No = req.getParameter("mem_No");
				System.out.println("接收請求參數mem_No" + mem_No);
				if (mem_No == null || (mem_No.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				String custom_No = req.getParameter("custom_No");
				if (custom_No == null || custom_No.trim().length() == 0) {
					errorMsgs.add("自訂餐點編號請勿空白");
				}

				String post_Cont = req.getParameter("post_Cont");
				if (post_Cont == null || post_Cont.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}

				Integer post_Eva = new Integer(req.getParameter("post_Eva").trim());

				java.sql.Timestamp post_Time = new java.sql.Timestamp(System.currentTimeMillis());
				byte[] post_Photo = null;
				Part part = req.getPart("post_Photo");
				try {
					String filename = getFileName(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						post_Photo = new byte[in.available()];
						in.read(post_Photo);
						in.close();
					} else {
						PostService postSvc = new PostService();
						PostVO postVO = postSvc.getOne_Post(post_No);
						post_Photo = postVO.getPost_Photo();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}

				PostVO postVO = new PostVO();
				postVO.setPost_No(post_No);
				postVO.setMem_No(mem_No);
				postVO.setCustom_No(custom_No);
				postVO.setPost_Cont(post_Cont);
				postVO.setPost_Eva(post_Eva);
				postVO.setPost_Photo(post_Photo);
				postVO.setPost_Time(post_Time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/update_post_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PostService postSvc = new PostService();
				System.out.println("開始修改資料");
				/* 同時修改餐點名稱 */
				CustommealsService custoSvc = new CustommealsService();
				custoSvc.updateNameOnly(custom_Name, custom_No);

				/* 同時修改Post */
				postVO = postSvc.updatePost(post_No, mem_No, custom_No, post_Cont, post_Eva, post_Photo, post_Time);
				System.out.println("修改完成" + postVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的VO物件,存入req
				System.out.println("req.setAttribute" + postVO);
				String url = "/front_end/post/listPostByMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/update_post_input.jsp");
				failureView.forward(req, res);
			}
		}

		/*********************** 新增貼文 *************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_No = req.getParameter("mem_No");
				System.out.println("新增 會員編號: " + mem_No);
				String custom_No = req.getParameter("custom_No");
				if (custom_No == null || custom_No.trim().length() == 0) {
					errorMsgs.add("餐點編號請勿空白");
				}
				String post_Cont = req.getParameter("post_Cont");
				if (post_Cont == null || post_Cont.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}
				Integer post_Eva = null;
				try {
					post_Eva = new Integer(req.getParameter("post_Eva").trim());
				} catch (NumberFormatException e) {
					post_Eva = 5;
					errorMsgs.add("評價請填數字1-5.");
				}

				byte[] post_Photo = null;
				Part part = req.getPart("post_Photo");
				try {
					String filename = getFileName(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						post_Photo = new byte[in.available()];
						in.read(post_Photo);
						in.close();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}
				java.sql.Timestamp post_Time = new java.sql.Timestamp(System.currentTimeMillis());
				PostVO postVO = new PostVO();
				postVO.setMem_No(mem_No);
				postVO.setCustom_No(custom_No);
				postVO.setPost_Cont(post_Cont);
				postVO.setPost_Time(post_Time);
				postVO.setPost_Photo(post_Photo);

				/*************************** 2.開始新增資料 ***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(mem_No, custom_No, post_Cont, post_Eva, post_Photo, post_Time);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/post/listAllpost.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPost.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/addPost.jsp");
				failureView.forward(req, res);
			}
		}
		/*********************** 刪除貼文 *************************/
		if ("delete".equals(action)) {
			System.out.println("delete開始");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
			String whichPage = req.getParameter("whichPage");
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String post_No = req.getParameter("post_No");
			
				/*************************** 2.開始刪除 貼文***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(post_No);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL + "?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listPostByMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getYear_and_Month_Post".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// System.out.println("跳進getYear_and_Month_Post");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String date = req.getParameter("bdaymonth");
				if (date == null || date.trim().length() == 0) {
					System.out.println("跳進errorMsgs");
					errorMsgs.add("請填寫有效日期");
				}
				System.out.println("date=" + date);
				String[] str1 = date.split("-");
				String year = str1[0];
				String month = str1[1];
				System.out.println("year=" + year + "month=" + month);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				PostService postSvc = new PostService();
				List<PostVO> list = postSvc.getYear_and_Month_Post(year, month);
				if (list == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					System.out.println("查詢失敗");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("list", list);
				System.out.println("session.setAttributelist=" + list);

				RequestDispatcher successView = req.getRequestDispatcher("/front_end/post/listPostByQuery.jsp");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
				failureView.forward(req, res);
			}

		}

		
	

		if ("keyword".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

        	System.out.println("跳進keyword");
				String keyword = req.getParameter("keyword"); // 使用者輸入的值
        	System.out.println("keyword的req.getParameter"+keyword);
				/*************************** 2.開始查詢資料 *****************************************/
				PostService postSvc = new PostService();
				List<PostVO> list = postSvc.getAllByKeywordOrderByViews(keyword.trim());
				if (list.isEmpty()) { // 如果list是空的代表沒有資料
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) { // 如果錯誤List不是空的就return
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;
				}
				HttpSession session = req.getSession();
				session.setAttribute("keyword", keyword);
				session.setAttribute("list", list); // 將list存到req中
				String url = "/front_end/post/listPostByQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
				failureView.forward(req, res);
			}

		}

		if ("orderbyViews".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("orderbyViews");
			try {
				/*************************** 2.開始查詢資料 *****************************************/
				PostService postSvc = new PostService();
				List<PostVO> list = postSvc.getAllByHot();
				if (list.isEmpty()) { // 如果list是空的代表沒有資料
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) { // 如果錯誤List不是空的就return
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
					failureView.forward(req, res);
					return;
				}
				HttpSession session = req.getSession();
				session.setAttribute("list", list); // 將list存到req中
				String url = "/front_end/post/listPostByQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/listAllpost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updatePostStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("in updatePostStatus");
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String post_No = req.getParameter("post_No");
				System.out.println("updateStatus的post_No"+post_No);
				if (post_No == null || (post_No.trim()).length() == 0) {
					errorMsgs.add("貼文編號沒取到");
				}
				String rpt_No = req.getParameter("rpt_No");
				if (rpt_No == null || (rpt_No.trim()).length() == 0) {
					errorMsgs.add(" rpt_No沒取到");
				}
				/*************************** 2.開始更新資料 *****************************************/
				PostVO postVO = new PostVO();
				postVO.setPost_No(post_No);
				/***************************修改貼文狀態******************************/
				PostService postSvc = new PostService();
				postSvc.updatePostStatus(post_No);
				System.out.println("updatePostStatus");
				/***************************同時修改Report處理狀態******************************/
				ReportService rptSvc = new ReportService();
				rptSvc.updateReportStatus(rpt_No);
				/***************************返回畫面*****************************/
				
				String url = "/back_end/report/Postreport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/report/Postreport.jsp");
				failureView.forward(req, res);
			}
				
			
			
		}

	}
	
	
	

	public String getFileName(Part part) {
	
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
//		System.out.println(header);
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename);  //測試用
		//取出副檔名
		String fnameExt = filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
		System.out.println("fnameExt=" + fnameExt);  //測試用
		if (filename.length() == 0) {
			return null;
		}
		return fnameExt;
	}
}
