package com.ingredients.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class IngredientsDAO implements IngredientsDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "CA103";
//	private static final String PASSWORD = "123456";
//	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT=		
			"INSERT INTO INGREDIENTS (INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE, INGDT_PHOTO)"
					+"VALUES(('I'||LPAD(to_char(INGREDIENTS_seq.NEXTVAL), 4, '0')),?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE INGREDIENTS SET INGDTC_ID=?, INGDT_NAME=?, INGDT_STATUS=?, INGDT_POINT=?, INGDT_UNIT=?, INGDT_PRICE=?, INGDT_PHOTO=? WHERE INGDT_ID=?";
	private static final String DELETE_STMT =
			"DELETE FROM INGREDIENTS WHERE INGDT_ID=?";
	private static final String SELECT_ONE_STMT=
			"SELECT INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE, INGDT_PHOTO FROM INGREDIENTS WHERE INGDT_ID=?";
	private static final String SELECT_ALL_STMT=
			"SELECT INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE, INGDT_PHOTO FROM INGREDIENTS ORDER BY INGDT_ID";


	

	@Override
	public void insert(IngredientsVO ingredientsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, ingredientsVO.getingdt_Id());
			pstmt.setString(1, ingredientsVO.getingdtc_Id());
			pstmt.setString(2, ingredientsVO.getingdt_Name());
			pstmt.setInt(3, ingredientsVO.getingdt_Status());
			pstmt.setString(4, ingredientsVO.getingdt_Point());
			pstmt.setString(5, ingredientsVO.getingdt_Unit());
			pstmt.setInt(6, ingredientsVO.getingdt_Price());
			pstmt.setBytes(7, ingredientsVO.getingdt_Photo());
			System.out.println("ingredientsVO.getingdt_Photo()=" + ingredientsVO.getingdt_Photo());
			
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			
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
	public void update(IngredientsVO ingredientsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, ingredientsVO.getingdtc_Id());
			pstmt.setString(2, ingredientsVO.getingdt_Name());
			pstmt.setInt(3, ingredientsVO.getingdt_Status());
			pstmt.setString(4, ingredientsVO.getingdt_Point());
			pstmt.setString(5, ingredientsVO.getingdt_Unit());
			pstmt.setInt(6, ingredientsVO.getingdt_Price());
			pstmt.setBytes(7, ingredientsVO.getingdt_Photo());
			pstmt.setString(8, ingredientsVO.getingdt_Id());
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void delete(String ingdt_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, ingdt_Id);
			pstmt.executeUpdate();

			// Handle any driver errors
		}
//			catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//			// Handle any SQL errors
//		}
		catch (SQLException se) {
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
	public IngredientsVO findByPrimaryKey(String ingdt_Id) {
		IngredientsVO ingredientsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, ingdt_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingredientsVO = new IngredientsVO();
				ingredientsVO.setingdt_Id(rs.getString("INGDT_ID"));
				ingredientsVO.setingdtc_Id(rs.getString("INGDTC_ID"));
				ingredientsVO.setingdt_Name(rs.getString("INGDT_NAME"));
				ingredientsVO.setingdt_Status(rs.getInt("INGDT_STATUS"));
				ingredientsVO.setingdt_Point(rs.getString("INGDT_POINT"));
				ingredientsVO.setingdt_Unit(rs.getString("INGDT_UNIT"));
				ingredientsVO.setingdt_Price(rs.getInt("INGDT_PRICE"));
				ingredientsVO.setingdt_Photo(rs.getBytes("INGDT_PHOTO"));
				
				
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
		return ingredientsVO;
	}


	@Override
	public List<IngredientsVO> getAll() {
		List<IngredientsVO> ingredientsVOList = new ArrayList<>();
		IngredientsVO ingredientsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingredientsVO = new IngredientsVO();
				ingredientsVO.setingdt_Id(rs.getString("INGDT_ID"));
				ingredientsVO.setingdtc_Id(rs.getString("INGDTC_ID"));
				ingredientsVO.setingdt_Name(rs.getString("INGDT_NAME"));
				ingredientsVO.setingdt_Status(rs.getInt("INGDT_STATUS"));
				ingredientsVO.setingdt_Point(rs.getString("INGDT_POINT"));
				ingredientsVO.setingdt_Unit(rs.getString("INGDT_UNIT"));
				ingredientsVO.setingdt_Price(rs.getInt("INGDT_PRICE"));
				ingredientsVO.setingdt_Photo(rs.getBytes("INGDT_PHOTO"));	
				
				ingredientsVOList.add(ingredientsVO);
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
		return ingredientsVOList;
	}

}
