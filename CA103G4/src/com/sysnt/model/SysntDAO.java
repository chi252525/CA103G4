package com.sysnt.model;

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

public class SysntDAO implements SysntDAO_interface{
	
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
			"INSERT INTO SYSNT VALUES('NT'||LPAD(to_char(sysnt_seq.NEXTVAL),2,'0'),?,?)";
	private static final String UPDATE_STMT =
			"UPDATE SYSNT SET NT_TITTLE=?,NT_CONT=? WHERE NT_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM SYSNT WHERE NT_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT NT_NO,NT_TITTLE,NT_CONT FROM SYSNT WHERE NT_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT NT_NO,NT_TITTLE,NT_CONT FROM SYSNT ORDER BY NT_NO";
			

	@Override
	public void insert(SysntVO ntVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ntVO.getNt_Tittle());
			pstmt.setString(2, ntVO.getNt_Cont());
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
	public void update(SysntVO ntVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, ntVO.getNt_Tittle());
			pstmt.setString(2, ntVO.getNt_Cont());
			pstmt.setString(3, ntVO.getNt_No());
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
	public void delete(String nt_No) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, nt_No);
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
	public SysntVO findByPrimaryKey(String nt_No) {
		// TODO Auto-generated method stub
		SysntVO ntVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, nt_No);
			rs = pstmt.executeQuery();
			rs.next();
			ntVO = new SysntVO();
			ntVO.setNt_No(rs.getString("NT_NO"));
			ntVO.setNt_Tittle(rs.getString("NT_TITTLE"));
			ntVO.setNt_Cont(rs.getString("NT_CONT"));
			
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
		return ntVO;
	}

	@Override
	public List<SysntVO> getAll() {
		// TODO Auto-generated method stub
		SysntVO ntVO = null;
		List<SysntVO> list = new ArrayList<>();;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ntVO = new SysntVO();
				ntVO.setNt_No(rs.getString("NT_NO"));
				ntVO.setNt_Tittle(rs.getString("NT_TITTLE"));
				ntVO.setNt_Cont(rs.getString("NT_CONT"));
				list.add(ntVO);
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