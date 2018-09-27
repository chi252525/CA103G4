package com.activity.controller;


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

import com.activity.model.ActivityService;
import com.post.model.PostService;

public class ActivityShowImage extends HttpServlet{
	Connection con;
	public ActivityShowImage() {
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		System.out.println("跳到activityshowimage");
		try{
			String act_No = req.getParameter("act_No");
			System.out.println("ActivityShowImage +req.getParameter act_No="+act_No);
			ActivityService actSvc = new ActivityService();
			byte[] image = actSvc.getOneActivity(act_No).getAct_Carousel();
			
			out.write(image);
			System.out.println("成功寫出圖片");
		}	catch (Exception e){
			InputStream in = getServletContext().getResourceAsStream("/res/img/ad_no_photo.png");
			byte[] b = new byte[in.available()];
			
			in.read(b);
			out.write(b);
			System.out.println("寫出no_photo");
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