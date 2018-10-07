package com.storedrecord.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
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

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.storedrecord.model.StoredrecordService;
import com.storedrecord.model.StoredrecordVO;

/**
 * Servlet implementation class storedrecordServlet
 */
@WebServlet("/front_end/storedrecord/storedrecord.do")
public class StoredrecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		// ==================查單筆儲值紀錄=================
		if ("findByPrimaryKey".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// ==================輸入檢驗====================
				String stor_No = req.getParameter("stor_No");
				String mem_No = req.getParameter("mem_No");
				String regexMem = "^M\\d{6}$";
				String regexStor = "^B\\d{9}$";
				Boolean x = stor_No.matches(regexStor);// test for regular expression
				if (stor_No == null || stor_No.trim().length() == 0) {
					errorMsgs.add("請輸入儲值流水單號");
				} else if (!mem_No.matches(regexMem)) {
					errorMsgs.add("格式錯誤:會員編號格式必須是大寫英文字母M加上5個數字");
				} else if (!stor_No.matches(regexStor)) {
					errorMsgs.add("儲值流水單號必須是大寫英文字母B加上9個數字");
				}
				if (!errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/front_end/storedrecord/transaction_query.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

				// ===================開始查詢=====================
				StoredrecordService srSv = new StoredrecordService();
				StoredrecordVO srVO = srSv.getOneStoredrecord(stor_No);
				if (srVO == null) {
					errorMsgs.add("查無資料");
					req.getRequestDispatcher("/front_end/storedrecord/select_page.jsp").forward(req, res);
					return;// 有錯誤,返回
				}
				// error display...
				/* ==================轉交查詢結果====================== */
				req.setAttribute("srVO", srVO);
				req.getRequestDispatcher("/front_end/storedrecord/listOneStoredrecord.jsp").forward(req, res);

				// ====================錯誤處理===========================
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				req.getRequestDispatcher("/front_end/storedrecord/select_page.jsp").forward(req, res);
			}
		}
//			==================查點擊的儲值紀錄====================
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("error", errorMsgs);
			try {
				String stor_No = req.getParameter("stor_No");

				// 查點擊的儲值紀錄
				StoredrecordService stsvc = new StoredrecordService();
				StoredrecordVO stvo = stsvc.getOneStoredrecord(stor_No);
				req.setAttribute("StoredrecordVO", stvo);
				req.getRequestDispatcher("/front_end/storedrecord/update_storedrecord_input.jsp").forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		// =================修改單筆儲值紀錄=======================
		if ("Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("error", errorMsgs);
			try {
				String stor_No = req.getParameter("stor_No");

				String regexStor = "^B\\d{9}$";
				if (stor_No == null || stor_No.trim().length() == 0) {
					errorMsgs.add("請輸入儲值流水單號");
				} else if (!stor_No.matches(regexStor)) {
					errorMsgs.add("儲值流水單號必須是大寫英文字母A-Z加上9個數字");
				}

				String mem_No = req.getParameter("mem_No").trim();
				String regexMem = "^M\\d{6}$";

				if (stor_No == null || stor_No.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				} else if (!stor_No.matches(regexMem)) {
					errorMsgs.add("會員編號必須是大寫英文字母A-Z加上5個數字");
				}

				Timestamp stor_Date = null;
				try {
					stor_Date = Timestamp.valueOf(req.getParameter("stor_Date").trim());
				} catch (IllegalArgumentException ie) {

					errorMsgs.add("請選取日期");
				}
				Integer stor_Point = null;
				try {
					stor_Point = new Integer(req.getParameter("stor_Point").trim());
				} catch (NumberFormatException ne) {
					errorMsgs.add("請輸入數字");
				}

				Integer drew_Point = null;
				try {
					drew_Point = Integer.parseInt(req.getParameter("drew_Point").trim());
				} catch (NumberFormatException ne) {
					errorMsgs.add("請輸入數字");
				}

				Integer stor_Status = null;
				try {
					stor_Status = Integer.parseInt(req.getParameter("stor_Status").trim());
					if (stor_Status != 1 || stor_Status != 0) {
						stor_Status = 0;
						errorMsgs.add("請輸入1或0");
					}
				} catch (NumberFormatException ne) {
					stor_Status = 0;
					errorMsgs.add("請輸入1或0");
				}

				StoredrecordVO srVO = new StoredrecordVO();
				srVO.setStor_No(stor_No);
				srVO.setMem_No(mem_No);
				srVO.setStor_Date(stor_Date);
				srVO.setStor_Point(stor_Point);
				srVO.setDrew_Point(drew_Point);
				srVO.setStor_Status(stor_Status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storedrecordVO", srVO);
					req.getRequestDispatcher("/front_end/storedrecord/update_storedrecord_input.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

//				======================開始修改=========================
				StoredrecordService stsvc = new StoredrecordService();
				StoredrecordVO srVo = stsvc.updateStoredrecord(stor_No, mem_No, stor_Date, stor_Point, drew_Point,
						stor_Status);

				// ================改完，轉交===================
				req.setAttribute("storedrecordVO", srVo);
				req.getRequestDispatcher("front-end/storedrecord/listOneStoredrecord.jsp").forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/storedrecord/update_storedrecord_input.jsp").forward(req, res);
			}
		}
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String mem_No = req.getParameter("mem_No");
				String card_number = null;
				String full_name = null;
				String expiry = null;
				String cvc = null;
				// 信用卡資料判斷
				card_number = req.getParameter("number");// 取得卡號
				String card_number_regx = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";

				if (card_number == null || card_number.trim().length() == 0) {
					errorMsgs.put("card_number", "請輸入卡號");

				} else if (!card_number.matches(card_number_regx)) {
					errorMsgs.put("card_number", "卡號為16位阿拉伯數字");
				}

				full_name = req.getParameter("name");// 取得持卡人姓名
				String full_name_regx = "^[(\u4e00-\u9fa5)(a-zA-Z)]++-[(\\u4e00-\\u9fa5)(a-zA-Z)]++";

				if (full_name == null || full_name.trim().length() == 0) {

					errorMsgs.put("full_name", "請輸入持卡人姓名");
				} else if (!full_name.trim().matches(full_name_regx)) {
					errorMsgs.put("full_name", "持卡人姓名為中英文字且不可有空格(eg.Kevin-Tsai)");
				}

				expiry = req.getParameter("expiry");// 取得卡片期限
				String expiry_regx = "^0[1-9]||1[0-2]\\s/\\s\\d{2}$";

				if (expiry == null || expiry.trim().length() == 0) {
					errorMsgs.put("expiry", "請輸卡限");

				} else if (!expiry.matches(expiry_regx)) {
					errorMsgs.put("expiry", "年月格式:MM/YY");
				}

				cvc = req.getParameter("cvc");// 取得安全碼
				String cvc_regx = "^\\d{3}$";
				if (cvc == null || cvc.trim().length() == 0) {

					errorMsgs.put("cvc", "請輸入安全碼");
				} else if (!cvc.matches(cvc_regx)) {
					errorMsgs.put("cvc", "安全碼須為3個數字");
				}

				if (!errorMsgs.isEmpty()) {
					// 剛寫的錯誤資料依然回傳

					// credit card
					req.setAttribute("card_number", card_number);
					req.setAttribute("name", full_name);
					req.setAttribute("expiry", expiry);
					req.setAttribute("cvc", cvc);
					req.setAttribute("mem_No", mem_No);

					RequestDispatcher failureView = req.getRequestDispatcher("addNewtransaction2.jsp");
					failureView.forward(req, res);
					return;// 中斷
				}

				Timestamp stor_Date = new Timestamp(System.currentTimeMillis());
				Integer stor_Point = new Integer(req.getParameter("stor_Point"));
//				System.out.println("stor_Point="+req.getParameter("stor_Point"));
				Integer stor_Status = null;

//				StoredrecordVO srVO = new StoredrecordVO();
//				srVO.setMem_No(mem_No);
//				srVO.setStor_Date(stor_Date);
//				srVO.setStor_Point(stor_Point);
//				srVO.setStor_Status(stor_Status);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("StoredrecordVO", srVO);// 含有輸入格式錯誤的empVO物件,也存入req
//					req.getRequestDispatcher("/front_end/storedrecord/addStoredrecord.jsp").forward(req, res);
//					return;// 有錯誤,返回addStoredrecord
//				}
				// =============開始儲值(儲值成功)====================
				StoredrecordService stsvc = new StoredrecordService();
				stor_Status = 1;
				stsvc.addStoredrecord(mem_No, stor_Date, stor_Point, 0, stor_Status);
				// ================改完，轉交===================
				req.getRequestDispatcher(req.getContextPath()+"/front_end/menu/listAllMenu4.jsp").forward(req, res);
				// =====================其他可能錯誤(儲值失敗)=========================
			} catch (Exception e) {
				errorMsgs.put("stor_failur", "儲值失敗,請聯絡管理員");
				req.getRequestDispatcher("addNewtransaction2.jsp").forward(req, res);
			}
		}

		if ("getPoint".equals(action)) {
			Integer stor_Point = new Integer(req.getParameter("stor_Point"));
			req.setAttribute("stor_Point", stor_Point);
			req.getRequestDispatcher("/front_end/storedrecord/addNewtransaction2.jsp").forward(req, res);
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// ===============開始刪除==================
				String stor_No = req.getParameter("stor_No");
				StoredrecordService strSvc = new StoredrecordService();
				strSvc.delete(stor_No);
				// ===============轉交=======================
				req.getRequestDispatcher("/front_end/storedrecord/listAllStoredrecord.jsp").forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/storedrecord/listAllStoredrecord.jsp").forward(req, res);
			}
		}

		if ("findByMem_no".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_No = req.getParameter("mem_No");

				// =========query=========================
				StoredrecordService srvc = new StoredrecordService();
				List<StoredrecordVO> list = srvc.findByMem_no(mem_No);
				if (list.size() == 0) {
					errorMsgs.add("您目前沒有任何儲值歷史紀錄");
					// req.setAttribute("list", list);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/front_end/storedrecord/transaction_query.jsp").forward(req, res);
					return;// 有錯誤,返回addStoredrecord
				}

				// ==========forward result===============
				req.setAttribute("list", list);
				req.getRequestDispatcher("/front_end/storedrecord/transaction_result.jsp").forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.getRequestDispatcher("/front_end/storedrecord/transaction_query.jsp").forward(req, res);
			}
		}
	}
}
