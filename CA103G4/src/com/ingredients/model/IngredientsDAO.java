package com.ingredients.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IngredientsDAO implements IngredientsDAO_interface{
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT=		
			"INSERT INTO INGREDIENTS (INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE)"
					+"VALUES(('I'||LPAD(to_char(INGREDIENTS_seq.NEXTVAL), 4, '0')),?,?,?,?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE INGREDIENTS SET, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE WHERE INGDT_ID=?";
	private static final String DELETE_STMT =
			"DELETE FROM INGREDIENTS WHERE INGDT_ID=?";
	private static final String SELECT_ONE_STMT=
			"SELECT INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE FROM INGREDIENTS WHERE INGDT_ID=?";
	private static final String SELECT_ALL_STMT=
			"SELECT INGDT_ID, INGDTC_ID, INGDT_NAME, INGDT_STATUS, INGDT_POINT, INGDT_UNIT, INGDT_PRICE FROM INGREDIENTS ORDER BY INGDT_ID";


	

	@Override
	public void insert(IngredientsVO ingredientsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ingredientsVO.getIngdt_Id());
			pstmt.setString(2, ingredientsVO.getIngdtc_Id());
			pstmt.setString(3, ingredientsVO.getIngdt_Name());
			pstmt.setInt(4, ingredientsVO.getIngdt_Status());
			pstmt.setString(5, ingredientsVO.getIngdt_Point());
			pstmt.setString(6, ingredientsVO.getIngdt_Unit());
			pstmt.setInt(7, ingredientsVO.getIngdt_Price());
			
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			
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
	public void update(IngredientsVO ingredientsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, ingredientsVO.getIngdt_Id());
			pstmt.setString(2, ingredientsVO.getIngdtc_Id());
			pstmt.setString(3, ingredientsVO.getIngdt_Name());
			pstmt.setInt(4, ingredientsVO.getIngdt_Status());
			pstmt.setString(5, ingredientsVO.getIngdt_Point());
			pstmt.setString(6, ingredientsVO.getIngdt_Unit());
			pstmt.setInt(7, ingredientsVO.getIngdt_Price());
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");
			
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
	public void delete(String Ingdt_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Ingdt_Id);
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
	public IngredientsVO findByPrimaryKey(String Ingdt_Id) {
		IngredientsVO ingredientsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, Ingdt_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingredientsVO = new IngredientsVO();
				ingredientsVO.setIngdt_Id(rs.getString("INGDT_ID"));
				ingredientsVO.setIngdtc_Id(rs.getString("INGDTC_ID"));
				ingredientsVO.setIngdt_Name(rs.getString("INGDT_NAME"));
				ingredientsVO.setIngdt_Status(rs.getInt("INGDT_STATUS"));
				ingredientsVO.setIngdt_Point(rs.getString("INGDT_POINT"));
				ingredientsVO.setIngdt_Unit(rs.getString("INGDT_UNIT"));
				ingredientsVO.setIngdt_Price(rs.getInt("INGDT_PRICE"));		
				
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingredientsVO = new IngredientsVO();
				ingredientsVO.setIngdt_Id(rs.getString("INGDT_ID"));
				ingredientsVO.setIngdtc_Id(rs.getString("INGDTC_ID"));
				ingredientsVO.setIngdt_Name(rs.getString("INGDT_NAME"));
				ingredientsVO.setIngdt_Status(rs.getInt("INGDT_STATUS"));
				ingredientsVO.setIngdt_Point(rs.getString("INGDT_POINT"));
				ingredientsVO.setIngdt_Unit(rs.getString("INGDT_UNIT"));
				ingredientsVO.setIngdt_Price(rs.getInt("INGDT_PRICE"));		
				
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
