package com.report_msg.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.post.model.PostVO;
import com.reply_msg.model.ReplyVO;


public class ReportDAO implements ReportDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	// 新增一個貼文被檢舉
	private static final String INSERT_STMT =
			"INSERT INTO REPORT_Msg(RPT_NO,MEM_NO,POST_NO,RPT_RSM,RPT_STATUS,RPT_TIME)VALUES "
			+ "(TO_CHAR(SYSDATE,'YYYYMMDDHH24MI')||'-'||LPAD(to_char(RPT_MSG_seq.NEXTVAL), 2, '0'),"
			+ "?,?,?,'RS0',sysdate)";	
	// 修改檢舉的處理狀態為已處理
	private static final String UPDATESTATUS_STMT ="UPDATE REPORT_MSG SET RPT_STATUS='RS1' WHERE RPT_NO=?";
	// 傳回全部根據檢舉處理狀況排序，未處理的排上面
		private static final String GET_ALL_STMT = "SELECT * FROM REPORT_MSG ORDER BY RPT_STATUS ";
	//傳回根據會員檢舉的單筆
	private static final String GET_ONE_REPORT = "SELECT * FROM REPORT_MSG WHERE RPT_NO=?";
	//傳回處理狀況的List
		private static final String GETALL_BYSTATUS_STMT="SELECT * FROM REPORT_MSG WHERE RPT_STATUS=?";
		
	@Override
	public int insert(ReportVO report_MsgVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, report_MsgVO.getMem_No());
			pstmt.setString(2, report_MsgVO.getPost_No());
			pstmt.setString(3, report_MsgVO.getRpt_Rsm());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	}
	
	@Override
	public int updateReportStatus(String rpt_No) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			pstmt.setString(1, rpt_No);

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
	public List<ReportVO> findbyStatus(String rpt_Status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportVO> reportlist = new ArrayList<>();
		try {
			con = ds.getConnection();
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL_BYSTATUS_STMT);
			pstmt.setString(1, rpt_Status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO reportVO=new ReportVO();
				reportVO.setRpt_No(rs.getString("rpt_No"));
				reportVO.setMem_No(rs.getString("mem_No"));
				reportVO.setPost_No(rs.getString("post_No"));
				reportVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				reportVO.setRpt_Status(rs.getString("rpt_Status"));
				reportVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
				reportlist.add(reportVO); 
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
		return reportlist;}

	@Override
	public List<ReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportVO> rpt_Msglist = new ArrayList<>();

		try {
			con = ds.getConnection();
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportVO reportVO=new ReportVO();
				reportVO.setRpt_No(rs.getString("rpt_No"));
				reportVO.setMem_No(rs.getString("mem_No"));
				reportVO.setPost_No(rs.getString("post_No"));
				reportVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				reportVO.setRpt_Status(rs.getString("rpt_Status"));
				reportVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
				rpt_Msglist.add(reportVO); 
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
		return rpt_Msglist;
	}

	@Override
	public ReportVO getOneReport(String rpt_No) {

		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_REPORT);
		
			pstmt.setString(1, rpt_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRpt_No(rs.getString("rpt_No"));
				reportVO.setMem_No(rs.getString("mem_No"));
				reportVO.setPost_No(rs.getString("post_No"));
				reportVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				reportVO.setRpt_Status(rs.getString("rpt_Status"));
				reportVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
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
		return reportVO;
	
	}

	

}
