package com.reply_msg.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.post.model.PostVO;

public class Reply_MsgDAO implements Reply_MsgDAO_interface  {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "raman";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT="INSERT INTO REPLY_MSG(RPLY_NO,MEM_NO,POST_NO,RPLY_CONT)" + 
			"VALUES (TO_CHAR(SYSDATE,'YYYYMMDDHH24MI')||'-'||LPAD(to_char(REPLY_MSG_seq.NEXTVAL), 2, '0')," + 
			"?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE REPLY_MSG SET RPLY_STATUS=? WHERE RPLY_NO=?";
	private static final String DELETE_STMT = "DELETE FROM POST WHERE RPLY_NO = ?";
	private static final String GETALL = "SELECT * FROM REPLY_MSG";
	@Override
	public void insert(Reply_MsgVO reply_MsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, reply_MsgVO.getMem_No());
			pstmt.setString(2, reply_MsgVO.getPost_No());
			pstmt.setString(3, reply_MsgVO.getRply_Cont());
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
	public void delete(String rply_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, rply_No);
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
	public void update_status(Reply_MsgVO reply_MsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, reply_MsgVO.getRply_No());
			pstmt.setString(2, reply_MsgVO.getRply_Status());
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
	public List<Reply_MsgVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply_MsgVO> activitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Reply_MsgVO reply_MsgVO=new Reply_MsgVO();
				reply_MsgVO.setRply_No(rs.getString("rpt_No"));
				reply_MsgVO.setMem_No(rs.getString("mem_No"));
				reply_MsgVO.setPost_No(rs.getString("post_No"));
				reply_MsgVO.setRply_Cont(rs.getString("rply_Cont"));
				reply_MsgVO.setRply_Time(rs.getTimestamp("rply_Time"));
				activitylist.add(reply_MsgVO); 
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
		return activitylist;
	}

}
