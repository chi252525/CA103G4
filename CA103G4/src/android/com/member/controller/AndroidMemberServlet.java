package android.com.member.controller;

import android.com.main.ImageUtil;
import android.com.member.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
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
		// 圖片請求
		else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String pk = jsonObject.get("pk").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memDAO.getImage(pk);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			try {
				os.write(image);
			} catch (NullPointerException ne) {
				System.out.println(pk+" : No Picture!");
			}
		}
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
