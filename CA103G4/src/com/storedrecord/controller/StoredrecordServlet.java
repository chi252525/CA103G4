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

/**
 * Servlet implementation class storedrecordServlet
 */
@WebServlet("front_end/storedrecord/storedrecord.do")
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
			
			String Mem_No = new String(req.getParameter("mem_No").trim()); 
			String stor_No = new String(req.getParameter("stor_No").trim());
			Timestamp stor_Date = new Timestamp(req.getParameter("stor_Date").trim());
			
			
			
			
		}

		// ===================開始查詢==============================

		StoredrecordService srs = new StoredrecordService();

	}

}
