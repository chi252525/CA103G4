package android.com.reservation.controller;

import android.com.reservation.model.*;
import android.com.desk.model.*;
import android.com.main.ImageUtil;

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
			String orderJson = jsonObject.get("reservation").getAsString();
			ResVO resVO = gson.fromJson(orderJson, ResVO.class);
			String branchNo = jsonObject.get("branch_no").getAsString();
			String seatStr = jsonObject.get("seatStr").getAsString();
			String res_no = dao.addWithBranchNo(branchNo, resVO, seatStr);
			if (!"-1".equals(res_no)) {
				resVO = dao.findByPrimaryKey(res_no);
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
