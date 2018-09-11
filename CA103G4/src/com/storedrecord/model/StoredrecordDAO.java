package com.storedrecord.model;

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

public class StoredrecordDAO implements StoredrecordDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = "INSERT INTO STOREDRECORD (stor_NO, MEM_NO,  STOR_DATE, STOR_POINT, DREW_POINT, STOR_STATUS)"
			+ "VALUES( ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE STOREDRECORD SET MEM_NO=?,  STOR_DATE=? , STOR_POINT=?, DREW_POINT =? STOR_STATUS =?, WHERE stor_NO=?";
	private static final String FINDBY_stor_NO = "SELECT * FROM STOREDRECORD WHERE stor_NO = ? ";
	private static final String GETALL = "SELECT * FROM STOREDRECORD order by stor_NO";
	private static final String FINDBY_MEM_NO = "SELECT * FROM STOREDRECORD WHERE MEM_NO=?";
	private static final String FINDBY_MON = "SELECT * FROM STOREDRECORD WHERE STOR_DATE BETWEEN STOR_DATE="
	private static final String DELETE_STMT = "DELETE FROM STOREDRECORD WHERE stor_No = ?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(StoredrecordVO STOREDRECORD) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, STOREDRECORD.getstor_No());
			pstmt.setString(2, STOREDRECORD.getMem_No());
			pstmt.setString(3, STOREDRECORD.getSTOR_DATE());
			pstmt.setString(4, STOREDRECORD.getSTOR_POINT());
			pstmt.setString(5, STOREDRECORD.getDREW_POINT());
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
	public void update(StoredrecordVO STOREDRECORD) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, STOREDRECORD.getMem_No());
			pstmt.setString(2, STOREDRECORD.getSTOR_DATE());
			pstmt.setString(3, STOREDRECORD.getSTOR_POINT());
			pstmt.setString(4, STOREDRECORD.getDREW_POINT());
			pstmt.setString(5, STOREDRECORD.getstor_No());
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
	public StoredrecordVO findByPrimaryKey(String stor_No) {
		StoredrecordVO STOREDRECORD = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_stor_NO);
			pstmt.setString(1, stor_No);
			rs = pstmt.executeQuery();
			rs.next();
			STOREDRECORD = new StoredrecordVO();
			STOREDRECORD.setstor_No(rs.getString("stor_No"));
			STOREDRECORD.setMem_No(rs.getString("mem_No"));
			STOREDRECORD.setSTOR_DATE(rs.getString("STOR_DATE"));
			STOREDRECORD.setSTOR_POINT(rs.getString("STOR_POINT"));
			STOREDRECORD.setDREW_POINT(rs.getString("DREW_POINT"));
			
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
		return STOREDRECORD;
	}

	@Override
	public List<StoredrecordVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoredrecordVO> STOREDRECORDivitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StoredrecordVO STOREDRECORD = new StoredrecordVO();
				STOREDRECORD = new StoredrecordVO();
				STOREDRECORD.setstor_No(rs.getString("stor_No"));
				STOREDRECORD.setMem_No(rs.getString("mem_No"));
				STOREDRECORD.setSTOR_DATE(rs.getString("STOR_DATE"));
				STOREDRECORD.setSTOR_POINT(rs.getString("STOR_POINT"));
				STOREDRECORD.setDREW_POINT(rs.getString("DREW_POINT"));
				STOREDRECORDivitylist.add(STOREDRECORD); // Store the row in the list
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
		return STOREDRECORDivitylist;
	}

	@Override
	public StoredrecordVO findByMem_no(String mem_No) {
		StoredrecordVO STOREDRECORD = null;
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
				STOREDRECORD = new StoredrecordVO();
				STOREDRECORD.setstor_No(rs.getString("stor_No"));
				STOREDRECORD.setMem_No(rs.getString("mem_No"));
				STOREDRECORD.setSTOR_DATE(rs.getString("STOR_DATE"));
				STOREDRECORD.setSTOR_POINT(rs.getString("STOR_POINT"));
				STOREDRECORD.setDREW_POINT(rs.getString("DREW_POINT"));
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
		return STOREDRECORD;
	}

	@Override
	public void delete(String stor_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, stor_No);
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

	@Override
	public StoredrecordVO findByMon(String Mon) {
		// TODO Auto-generated method stub
		return null;
	}

}