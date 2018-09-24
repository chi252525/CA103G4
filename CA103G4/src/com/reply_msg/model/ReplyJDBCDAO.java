package com.reply_msg.model;

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


public class ReplyJDBCDAO implements ReplyDAO_interface  {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//新增一個留言
	private static final String INSERT_STMT="INSERT INTO RPLY_MSG(RPLY_NO,MEM_NO,POST_NO,RPLY_CONT)VALUES (TO_CHAR(SYSDATE,'YYYYMMDDHH24MI')||'-'||LPAD(to_char(RPLY_MSG_seq.NEXTVAL), 2,'0'),?,?,?)";
	//修改一個留言
	private static final String UPDATE_STMT = 
			"UPDATE RPLY_MSG SET RPLY_CONT=?, RPLY_STATUS=? ,RPLY_TIME=? WHERE RPLY_NO=?";
	//刪除單一留言
		private static final String DELETE_STMT = "DELETE FROM RPLY_MSG WHERE RPLY_NO = ?";
	//取一個貼文的所有留言
	private static final String GET_ALL_BY_POSTNO = "SELECT * FROM RPLY_MSG WHERE POST_NO=?";
	// 修改此會員在單一貼文的留言狀態-隱藏或顯示 
	private static final String UPDATE_STATUS_STMT = "UPDATE RPLY_MSG SET RPLY_STATUS = ? WHERE MEM_NO = ? AND POST_NO = ?";
	
	//取全部
		private static final String GETALL = "SELECT * FROM RPLY_MSG";
	
	// 修改留言狀態-隱藏或顯示 FORBACKEND
	private static final String UPDATE_STATUS_FOR_BACKEND_STMT = "UPDATE RPLY_MSG SET RPLY_STATUS = ? WHERE RPLY_NO = ? ";
	
	@Override
	public void insert(ReplyVO replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, replyVO.getMem_No());
			pstmt.setString(2, replyVO.getPost_No());
			pstmt.setString(3, replyVO.getRply_Cont());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			// Handle any SQL errors
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
	public void update(ReplyVO replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, replyVO.getRply_Cont());
			pstmt.setString(2, replyVO.getRply_Status());
			pstmt.setTimestamp(3, replyVO.getRply_Time());
			pstmt.setString(4, replyVO.getRply_No());
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
	public int delete(String rply_No) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, rply_No);

			updateCount = pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}


	@Override
	public List<ReplyVO> findByPostNo(String post_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyVO> msglist = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ALL_BY_POSTNO);
			pstmt.setString(1, post_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyVO replyVO=new ReplyVO();
				replyVO.setRply_No(rs.getString("rply_No"));
				replyVO.setMem_No(rs.getString("mem_No"));
				replyVO.setPost_No(rs.getString("post_No"));
				replyVO.setRply_Cont(rs.getString("rply_Cont"));
				replyVO.setRply_Time(rs.getTimestamp("rply_Time"));
				msglist.add(replyVO); 
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
		return msglist;
	}
	


	@Override
	public int updateStatus(String post_No, String mem_No, String rply_Status) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setString(1, rply_Status);
			pstmt.setString(2, mem_No);
			pstmt.setString(3, post_No);

			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}
	@Override
	public int updateStatusForBackEnd( String rply_Status,String mem_No) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_FOR_BACKEND_STMT);

			pstmt.setString(1, rply_Status);
			pstmt.setString(2, mem_No);

			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}

	@Override
	public List<ReplyVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyVO> msglist = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyVO replyVO=new ReplyVO();
				replyVO.setRply_No(rs.getString("rply_No"));
				replyVO.setMem_No(rs.getString("mem_No"));
				replyVO.setPost_No(rs.getString("post_No"));
				replyVO.setRply_Cont(rs.getString("rply_Cont"));
				replyVO.setRply_Time(rs.getTimestamp("rply_Time"));
				msglist.add(replyVO); 
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
		return msglist;
	}


}
