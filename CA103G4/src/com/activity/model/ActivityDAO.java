package com.activity.model;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
public class ActivityDAO implements ActivityDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "raman";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT ="INSERT INTO ACTIVITY(" + 
			"ACT_NO,COUCAT_NO,ACT_CAT,ACT_NAME," + 
			"ACT_CAROUSEL,ACT_CMIMETYPE,ACT_Pic,ACT_PMIMETYPE,ACT_CONTENT,ACT_START,ACT_END,ACT_USECOU)" + 
			"VALUES(to_char(sysdate,'yyyymm')||'-'||LPAD(to_char(ACTIVITY_seq.NEXTVAL), 4," + 
			"'0')," + 
			"?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE ACTIVITY SET Coucat_No=?,ACT_CAT=?,ACT_NAME=?,ACT_CAROUSEL=?,ACT_CMIMETYPE=?,ACT_PIC=?,ACT_PMIMETYPE=?,ACT_CONTENT=?," + 
			"ACT_START=?," + 
			"ACT_END=?,ACT_USECOU=? WHERE ACT_NO=?";
	private static final String GET_ONE_STMT = "SELECT * FROM ACTIVITY WHERE ACT_NO=?";
	private static final String FINDBYDATEBETWEEN = 
    		"SELECT * FROM activity WHERE act_start BETWEEN ? AND ? AND " + 
    				"act_End BETWEEN ? and ?";
	private static final String GETALL = 
			"SELECT * FROM ACTIVITY";
	private static final String FINDBYACTCATA ="SELECT * FROM ACTIVITY WHERE ACT_CAT=?";
			
	static {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		}
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, activityVO.getCoucat_No());
			pstmt.setString(2, activityVO.getAct_Cat());
			pstmt.setString(3, activityVO.getAct_Name());
			pstmt.setBytes(4, activityVO.getAct_Carousel());
			pstmt.setString(5, activityVO.getAct_Cmimetype());
			pstmt.setBytes(6, activityVO.getAct_Pic());
			pstmt.setString(7, activityVO.getAct_Pmimetype());
			pstmt.setString(8, activityVO.getAct_Content());
			pstmt.setTimestamp(9, activityVO.getAct_Start());
			pstmt.setTimestamp(10, activityVO.getAct_End());
			pstmt.setString(11, activityVO.getAct_Usecou());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");

			// Handle any SQL errors
		}catch (SQLException se) {
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
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, activityVO.getCoucat_No());
			pstmt.setString(2, activityVO.getAct_Cat());
			pstmt.setString(3, activityVO.getAct_Name());
			pstmt.setBytes(4, activityVO.getAct_Carousel());
			pstmt.setString(5, activityVO.getAct_Cmimetype());
			pstmt.setBytes(6, activityVO.getAct_Pic());
			pstmt.setString(7, activityVO.getAct_Pmimetype());
			pstmt.setString(8, activityVO.getAct_Content());
			pstmt.setTimestamp(9, activityVO.getAct_Start());
			pstmt.setTimestamp(10, activityVO.getAct_End());
			pstmt.setString(11, activityVO.getAct_Usecou());
			pstmt.setString(12, activityVO.getAct_No());
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");

			// Handle any SQL errors
		}catch (SQLException se) {
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
	public ActivityVO findByDate_between(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYDATEBETWEEN);
			pstmt.setTimestamp(1, act_Start1);
			pstmt.setTimestamp(2, act_Start2);
			pstmt.setTimestamp(3, act_End1);
			pstmt.setTimestamp(4, act_End2);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return activityVO;
	}
	

	@Override
	public List<ActivityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActivityVO activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activitylist.add(activityVO); 
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
 
	@Override
	public ActivityVO findByAct_Cata(String act_Cata) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYACTCATA);
			pstmt.setString(1, act_Cata);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
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
		return activityVO;
	}

	@Override
	public ActivityVO findByPrimaryKey(String act_No) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, act_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
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
		return activityVO;
	}
	

	

	

			
		
}