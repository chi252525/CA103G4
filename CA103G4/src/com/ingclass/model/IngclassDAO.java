package com.ingclass.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class IngclassDAO implements IngclassDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT=
			"INSERT INTO INGCLASS" 
					+"(INGDTC_ID, INGDTC_NAME)"
					+"VALUES(('T'||LPAD(to_char(MENU_seq.NEXTVAL),4,'0')),?)";
	private static final String UPDATE_STMT =
			"UPDATE INGCLASS SET INGDTC_NAME=? WHERE INGDTC_ID=?";
	private static final String DELETE_STMT =
			"DELETE FROM INGCLASS WHERE INGDTC_ID=?";
	private static final String SELECT_ONE_STMT=
			"SELECT INGDTC_ID, INGDTC_NAME FROM INGCLASS WHERE INGDTC_ID=?";
	private static final String SELECT_ALL_STMT=
			"SELECT INGDTC_ID, INGDTC_NAME FROM INGCLASS ORDER BY INGDTC_ID";
	
	@Override
	public void insert(IngclassVO ingclassVO) {
		// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(INSERT_STMT);

//				pstmt.setString(1, ingclassVO.getIngdtc_Id());
				pstmt.setString(1, ingclassVO.getIngdtc_Name());
				
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
	public void update(IngclassVO ingclassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, ingclassVO.getIngdtc_Name());
			pstmt.setString(2, ingclassVO.getIngdtc_Id());
			
			
			
			
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
	public void delete(String Ingdtc_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Ingdtc_Id);
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
	public IngclassVO findByPrimaryKey(String Ingdtc_Id) {
		IngclassVO ingclassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, Ingdtc_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingclassVO = new IngclassVO();
				ingclassVO.setIngdtc_Id(rs.getString("Ingdtc_Id"));
				ingclassVO.setIngdtc_Name(rs.getString("Ingdtc_Name"));
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
		return ingclassVO;
	}



	@Override
	public List<IngclassVO> getAll() {
		List<IngclassVO> ingclassVOList = new ArrayList<>();
		IngclassVO ingclassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingclassVO = new IngclassVO();
				ingclassVO.setIngdtc_Id(rs.getString("INGDTC_ID"));
				ingclassVO.setIngdtc_Name(rs.getString("INGDTC_NAME"));
				ingclassVOList.add(ingclassVO);
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
		return ingclassVOList;
	}
	

}
