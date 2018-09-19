package android.com.menu.controller;

import android.com.menu.model.*;
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
import com.google.gson.JsonObject;

public class AndroidMenuServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		MenuDAO_interface dao = new MenuDAO();
		Gson gson = new Gson();

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
			List<MenuVO> menuList = dao.getAll();
			writeText(res, gson.toJson(menuList));
		} 
//		else if ("findByCategory".equals(action)) {
//			int cid = Integer.parseInt(jsonObject.get("cid").getAsString());
//			List<Book> bookList = dao.findByCategory(cid);
//			writeText(res, gson.toJson(bookList));
//			
//		} 
		// 圖片請求
		else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String pk = jsonObject.get("pk").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(pk);
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
		MenuDAO_interface dao = new MenuDAO();
		List<MenuVO> menuList = dao.getAll();
		writeText(res, new Gson().toJson(menuList));
	}

}
