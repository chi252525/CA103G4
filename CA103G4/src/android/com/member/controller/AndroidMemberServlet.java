package android.com.member.controller;

import android.com.member.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AndroidMemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MemberDAO_interface memDAO = new MemberDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isMember")) {
			String mem_Id = jsonObject.get("mem_Id").getAsString();
			String mem_Pw = jsonObject.get("mem_Pw").getAsString();
			MemberVO memVO = memDAO.isMember(mem_Id, mem_Pw);
			// 查不到該會員回傳空字串，轉成Json格式字串為{}
			writeText(res, memVO == null ? "" : gson.toJson(memVO));
		}
//		else if (action.equals("isUserIdExist")) {
//			String userId = jsonObject.get("userId").getAsString();
////			writeText(res, String.valueOf(memberDao.isUserIdExist(userId)));
//		} else if (action.equals("add")) {
//			EmpVO empVO = gson.fromJson(jsonObject.get("employee").getAsString(), EmpVO.class);
//			writeText(res, String.valueOf(empDAO.add(empVO)));
//		} else if (action.equals("findById")) {
//			String userId = jsonObject.get("userId").getAsString();
//			EmpVO empVO = empDAO.findById(userId);
//			writeText(res, empVO == null ? "" : gson.toJson(empVO));
//		} else if (action.equals("update")) {
//			EmpVO empVO = gson.fromJson(jsonObject.get("employee").getAsString(), EmpVO.class);
//			writeText(res, String.valueOf(empDAO.update(empVO)));
//		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}
