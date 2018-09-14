package com.feature.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeatureJDBCDAO implements FeatureDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Test";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO FEATURE VALUES('F'||LPAD(to_char(feature_seq.NEXTVAL), 3, '0'),?)";
	private static final String UPDATE_STMT =
			"UPDATE FEATURE SET FEA_NAME=? WHERE FEA_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM FEATURE WHERE FEA_NO=?";
	private static final String GET_ONE_STMT =
			"SELECT FEA_NO,FEA_NAME FROM FEATURE WHERE FEA_NO=?";
	private static final String GET_ALL_STMT =
			"SELECT FEA_NO,FEA_NAME FROM FEATURE ORDER BY FEA_NO";
	

	@Override
	public void insert(FeatureVO feaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, feaVO.getFea_Name());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(FeatureVO feaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, feaVO.getFea_Name());
			pstmt.setString(2, feaVO.getFea_No());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String fea_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, fea_No);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public FeatureVO findByPrimaryKey(String fea_No) {
		FeatureVO feaVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fea_No);
			rs = pstmt.executeQuery();
			rs.next();
			feaVO = new FeatureVO();
			feaVO.setFea_No(rs.getString("FEA_NO"));
			feaVO.setFea_Name(rs.getString("FEA_NAME"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return feaVO;
	}

	@Override
	public List<FeatureVO> getAll() {
		List<FeatureVO> list = new ArrayList<>();
		FeatureVO feaVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				feaVO = new FeatureVO();
				feaVO.setFea_No(rs.getString("FEA_NO"));
				feaVO.setFea_Name(rs.getString("FEA_NAME"));
				list.add(feaVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		FeatureDAO dao = new FeatureDAO();
		//insert
		FeatureVO feaVO1 = new FeatureVO();
		feaVO1.setFea_Name("新增功能");
		dao.insert(feaVO1);
		//update
		FeatureVO feaVO2 = new FeatureVO();
		feaVO2.setFea_No("F022");
		feaVO2.setFea_Name("修改功能");
		dao.update(feaVO2);
		//delete
		dao.delete("F001");
		//select one
		FeatureVO feaVO3 = new FeatureVO();
		feaVO3 = dao.findByPrimaryKey("F002");
		System.out.println(feaVO3.getFea_No());
		System.out.println(feaVO3.getFea_Name());
		//select all
		List<FeatureVO> list = dao.getAll();
		for(FeatureVO aFea : list) {
			System.out.print(aFea.getFea_No()+" ");
			System.out.println(aFea.getFea_Name());
		}
		
	}
	
}