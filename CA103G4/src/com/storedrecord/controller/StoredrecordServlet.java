package com.storedrecord.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String action = req.getParameter("action");

		if ("findByPrimaryKey".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("error", errorMsgs);

			String stor_No = req.getParameter("stor_No").trim();
			String mem_No = req.getParameter("mem_No").trim();
			Timestamp stor_Date = null;
			Integer stor_Point = new Integer(req.getParameter("stor_Point").trim());
			Integer drew_Point = new Integer(req.getParameter("drew_Point").trim());
			Integer stor_Status = new Integer(req.getParameter("stor_Status").trim());

			try {
				stor_Date = Timestamp.valueOf(req.getParameter("stor_Date").trim());
			} catch (IllegalArgumentException ie) {
				stor_Date = new Timestamp(System.currentTimeMillis());
			}

			

			// ===================開始查詢=====================

			StoredrecordService srSv = new StoredrecordService();
			StoredrecordVO srVO = srSv.getOneStoredrecord(stor_No);
			if (srVO == null) {
				errorMsgs.add("查無資料");
			}
			// error display...

			/* ==================轉交查詢結果====================== */
			req.setAttribute("srVO", srVO);
			req.getRequestDispatcher("/storedrecord/listOneStoredrecord.jsp").forward(req, res);
			//======================================================
			
			
			
			
			
		}
		if("getOne_For_Update".equals(action)) {
			
			
			
			
			
			StoredrecordVO srVO = new StoredrecordVO();
			srVO.setStor_No(stor_No);
			srVO.setMem_No(mem_No);
			srVO.setStor_Date(stor_Date);
			srVO.setStor_Point(stor_Point);
			srVO.setDrew_Point(drew_Point);
			srVO.setStor_Status(stor_Status);
		}
		
	}
}
