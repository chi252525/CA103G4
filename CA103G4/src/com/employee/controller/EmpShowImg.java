package com.employee.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.DataSource;
@MultipartConfig(fileSizeThreshold=1024*1024)
public class EmpShowImg extends HttpServlet {
	Connection con;

	public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
						
		//登入顯示大頭照
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("image/gif");
			try {

				String emp_No = req.getParameter("emp_No").trim();
				System.out.println(emp_No);		
				Statement stmt = con.createStatement();
	
				ResultSet rs = stmt.executeQuery(
					"SELECT EMP_PHOTO FROM EMPLOYEE WHERE EMP_NO='"+emp_No+"'");
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("EMP_PHOTO"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/front_end/img/no-photo.png");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
					
			}catch(Exception e){
				System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/front_end/img/LOGO-04.png");
				byte[] b = new byte[in.available()]; 
				in.read(b);
				out.write(b);
				in.close();
			}		
	}
	
	
	public void  init() {
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
