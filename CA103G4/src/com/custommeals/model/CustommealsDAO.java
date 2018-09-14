package com.custommeals.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class CustommealsDAO implements CustommealsDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = 
			"INSERT INTO CUSTOMMEALS" 
	                +"(CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE, CUSTOM_PHOTO)"
	                +"VALUES(('C'||LPAD(to_char(CUSTOMMEALS_seq.NEXTVAL),10,'0')),?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE CUSTOMMEALS SET MEM_NO=?, CUSTOM_NAME=?, CUSTOM_PRICE=?, CUSTOM_PHOTO=? WHERE CUSTOM_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM CUSTOMMEALS WHERE CUSTOM_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE, CUSTOM_PHOTO FROM CUSTOMMEALS WHERE CUSTOM_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE, CUSTOM_PHOTO FROM CUSTOMMEALS ORDER BY CUSTOM_NO";
//	private static final String FINDBYMEMNO="SELECT * FROM CUSTOMMEALS WHERE MEM_NO=?";
//	private static final String FINDBYCUSTOMNO="SELECT * FROM CUSTOMMEALS WHERE CUSTOM_NO=?";
	@Override
	public void insert(CustommealsVO custommealsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, custommealsVO.getCustom_No());
			pstmt.setString(1, custommealsVO.getMem_No());
			pstmt.setString(2, custommealsVO.getCustom_Name());
			pstmt.setInt(3, custommealsVO.getCustom_Price());
			pstmt.setBytes(4, custommealsVO.getCustom_Photo());
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void update(CustommealsVO custommealsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, custommealsVO.getMem_No());
			pstmt.setString(2, custommealsVO.getCustom_Name());
			pstmt.setInt(3, custommealsVO.getCustom_Price());
			pstmt.setBytes(4, custommealsVO.getCustom_Photo());
			pstmt.setString(5, custommealsVO.getCustom_No());
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改 " + rowCount + " 筆資料");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " 
					+ se.getMessage());
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
	public void delete(String Custom_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Custom_No);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public CustommealsVO findByPrimaryKey(String Custom_No) {
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, Custom_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setCustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setMem_No(rs.getString("MEM_NO"));
				custommealsVO.setCustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setCustom_Price(rs.getInt("CUSTOM_PRICE"));
				custommealsVO.setCustom_Photo(rs.getBytes("MENU_PHOTO"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return custommealsVO;
	}
	@Override
	public List<CustommealsVO> getAll() {
		List<CustommealsVO> custommealsVOList = new ArrayList<>();
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setCustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setMem_No(rs.getString("MEM_NO"));
				custommealsVO.setCustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setCustom_Price(rs.getInt("CUSTOM_PRICE"));
				custommealsVO.setCustom_Photo(rs.getBytes("CUSTOM_PHOTO"));
				custommealsVOList.add(custommealsVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " 
					+ se.getMessage());
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
		return custommealsVOList;
	}

	

}
