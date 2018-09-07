package com.sysnt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SysntDAO implements SysntDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Test";
	String passwd = "123456";
	
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ntVO.getNt_Tittle());
			pstmt.setString(2, ntVO.getNt_Cont());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, ntVO.getNt_Tittle());
			pstmt.setString(2, ntVO.getNt_Cont());
			pstmt.setString(3, ntVO.getNt_No());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, nt_No);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, nt_No);
			rs = pstmt.executeQuery();
			rs.next();
			ntVO = new SysntVO();
			ntVO.setNt_No(rs.getString("NT_NO"));
			ntVO.setNt_Tittle(rs.getString("NT_TITTLE"));
			ntVO.setNt_Cont(rs.getString("NT_CONT"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ntVO = new SysntVO();
				ntVO.setNt_No(rs.getString("NT_NO"));
				ntVO.setNt_Tittle(rs.getString("NT_TITTLE"));
				ntVO.setNt_Cont(rs.getString("NT_CONT"));
				list.add(ntVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		SysntDAO dao = new SysntDAO();
		//insert
		SysntVO sysntVO1 = new SysntVO();
		sysntVO1.setNt_Tittle("新增通知");
		sysntVO1.setNt_Cont("裡面有一堆內容，裡面有一堆內容，裡面有一堆內容，裡面有一堆內容，裡面有一堆內容");
		dao.insert(sysntVO1);
		//update
		SysntVO sysntVO2 = new SysntVO();
		sysntVO2.setNt_No("NT05");
		sysntVO2.setNt_Tittle("更新通知");
		sysntVO2.setNt_Cont("更新了一堆內容，更新了一堆內容，更新了一堆內容，更新了一堆內容，更新了一堆內容");
		dao.update(sysntVO2);
		//delete
		dao.delete("NT06");
		//select one
		SysntVO sysntVO3 = new SysntVO();
		sysntVO3 = dao.findByPrimaryKey("NT01");
		System.out.print(sysntVO3.getNt_No()+" ");
		System.out.print(sysntVO3.getNt_Tittle()+" ");
		System.out.println(sysntVO3.getNt_Cont());
		//select all
		List<SysntVO> list = new ArrayList<>();
		list = dao.getAll();
		for(SysntVO ntVO : list) {
			System.out.print(ntVO.getNt_No()+" ");
			System.out.print(ntVO.getNt_Tittle()+" ");
			System.out.println(ntVO.getNt_Cont());
		}
	}
}