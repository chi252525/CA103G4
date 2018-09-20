package android.com.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.menu.model.*;

@SuppressWarnings("serial")
public class SearchMenuServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private MenuService menuSvc;
	private List<MenuVO> listInDatabase;
	private List<MenuVOWithStringPhoto> list = new ArrayList<>();
	
	@Override
	public void init() throws ServletException {
		super.init();
		menuSvc = new MenuService();
		listInDatabase = menuSvc.getAll();
		byte[] imageBytes;
    	String imageBase64;
	    
	    for(MenuVO menuVO : listInDatabase) {
	    	MenuVOWithStringPhoto menuVOS = new MenuVOWithStringPhoto();
	    	menuVOS.setMenu_No(menuVO.getMenu_No());
	    	menuVOS.setMenu_Id(menuVO.getMenu_Id());
	    	menuVOS.setMenu_Type(menuVO.getMenu_Type());
	    	menuVOS.setMenu_Price(menuVO.getMenu_Price());
	    	menuVOS.setMenu_Intro(menuVO.getMenu_Intro());
	    	try {
	    		imageBytes = menuVO.getMenu_Photo();
		    	imageBase64 = DatatypeConverter.printBase64Binary(imageBytes);
	    	}catch(NullPointerException e) {
	    		imageBase64 = "";
	    	}
	    	menuVOS.setMenu_Photo(imageBase64);
	    	menuVOS.setMenu_Status(menuVO.getMenu_Status());
	    	list.add(menuVOS);
	    }
	    
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String param = jsonObject.get("param").getAsString();
		String outStr = "";
		switch (param) {
		case "ONE":
			outStr = gson.toJson(list);
			break;
		case "ALL":
			outStr = gson.toJson(list);
			break;
		default:
			doGet(req, res);
			break;
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		System.out.println("output: " + outStr);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Gson gson = new Gson();
		String menuList = gson.toJson(list);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println("<h3>Menus</h3>");
		out.println(menuList);
	}

}
