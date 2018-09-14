package com.perntd.model;

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

public class PerntdDAO implements PerntdDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO PERNTD VALUES('P'||LPAD(to_char(perntd_seq.NEXTVAL),6,'0'),?,?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE PERNTD SET MEM_NO=?,NT_NO=?,PERNTD_CONT=?,PERNTD_DATE=? WHERE PERNTD_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM PERNTD WHERE PERNTD_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT PERNTD_NO,MEM_NO,NT_NO,PERNTD_CONT,PERNTD_DATE FROM PERNTD WHERE PERNTD_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT PERNTD_NO,MEM_NO,NT_NO,PERNTD_CONT,PERNTD_DATE FROM PERNTD ORDER BY PERNTD_NO";

	@Override
	public void insert(PerntdVO perntdVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, perntdVO.getMem_No());
			pstmt.setString(2, perntdVO.getNt_No());
			pstmt.setString(3, perntdVO.getPerntd_Cont());
			pstmt.setString(4, perntdVO.getPerntd_Date());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(PerntdVO perntdVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, perntdVO.getMem_No());
			pstmt.setString(2, perntdVO.getNt_No());
			pstmt.setString(3, perntdVO.getPerntd_Cont());
			pstmt.setString(4, perntdVO.getPerntd_Date());
			pstmt.setString(5, perntdVO.getPerntd_No());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void delete(String perntd_No) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, perntd_No);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public PerntdVO findByPrimaryKey(String perntd_No) {
		// TODO Auto-generated method stub
		PerntdVO perntdVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, perntd_No);
			rs = pstmt.executeQuery();
			rs.next();
			perntdVO = new PerntdVO();
			perntdVO.setPerntd_No(rs.getString("PERNTD_NO"));
			perntdVO.setMem_No(rs.getString("MEM_NO"));
			perntdVO.setNt_No(rs.getString("NT_NO"));
			perntdVO.setPerntd_Cont(rs.getString("PERNTD_CONT"));
			perntdVO.setPerntd_Date(rs.getString("PERNTD_DATE"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return perntdVO;
	}

	@Override
	public List<PerntdVO> getAll() {
		// TODO Auto-generated method stub
		PerntdVO perntdVO = null;
		List<PerntdVO> list = new ArrayList<>();;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				perntdVO = new PerntdVO();
				perntdVO.setPerntd_No(rs.getString("PERNTD_NO"));
				perntdVO.setMem_No(rs.getString("MEM_NO"));
				perntdVO.setNt_No(rs.getString("NT_NO"));
				perntdVO.setPerntd_Cont(rs.getString("PERNTD_CONT"));
				perntdVO.setPerntd_Date(rs.getString("PERNTD_DATE"));
				list.add(perntdVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
}