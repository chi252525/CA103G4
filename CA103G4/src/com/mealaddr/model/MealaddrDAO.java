package com.mealaddr.model;

import java.util.*;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealaddrDAO implements MealaddrDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = "INSERT INTO MEALADDR (MADDR_NO, MEM_NO,  MADDR_CNAME, MADDR_ADDR, MADDR_RNAME)"
			+ "VALUES(?,?,?,?,?)";

	private static final String UPDATE_STMT = "UPDATE MEALADDR SET MEM_NO=?,  MADDR_CNAME=? , MADDR_ADDR=?, MADDR_RNAME =? WHERE MADDR_NO=?";
	private static final String FINDBY_MADDR_NO = "SELECT * FROM MEALADDR WHERE MADDR_NO = ? ";
	private static final String GETALL = "SELECT * FROM MEALADDR order by MADDR_NO";
	private static final String FINDBY_MEM_NO = "SELECT * FROM MEALADDR WHERE MEM_NO=?";
	private static final String DELETE_STMT = "DELETE FROM MEALADDR WHERE MADDR_No = ?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(MealaddrVO Mealaddr) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, Mealaddr.getMaddr_No());
			pstmt.setString(2, Mealaddr.getMem_No());
			pstmt.setString(3, Mealaddr.getMaddr_cname());
			pstmt.setString(4, Mealaddr.getMaddr_addr());
			pstmt.setString(5, Mealaddr.getMaddr_rname());
			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MealaddrVO Mealaddr) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, Mealaddr.getMem_No());
			pstmt.setString(2, Mealaddr.getMaddr_cname());
			pstmt.setString(3, Mealaddr.getMaddr_addr());
			pstmt.setString(4, Mealaddr.getMaddr_rname());
			pstmt.setString(5, Mealaddr.getMaddr_No());
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MealaddrVO findByPrimaryKey(String maddr_No) {
		MealaddrVO Mealaddr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_MADDR_NO);
			pstmt.setString(1, maddr_No);
			rs = pstmt.executeQuery();
			rs.next();
			Mealaddr = new MealaddrVO();
			Mealaddr.setMaddr_No(rs.getString("maddr_No"));
			Mealaddr.setMem_No(rs.getString("mem_No"));
			Mealaddr.setMaddr_cname(rs.getString("maddr_cname"));
			Mealaddr.setMaddr_addr(rs.getString("maddr_addr"));
			Mealaddr.setMaddr_rname(rs.getString("maddr_rname"));
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Mealaddr;
	}

	@Override
	public List<MealaddrVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MealaddrVO> mealaddrivitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MealaddrVO Mealaddr = new MealaddrVO();
				Mealaddr = new MealaddrVO();
				Mealaddr.setMaddr_No(rs.getString("maddr_No"));
				Mealaddr.setMem_No(rs.getString("mem_No"));
				Mealaddr.setMaddr_cname(rs.getString("maddr_cname"));
				Mealaddr.setMaddr_addr(rs.getString("maddr_addr"));
				Mealaddr.setMaddr_rname(rs.getString("maddr_rname"));
				mealaddrivitylist.add(Mealaddr); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mealaddrivitylist;
	}

	@Override
	public MealaddrVO findByMem_no(String mem_No) {
		MealaddrVO Mealaddr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_MEM_NO);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Mealaddr = new MealaddrVO();
				Mealaddr.setMaddr_No(rs.getString("maddr_No"));
				Mealaddr.setMem_No(rs.getString("mem_No"));
				Mealaddr.setMaddr_cname(rs.getString("maddr_cname"));
				Mealaddr.setMaddr_addr(rs.getString("maddr_addr"));
				Mealaddr.setMaddr_rname(rs.getString("maddr_rname"));
				System.out.println("===================================");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Mealaddr;
	}

	@Override
	public void delete(String maddr_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, maddr_No);
			pstmt.executeUpdate();
			System.out.println("刪除成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}