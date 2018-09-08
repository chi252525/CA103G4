package com.report_msg.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Report_MsgDAO implements Report_MsgDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "raman";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String UPDATESTATUS_STMT ="UPDATE REPORT_MSG SET RPT_RSM=? RPT_STATUS=? WHERE RPT_NO=?";
	private static final String FINDBYSTATUS="SELECT * FROM POST WHERE RPT_STATUS=?";
	private static final String GETALL = "SELECT * FROM RPT_MSG";
	@Override
	public void updateStatus(Report_MsgVO report_MsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			pstmt.setString(1, report_MsgVO.getRpt_Rsm());
			pstmt.setString(2, report_MsgVO.getRpt_Status());
			pstmt.setString(3, report_MsgVO.getRpt_No());
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
	public Report_MsgVO findbyStatus(String rpt_Status) {
		Report_MsgVO report_MsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYSTATUS);
			pstmt.setString(1, rpt_Status);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				report_MsgVO=new Report_MsgVO();
				report_MsgVO.setRpt_No(rs.getString("rpt_No"));
				report_MsgVO.setMem_No(rs.getString("mem_No"));
				report_MsgVO.setPost_No(rs.getString("post_No"));
				report_MsgVO.setRpt_Cont(rs.getString("rpt_Cont"));
				report_MsgVO.setRpt_Rsm(rs.getString("rpt_Rsm"));
				report_MsgVO.setRpt_Status(rs.getString("rpt_Status"));
				report_MsgVO.setRpt_Time(rs.getTimestamp("rpt_Time"));
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
		return report_MsgVO;
	}

	@Override
	public List<Report_MsgVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Report_MsgVO> rpt_Msglist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Report_MsgVO report_MsgVO=new Report_MsgVO();
				report_MsgVO.setRpt_No(rs.getString("rpt_No"));
				report_MsgVO.setMem_No(rs.getString("mem_No"));
				report_MsgVO.setPost_No(rs.getString("post_No"));
				report_MsgVO.setRpt_Cont(rs.getString("rpt_Cont"));
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

}
