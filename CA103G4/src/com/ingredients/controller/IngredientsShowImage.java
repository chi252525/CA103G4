package com.ingredients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ingredients.model.IngredientsService;


public class IngredientsShowImage extends HttpServlet{
	Connection con;
	public IngredientsShowImage() {
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
	
		try{
//			System.out.println("in PostShowImage");
			String ingdt_Id = req.getParameter("ingdt_Id");
			IngredientsService ingredientsvc = new IngredientsService();
			byte[] image = ingredientsvc.getOneIngredients(ingdt_Id).getingdt_Photo();
			out.write(image);
		}	catch (Exception e){
			InputStream in = getServletContext().getResourceAsStream("/res/img/menu_no_photo.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	
	}
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
