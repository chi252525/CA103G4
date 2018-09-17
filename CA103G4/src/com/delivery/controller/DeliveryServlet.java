package com.delivery.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.model.DeliveryService;
import com.delivery.model.DeliveryVO;

/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("get_By_Key".equals(action)) { // getOne_For_Display >> get_By_Key
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String strd = req.getParameter("deliv_no");
				String stre = req.getParameter("emp_no");
				String strs = req.getParameter("deliv_status");

				String strdReg = "^[(D0-9-)]{11,11}$";
				String streReg = "^[(E0-9)]{10,10}$";
				String strsReg = "^[(1-2)]{1,1}$";

				if (strd != strdReg) {
					errorMsgs.add("請檢查派送單編號的格式是否正確。");
//						break;
				} else if (stre != streReg) {
					errorMsgs.add("請檢查員工編號的格式是否正確。");
//						break;
				} else if (strs != strsReg) {
					errorMsgs.add("請檢查派送單狀態的格式是否正確。");
//						break;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/delivery/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DeliveryService delSvc = new DeliveryService();
				List<DeliveryVO> delVO = delSvc.getSelect(strd, stre, strs);
				if (delVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/delivery/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("delVO", delVO);
				String url = "/emp/select_page.jsp"; // !!!!!!!!!!!!!!!!這裡要改
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/delivery/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 更新派送單狀態

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String deliv = new String(req.getParameter("deliv_no"));
				String status = null;

				status = new String(req.getParameter("deliv_status").trim());
				if ("1".equals(status)) {
					status = "2";
				} else if ("2".equals(status)) {
					status = "3";
				}

				String emp = null;

				if ("1".equals(status)) {
					emp = new String(req.getParameter("emp_no").trim());
				}

				/*************************** 2.開始修改資料 ***************************************/
				DeliveryVO delVO = new DeliveryVO();
				DeliveryService delSvc = new DeliveryService();
				delVO = delSvc.updateDelivery(emp, status, deliv);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("delVO", delVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/delivery/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/delivery/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { //
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String branchno = new String(req.getParameter("branch_no").trim());
				String emp = null;
				String status = null;

				/*************************** 2.開始新增資料 ***************************************/
				DeliveryVO delVO = new DeliveryVO();
				DeliveryService delSvc = new DeliveryService();
				delVO = delSvc.addDelivery(branchno, emp, status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/delivery/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/delivery/select_page.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
