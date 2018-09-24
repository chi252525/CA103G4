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


public class ReportJDBCDAO implements ReportDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	

	// 新增一個貼文被檢舉
	private static final String INSERT_STMT =
			"INSERT INTO REPORT_MSG (MEM_NO,RPLY_NO,RPT_RSM,RPT_TIME,RPT_STATUS) VALUES(?,?,?,SYSDATE,'RS0')";
	
	// 修改貼文留言檢舉狀態
	private static final String UPDATESTATUS_STMT ="UPDATE REPORT_MSG SET RPT_STATUS=?, WHERE MEM_NO=? AND RPLY_NO=?";
	// 傳回全部根據檢舉處理狀況排序，未處理的排上面
		private static final String GET_ALL_STMT = "SELECT * FROM REPORT_MSG ORDER BY RPT_STATUS";
	//傳回單筆
	private static final String GET_ONE_REPORT = "SELECT * FROM REPORT_MSG WHERE MEM_NO=? AND RPLY_NO=?";
	//傳回處理狀況的List
		private static final String GETALL_BYSTATUS_STMT="SELECT * FROM REPORT_MSG WHERE RPT_STATUS=?";
		
	@Override
	public int insert(ReportVO report_MsgVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, report_MsgVO.getMem_No());
			pstmt.setString(2, report_MsgVO.getRply_No());
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
	public void updateStatus(ReportVO report_MsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
	
			pstmt.setString(1, report_MsgVO.getRpt_Status());
			pstmt.setString(2, report_MsgVO.getMem_No());
			pstmt.setString(3, report_MsgVO.getRply_No());
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ReportVO> findbyStatus(String rpt_Status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportVO> reportlist = new ArrayList<>();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL_BYSTATUS_STMT);
			pstmt.setString(1, rpt_Status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO report_MsgVO=new ReportVO();
				report_MsgVO.setMem_No(rs.getString("mem_No"));
				report_MsgVO.setRply_No(rs.getString("rply_No"));
				report_MsgVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				report_MsgVO.setRpt_Status(rs.getString("rpt_Status"));
				report_MsgVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
				reportlist.add(report_MsgVO); 
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportVO report_MsgVO=new ReportVO();
				report_MsgVO.setMem_No(rs.getString("mem_No"));
				report_MsgVO.setRply_No(rs.getString("rply_No"));
				report_MsgVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				report_MsgVO.setRpt_Status(rs.getString("rpt_Status"));
				report_MsgVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
				rpt_Msglist.add(report_MsgVO); 
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
	public ReportVO getOne(String mem_No,String rply_No) {

		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_REPORT);
			pstmt.setString(1, mem_No);
			pstmt.setString(2, rply_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setMem_No(rs.getString("mem_No"));
				reportVO.setRply_No(rs.getString("rply_No"));
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
