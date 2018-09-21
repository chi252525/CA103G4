package com.branch.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.branch.model.BranchService;
import com.branch.model.BranchVO;

/**
 * Servlet implementation class BranchServlet
 */
@WebServlet("/front_end/branch/branch.do")
public class BranchServlet extends HttpServlet {
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

		String action = req.getParameter("action");
		// ==================查單筆儲值紀錄=================
		if ("findByPrimaryKey".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// ==================輸入檢驗====================
				String branch_No = req.getParameter("branch_No");
				String branch_City = req.getParameter("branch_City");
				String regexMem = "^M\\d{6}$";
				String regexStor = "^B\\d{9}$";
				Boolean x = branch_No.matches(regexStor);// test for regular expression
				if (branch_No == null || branch_No.trim().length() == 0) {
					errorMsgs.add("請輸入儲值流水單號");
				} else if (branch_No == null && !branch_City.matches(regexMem)) {
					errorMsgs.add("格式錯誤:會員編號格式必須是大寫英文字母M加上5個數字");
				} else if (branch_City == null && !branch_No.matches(regexStor)) {
					errorMsgs.add("儲值流水單號必須是大寫英文字母B加上9個數字");
				}
				if (!errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/front_end/branch/select_page.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

				// ===================開始查詢=====================
				BranchService brsvc = new BranchService();
				BranchVO srVO = brsvc.findByBranch_No(branch_No);
				if (srVO == null) {
					errorMsgs.add("查無資料");
					req.getRequestDispatcher("/front_end/branch/select_page.jsp").forward(req, res);
					return;// 有錯誤,返回
				}
				// error display...
				/* ==================轉交查詢結果====================== */
				req.setAttribute("srVO", srVO);
				req.getRequestDispatcher("/front_end/branch/listOnebranch.jsp").forward(req, res);

				// ====================錯誤處理===========================
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				req.getRequestDispatcher("/front_end/branch/select_page.jsp").forward(req, res);
			}
		}
//			==================查點擊的儲值紀錄====================
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("error", errorMsgs);
			try {
				String branch_No = req.getParameter("branch_No");

				// 查點擊的儲值紀錄
				BranchService brsvc = new BranchService();
				BranchVO brvo = brsvc.findByBranch_No(branch_No);
				req.setAttribute("BranchVO", brvo);
				req.getRequestDispatcher("/front_end/branch/update_branch_input.jsp").forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		// =================修改單筆儲值紀錄=======================
		if ("Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("error", errorMsgs);
			try {
				String branch_No = req.getParameter("branch_No");

				String regexStor = "^B\\d{9}$";
				if (branch_No == null || branch_No.trim().length() == 0) {
					errorMsgs.add("請輸入儲值流水單號");
				} else if (!branch_No.matches(regexStor)) {
					errorMsgs.add("儲值流水單號必須是大寫英文字母A-Z加上9個數字");
				}

				String branch_City = req.getParameter("branch_City").trim();
				String regexMem = "^M\\d{6}$";

				if (branch_No == null || branch_No.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				} else if (!branch_No.matches(regexMem)) {
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

				BranchVO srVO = new BranchVO();
				srVO.setbranch_No(branch_No);
				srVO.setbranch_City(branch_City);
				srVO.setStor_Date(stor_Date);
				srVO.setStor_Point(stor_Point);
				srVO.setDrew_Point(drew_Point);
				srVO.setStor_Status(stor_Status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("BranchVO", srVO);
					req.getRequestDispatcher("/front_end/branch/update_branch_input.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

//				======================開始修改=========================
				BranchService brsvc = new BranchService();
				BranchVO srVo = brsvc.updatebranch(branch_No, branch_City, stor_Date, stor_Point, drew_Point,
						stor_Status);

				// ================改完，轉交===================
				req.setAttribute("BranchVO", srVo);
				req.getRequestDispatcher("front-end/branch/listOnebranch.jsp").forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/branch/update_branch_input.jsp").forward(req, res);
			}
		}
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String branch_City = req.getParameter("branch_City").trim();
				String regexMem = "^M\\d{6}$";
				MemberService memsrv = new MemberService();
				List<MemberVO> memlist = memsrv.getAll();

				if (branch_City == null || branch_City.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				} else if (!branch_City.matches(regexMem)) {
					errorMsgs.add("會員編號必須是大寫英文字母A-Z加上5個數字");
				}
				MemberVO memVO = memsrv.getOne_Member(branch_City);
				// 判斷member 資料庫裡是否有輸入的會員編號branch_City
				if (!memlist.contains(memVO)) {
					errorMsgs.add("會員編號不存在");
				}
				Timestamp stor_Date = null;
				try {
					stor_Date = Timestamp.valueOf(req.getParameter("stor_Date").trim());
				} catch (IllegalArgumentException ie) {
					stor_Date = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請選取日期");
				}
				Integer stor_Point = null;
				try {
					stor_Point = new Integer(req.getParameter("stor_Point").trim());
				} catch (NumberFormatException ne) {
					errorMsgs.add("點數請輸入數字");
				}

				Integer drew_Point = null;
				try {
					drew_Point = Integer.parseInt(req.getParameter("drew_Point").trim());
				} catch (NumberFormatException ne) {
					errorMsgs.add("點數請輸入數字");
				}
				Integer stor_Status = null;
				try {
					stor_Status = Integer.parseInt(req.getParameter("stor_Status").trim());
					if (stor_Status != 1 && stor_Status != 0) {
						stor_Status = 0;
						errorMsgs.add("請輸入1或0");
					}
				} catch (NumberFormatException ne) {
					stor_Status = 0;
					errorMsgs.add("狀態請輸入1或0");
				}

				BranchVO srVO = new BranchVO();
				srVO.setbranch_City(branch_City);
				srVO.setStor_Date(stor_Date);
				srVO.setStor_Point(stor_Point);
				srVO.setDrew_Point(drew_Point);
				srVO.setStor_Status(stor_Status);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("BranchVO", srVO);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/front_end/branch/addbranch.jsp").forward(req, res);
					return;// 有錯誤,返回addbranch
				}
				// =============開始新增====================
				BranchService brsvc = new BranchService();
				brsvc.addbranch(branch_City, stor_Date, stor_Point, drew_Point, stor_Status);
				// ================改完，轉交===================
				req.getRequestDispatcher("/front_end/branch/listAllbranch.jsp").forward(req, res);
				// =====================其他可能錯誤=========================
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/branch/addbranch.jsp").forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// ===============開始刪除==================
				String branch_No = req.getParameter("branch_No");
				BranchService strSvc = new BranchService();
				strSvc.delete(branch_No);
				
				if(errorMsgs.isEmpty()) {
									}
				// ===============轉交=======================
				req.getRequestDispatcher("/front_end/branch/listAllbranch.jsp").forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/front_end/branch/listAllbranch.jsp").forward(req, res);
			}
		}
		if ("findBybranch_NO".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String branch_City = req.getParameter("branch_City");

				// =========query=========================
				BranchService brvc = new BranchService();
				List<BranchVO> list = brvc.findBy_City(branch_City);
				if (list.size() == 0) {
					errorMsgs.add("無符合的分店");
					//req.setAttribute("list", list);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/front_end/branch/transaction_mang.jsp").forward(req, res);
					return;// 有錯誤,返回addbranch
				}
				// ==========forward result===============
				req.setAttribute("list", list);
				req.getRequestDispatcher("/front_end/branch/transaction_result.jsp").forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.getRequestDispatcher("/front_end/branch/transaction_mang.jsp").forward(req, res);
			}
		}
	}
}
