package android.com.reservation.controller;

import android.com.reservation.model.*;
import android.com.desk.model.*;
import android.com.main.ImageUtil;
import android.com.main.Send;
import android.com.member.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AndroidReservationServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		ResDAO_interface dao = new ResDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if ("getAll".equals(action)) {
			List<ResVO> resList = dao.getAll();
			writeText(res, gson.toJson(resList));
		}
		else if("add".equals(action)) {
			String resJson = jsonObject.get("reservation").getAsString();
			ResVO resVO = gson.fromJson(resJson, ResVO.class);
			String branchNo = jsonObject.get("branch_no").getAsString();
			String branchName = jsonObject.get("branch_name").getAsString();
			String seatStr = jsonObject.get("seatStr").getAsString();
			String res_no = dao.addWithBranchNo(branchNo, resVO, seatStr);
			if (!"-1".equals(res_no)) {
				resVO = dao.findByPrimaryKey(res_no);
				MemberDAO_interface mdao = new MemberDAO();
				MemberVO memVO = mdao.findByPrimaryKey(resVO.getMem_no());
				
				// 訂位成功時發送簡訊
				Send se = new Send();
			 	String[] tel ={memVO.getMem_Phone()};//,"0977777777","0988888888"};
			 	StringBuilder message = new StringBuilder()
			 			.append("竹風堂通知您:親愛的顧客您好，您於本次訂位之資訊如下，")
			 			.append("分店:")
			 			.append(branchName)
			 			.append("，時間:")
			 			.append(resVO.getRes_timebg().toString().substring(0, 16))
			 			.append("，人數:")
			 			.append(resVO.getRes_people().toString());
			 			
			 	se.sendMessage(tel , message.toString());
			}
			writeText(res, gson.toJson(resVO));
		}
		else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

}
