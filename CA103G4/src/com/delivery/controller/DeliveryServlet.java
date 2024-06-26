package com.delivery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.delivery.model.DeliveryService;
import com.delivery.model.DeliveryVO;
import com.employee.model.EmpVO;
import com.orderform.model.OrderformService;
import com.orderform.model.OrderformVO;


/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String dn;
	String en;
	String ds;

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

				String strdReg = "^D[-]{1}[0-9]{9}$";
				String streReg = "^E{1}[0-9)]{9}$";
				String strsReg = "^[123]$";

				// 這邊是為了分頁進來時的空值問題，避免下面執行時Exception
				if (strd == null) {
					strd = "";
				}
				if (stre == null) {
					stre = "";
				}
				if (strs == null) {
					strs = "";
				}
				//

				if (strd.trim().length() != 0 && !strd.matches(strdReg))
					errorMsgs.add("請檢查派送單編號的格式是否正確。");
				if (stre.trim().length() != 0 && !stre.matches(streReg))
					errorMsgs.add("請檢查員工編號的格式是否正確。");
				if (strs.trim().length() != 0 && !strs.matches(strsReg))
					errorMsgs.add("請檢查派送單狀態的格式是否正確。");

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/delivery/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DeliveryService delSvc = new DeliveryService();
				List<DeliveryVO> delVOList = delSvc.getSelect(strd, stre, strs);

				if (req.getAttribute("deliv") != null && req.getAttribute("emp") != null && req.getAttribute("status") != null) {

					delVOList = delSvc.getSelect((String) req.getAttribute("deliv"), (String) req.getAttribute("emp"),
							(String) req.getAttribute("status"));

				} else if (req.getParameter("whichPage") != null) {
					delVOList = delSvc.getSelect(dn, en, ds);
				} else {
					dn = strd;
					en = stre;
					ds = strs;
				}
				
				

//				if (delVOList.isEmpty()) {
//					errorMsgs.add("查無資料");
//				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/delivery/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.getSession().setAttribute("get_By_Key", delVOList);

				String url = "/back_end/delivery/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/delivery/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 更新派送單狀態

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String emp = new String(req.getParameter("emp_no"));
			String status = new String(req.getParameter("deliv_status"));
			String deliv = new String(req.getParameter("deliv_no"));

			if ("2".equals(status)) {
				status = "3";
			}

			if ("1".equals(status)) {
				status = "2";
			}

			DeliveryVO delVO = new DeliveryVO();
			delVO.setEmp_no(emp);
			delVO.setDeliv_status(status);
			delVO.setDeliv_no(deliv);

			/*************************** 2.開始修改資料 ***************************************/
			DeliveryService delSvc = new DeliveryService();
			delVO = delSvc.updateDelivery(emp, status, deliv);
			
			OrderformService ordSvc = new OrderformService();
			
			if ("3".equals(status)) {

				ordSvc.update(deliv);

			}
			
			deliv = dn;
			emp = en;
			status = ds;

			/*************************** 3.,準備轉交(Send the Success view) ***********/
			req.setAttribute("update", delVO);
			req.setAttribute("deliv", deliv);
			req.setAttribute("emp", emp);
			req.setAttribute("status", status);
			
			
			

			String url = "/front_end/delivery/delivery.do?action=get_By_Key";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {
			EmpVO empVO = (EmpVO) req.getSession().getAttribute("empVO");//綁定登入員工的分店編號
			String branchno = empVO.getBranch_No();
//			test
//				String branchno = "0001";

				OrderformVO ord = null;

				List<OrderformVO> list = new ArrayList<OrderformVO>();
				String[] ordlist = req.getParameterValues("ordno");// name jsp

				for (int i = 0; i < ordlist.length; i++) {
					ord = new OrderformVO();
					ord.setOrder_no(ordlist[i]);
					list.add(ord);
				}
				
//			test
//			OrderformVO vo1 = new OrderformVO();
//			vo1.setOrder_no("O000000001");
//			OrderformVO vo2 = new OrderformVO();
//			vo2.setOrder_no("O000000002");
//			OrderformVO vo3 = new OrderformVO();
//			vo3.setOrder_no("O000000003");
//			OrderformVO vo4 = new OrderformVO();
//			vo4.setOrder_no("O000000004");
//			OrderformVO vo5 = new OrderformVO();
//			vo5.setOrder_no("O000000005");
//			list.add(vo1);
//			list.add(vo2);
//			list.add(vo3);
//			list.add(vo4);
//			list.add(vo5);

				DeliveryVO delVO = new DeliveryVO();
				delVO.setBranch_no(branchno);

				/*************************** 2.開始新增資料 ***************************************/
				DeliveryService delSvc = new DeliveryService();
				delVO = delSvc.addDelivery(delVO, list);

				req.setAttribute("insert", delVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/delivery/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (RuntimeException e) {
				errorMsgs.add("請選擇需要派送的訂單或回到派送單頁面。");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/delivery/delivery.do?action=selectOrd");
				failureView.forward(req, res);
			}
		}

		if ("listAllDelivery".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				/*************************** 2.開始查詢資料 ****************************************/
				DeliveryService deltSvc = new DeliveryService();
				List<DeliveryVO> list = deltSvc.getAll();

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllDelivery", list); // 資料庫取出的set物件,存入request

				String url = null;
				if ("listAllDelivery".equals(action))
					url = "/back_end/delivery/select_page.jsp"; // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("listNotOk".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				/*************************** 2.開始查詢資料 ****************************************/
				DeliveryService deltSvc = new DeliveryService();
				List<DeliveryVO> list = deltSvc.getNotOk();

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listNotOk", list); // 資料庫取出的set物件,存入request

				String url = null;
				if ("listNotOk".equals(action))
					url = "/back_end/delivery/select_page.jsp"; // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("selectOrd".equals(action)) {

			try {
				/*************************** 1.接收請求參數 ****************************************/

				/*************************** 2.開始查詢資料 ****************************************/
				OrderformService ordSvc = new OrderformService();
				List<OrderformVO> list = ordSvc.getDeliv();

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("selDel", list); // 資料庫取出的set物件,存入request

				String url = "/back_end/delivery/choseord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}

}
