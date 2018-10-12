package com.branch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.branch.model.BranchService;
import com.branch.model.BranchVO;

/**
 * Servlet implementation class BranchServlet
 */
@WebServlet("/back_end/branch/branch.do")
public class BranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);
		// ==================查單筆儲值紀錄=================
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// ==================輸入檢驗 select menu doesnt need====================
				String branch_No = req.getParameter("branch_No");
				String location = req.getParameter("location");
				System.out.println("location=" + location);
				if (!errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

				// ===================開始查詢=====================
				BranchService brsvc = new BranchService();
				BranchVO brVO = brsvc.findByBranch_No(branch_No);
				if (brVO == null) {
					errorMsgs.add("查無資料");
					if (location.contains("front_end")) {
						req.getRequestDispatcher(location).forward(req, res);
					}

					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
					return;// 有錯誤,返回
				}
				// error display...
				/* ==================轉交查詢結果====================== */
				req.setAttribute("brVO", brVO);
				if (location.contains("front_end")) {
					req.getRequestDispatcher(location).forward(req, res);

				} else {
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
				}

				// ====================錯誤處理===========================
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());

				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
			}
		}
		// =================修改單筆儲值紀錄=======================
		if ("Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("error", errorMsgs);
			try {
				String branch_No = req.getParameter("branch_No");

				String branch_City = req.getParameter("branch_City");
				String regexForZhAndEn = "^[(\u4e00-\u9fa5)(_a-zA-Z)(0-9)]+$";

				String branch_Name = req.getParameter("branch_Name").trim();

				if (branch_Name == null || branch_Name.length() == 0) {
					errorMsgs.add("請輸入分店名稱");
				} else if (!branch_Name.matches(regexForZhAndEn)) {

					errorMsgs.add("分店名稱必須是中英文");
				}

				String branch_Dist = req.getParameter("branch_Dist");


				String branch_Addr = req.getParameter("branch_Addr");

				if (branch_Addr == null || branch_Addr.length() == 0) {
					errorMsgs.add("請輸入地址");
				} else if (!branch_Addr.matches(regexForZhAndEn)) {

					errorMsgs.add("地址必須是中英文");
				}

				String branch_Tel = null;

				branch_Tel = req.getParameter("branch_Tel").trim();
				String regexForTel = "^\\d{9,10}$";
				if (branch_Tel == null || branch_Tel.length()==0) {
					errorMsgs.add("尚未輸入電話");
				} else if (!branch_Tel.matches(regexForTel)) {
					errorMsgs.add("請輸入數字且長度須為9-10個數字");
				}

				String branch_Pos = req.getParameter("branch_Pos");

				String branch_Lan = req.getParameter("branch_Lan");
				String regexLatLat = "^\\d++$";
				if (branch_Lan.length() == 0 || branch_Lan == null) {
					errorMsgs.add("請輸入經度");
				} else if (branch_Lan.matches(regexLatLat)) {
					errorMsgs.add("經度需為數字");
				}

				String branch_Lat = req.getParameter("branch_Lat");
				if (branch_Lat.length() == 0 || branch_Lat == null) {
					errorMsgs.add("請輸入經緯度");
				} else if (branch_Lat.matches(regexLatLat)) {
					errorMsgs.add("緯度需為數字");
				}

				String branch_Time = req.getParameter("branch_Time");
				if (branch_Time.trim().length() == 0 || branch_Time == null) {
					errorMsgs.add("請輸入營業時間");
				}
				Double branch_Del = null;
				try {
					branch_Del=Double.parseDouble(req.getParameter("branch_Del"));

				} catch (NullPointerException e) {
					errorMsgs.add("請輸入外送範圍");
				} catch (NumberFormatException e) {
					errorMsgs.add("外送範圍需為數字(單位為km)");
				}

				Integer branch_Tdesk = null;
				
				try {
					branch_Tdesk = Integer.parseInt(req.getParameter("branch_Tdesk"));
					if (branch_Tdesk == 0 || branch_Tdesk == null) {
						errorMsgs.add("請輸入桌數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("桌數需為數字");
				}

				BranchVO updateBrVO = new BranchVO();
				updateBrVO.setBranch_No(branch_No);
				System.out.println("branch_No"+branch_No);
				updateBrVO.setBranch_City(branch_City);
				System.out.println("branch_City="+branch_City);
				updateBrVO.setBranch_Name(branch_Name);
				System.out.println("branch_Name="+branch_Name);
				updateBrVO.setBranch_Dist(branch_Dist);
				System.out.println("branch_Dist="+branch_Dist);
				updateBrVO.setBranch_Addr(branch_Addr);
				System.out.println("branch_Addr="+branch_Addr);
				updateBrVO.setBranch_Pos(branch_Pos);
				System.out.println("branch_Pos="+branch_Pos);
				updateBrVO.setBranch_Lan(branch_Lan);
				System.out.println("branch_Lan="+branch_Lan);
				updateBrVO.setBranch_Lat(branch_Lat);
				System.out.println("branch_Lat="+branch_Lat);
				updateBrVO.setBranch_Time(branch_Time);
				System.out.println("branch_Time="+branch_Time);
				updateBrVO.setBranch_Del(branch_Del);
				System.out.println("branch_Del="+branch_Del);
				updateBrVO.setBranch_Tel(branch_Tel);
				System.out.println("branch_Tel="+branch_Tel);
				updateBrVO.setBranch_Tdesk(branch_Tdesk);
				System.out.println("branch_Tdesk="+branch_Tdesk);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("updateBrVO", updateBrVO);// save error object
					req.getRequestDispatcher("/back_end/branch/updatebranch.jsp").forward(req, res);
					return;// 有錯誤,返回
				}

//				======================開始修改=========================
				BranchService brsvc = new BranchService();
				BranchVO brVO2 = brsvc.updateBranch(branch_No, branch_Name, branch_City, branch_Dist, branch_Addr,
						branch_Pos, branch_Lan, branch_Lat, branch_Time, branch_Del, branch_Tel, branch_Tdesk);

				// ================改完，轉交===================
//				req.setAttribute("BranchVO", brVO2);
				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/back_end/branch/updatebranch.jsp").forward(req, res);
			}
		}
		
		//顯示所要修改的分店資訊
		if("getonebranch".equals(action)) {
			String branch_No = req.getParameter("branch_No");
			System.out.println(branch_No);
			BranchService brsvc = new BranchService();
			BranchVO updateBrVO = new BranchVO();
			
			updateBrVO =brsvc.findByBranch_No(branch_No);
			System.out.println(updateBrVO);
			req.setAttribute("updateBrVO", updateBrVO);
			
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/branch/updatebranch.jsp");
			successView.forward(req, res);
		}
		
		
		//新增一間分店
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {


				String branch_City = req.getParameter("branch_City");
				String regexForZhAndEn = "^[(\u4e00-\u9fa5)(_a-zA-Z)(0-9)]+$";


				String branch_Name = req.getParameter("branch_Name").trim();

				if (branch_Name == null || branch_Name.length() == 0) {
					errorMsgs.add("請輸入分店名稱");
				} else if (!branch_Name.matches(regexForZhAndEn)) {

					errorMsgs.add("分店名稱必須是中英文");
				}

				String branch_Dist = req.getParameter("branch_Dist");


				String branch_Addr = req.getParameter("branch_Addr");

				if (branch_Addr == null || branch_Addr.length() == 0) {
					errorMsgs.add("請輸入地址");
				} else if (!branch_Addr.matches(regexForZhAndEn)) {

					errorMsgs.add("地址必須是中英文");
				}

				String branch_Tel = null;

				branch_Tel = req.getParameter("branch_Tel").trim();
				String regexForTel = "^\\d{9,10}$";
				if (branch_Tel == null || branch_Tel.length()==0) {
					errorMsgs.add("尚未輸入電話");
				} else if (!branch_Tel.matches(regexForTel)) {
					errorMsgs.add("請輸入數字且長度須為9-10個數字");
				}

				String branch_Pos = req.getParameter("branch_Pos");

				String branch_Lan = req.getParameter("branch_Lan");
				String regexLatLat = "^\\d++$";
				if (branch_Lan.length() == 0 || branch_Lan == null) {
					errorMsgs.add("請輸入經度");
				} else if (branch_Lan.matches(regexLatLat)) {
					errorMsgs.add("經度需為數字");
				}

				String branch_Lat = req.getParameter("branch_Lat");
				if (branch_Lat.length() == 0 || branch_Lat == null) {
					errorMsgs.add("請輸入經緯度");
				} else if (branch_Lat.matches(regexLatLat)) {
					errorMsgs.add("緯度需為數字");
				}

				String branch_Time = req.getParameter("branch_Time");
				if (branch_Time.trim().length() == 0 || branch_Time == null) {
					errorMsgs.add("請輸入營業時間");
				}
				Double branch_Del = null;
				try {
					branch_Del=Double.parseDouble(req.getParameter("branch_Del"));

				} catch (NullPointerException e) {
					errorMsgs.add("請輸入外送範圍");
				} catch (NumberFormatException e) {
					errorMsgs.add("外送範圍需為數字(單位為km)");
				}

				Integer branch_Tdesk = null;
				
				try {
					branch_Tdesk = Integer.parseInt(req.getParameter("branch_Tdesk"));
					if (branch_Tdesk == 0 || branch_Tdesk == null) {
						errorMsgs.add("請輸入桌數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("桌數需為數字");
				}

				BranchVO insertbrVO = new BranchVO();
				insertbrVO.setBranch_City(branch_City);
				System.out.println("branch_City="+branch_City);
				insertbrVO.setBranch_Name(branch_Name);
				System.out.println("branch_Name="+branch_Name);
				insertbrVO.setBranch_Dist(branch_Dist);
				System.out.println("branch_Dist="+branch_Dist);
				insertbrVO.setBranch_Addr(branch_Addr);
				System.out.println("branch_Addr="+branch_Addr);
				insertbrVO.setBranch_Pos(branch_Pos);
				System.out.println("branch_Pos="+branch_Pos);
				insertbrVO.setBranch_Lan(branch_Lan);
				System.out.println("branch_Lan="+branch_Lan);
				insertbrVO.setBranch_Lat(branch_Lat);
				System.out.println("branch_Lat="+branch_Lat);
				insertbrVO.setBranch_Time(branch_Time);
				System.out.println("branch_Time="+branch_Time);
				insertbrVO.setBranch_Del(branch_Del);
				System.out.println("branch_Del="+branch_Del);
				insertbrVO.setBranch_Tel(branch_Tel);
				System.out.println("branch_Tel="+branch_Tel);
				insertbrVO.setBranch_Tdesk(branch_Tdesk);
				System.out.println("branch_Tdesk="+branch_Tdesk);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("insertbrVO", insertbrVO);// save error object
					req.getRequestDispatcher("/back_end/branch/addbranch.jsp").forward(req, res);
					return;// 有錯誤,返回
				}
				// =============開始新增====================
				BranchService brsvc = new BranchService();
				brsvc.addBranch(branch_Name, branch_City, branch_Dist, branch_Addr, branch_Pos, branch_Lan, branch_Lat,
						branch_Time, branch_Del, branch_Tel, branch_Tdesk);
				// ================改完，轉交===================
				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
				// =====================其他可能錯誤=========================
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/back_end/branch/addbranch.jsp").forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// ===============開始刪除==================
				String branch_No = req.getParameter("branch_No");
				BranchService strSvc = new BranchService();
				String delrow = Integer.toString(strSvc.delete(branch_No));
				if (delrow.equals("0")) {
					delrow = "刪除失敗，請聯絡管理員";
				} else {
					delrow = "刪除成功";
				}
				if (!errorMsgs.isEmpty()) {
					errorMsgs.add("刪除失敗，請聯絡管理員");
					// req.setAttribute("list", list);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
					return;// 有錯誤,返回addbranch
				}
				// ===============轉交=======================
				res.setCharacterEncoding("UTF-8");
				res.getWriter().print(new StringBuilder("<tr valign=\"middle\">")
						.append("<td class=\"text-center\" colspan=\"6\" rowspan=\"6\"\r\n"
								+ "style=\"vertical-align: middle; font-size: 20px; color: sienna; padding-top: 20px; font-weight: bold;\"> "
								+ delrow + " </td>\r\n" + "</tr>")
						.append("<tr style=\"height: 40px;\"></tr>").append("<tr style=\"height: 40px;\"></tr>")
						.append("<tr style=\"height: 40px;\"></tr>").append("<tr style=\"height: 40px;\"></tr>")
						.append("<tr style=\"height: 40px;\"></tr>").append("<tr style=\"height: 40px;\"></tr>"));
				// req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req,
				// res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
			}
		}

		if ("findBybranch_City".equals(action) || "findBybranch_CityfonrEnd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String branch_City = req.getParameter("branch_City");

				// =========query=========================
				BranchService brvc = new BranchService();
				List<BranchVO> list = brvc.findBy_City(branch_City);
				if (list.size() == 0) {
					errorMsgs.add("無符合的分店");
					// req.setAttribute("list", list);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
					return;// 有錯誤,返回addbranch
				}
				// ==========forward result===============
				req.setAttribute("brlist", list);
				if ("findBybranch_City".equals(action))
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
				else if ("findBybranch_CityfonrEnd".equals(action))
					req.getRequestDispatcher("/front_end/branchInfo/branch_mang.jsp").forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
			}
		}

		if ("listAll".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// =========query=========================
				BranchService brvc = new BranchService();
				List<BranchVO> list = brvc.getAll();
				
				if (list.size() == 0) {
					errorMsgs.add("一間分店都沒有");
					// req.setAttribute("list", list);// 含有輸入格式錯誤的empVO物件,也存入req
					req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
					return;// 有錯誤,返回addbranch
				}
				// ==========forward result===============
				req.setAttribute("brlist", list);
				// req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req,
				// res);
				// =============print to web page=====================
				StringBuilder htmlstr = new StringBuilder();
				JSONArray jsonarr = new JSONArray();

				for (BranchVO brVO : list) {
					JSONObject jso = new JSONObject();
					jso.put("branch_No", brVO.getBranch_No());
					jso.put("branch_Name", brVO.getBranch_Name());
					jso.put("branch_City", brVO.getBranch_City());
					jso.put("branch_Dist", brVO.getBranch_Dist());
					jso.put("branch_Addr", brVO.getBranch_Addr());
					jso.put("branch_Tel", brVO.getBranch_Tel());
//					htmlstr.append("<tr><td>" + brVO.getBranch_No() + "</td>")
//							.append("<td>" + brVO.getBranch_Name() + "</td>")
//							.append("<td>" + brVO.getBranch_City() + "</td>")
//							.append("<td>" + brVO.getBranch_Dist() + "</td>")
//							.append("<td>" + brVO.getBranch_Addr() + "</td>")
//							.append("<td>" + brVO.getBranch_Tel() + "</td>")
//							.append("<td><input type=\"button\" class=\"update btn btn-warning btn-sm\" value=\"修改\" style=\"display:none\"/></td>")
//							.append("<td><input type=\"button\" class=\"del btn btn-danger btn-sm\" value=\"刪除\" style=\"display:none\"/></td>");
					jsonarr.put(jso);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");

				PrintWriter out = res.getWriter();
				out.print(jsonarr);
				System.out.println(jsonarr);
				out.flush();
				out.close();
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.getRequestDispatcher("/back_end/branch/branch_mang.jsp").forward(req, res);
			}
		}
		
		if("cityToBranch".equals(action)) {  
			String city = req.getParameter("city");
			System.out.println(city);
			BranchService brhSvc = new BranchService();
			List<BranchVO> listbrh = brhSvc.findBy_City(city);
			System.out.println(listbrh);
			PrintWriter out = res.getWriter();
			for(BranchVO brhVO: listbrh) {
			    out.write(brhVO.getBranch_No()+",");
			    System.out.println(brhVO.getBranch_Name());
			}	
			 out.write(":");
			for(BranchVO brhVO: listbrh) {
				out.write(brhVO.getBranch_Name()+",");
			    System.out.println(brhVO.getBranch_Name());
			}
			
		}
	}
}
