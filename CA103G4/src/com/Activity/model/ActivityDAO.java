package com.Activity.model;
import java.util.*;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ActivityDAO implements ActivityDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "raman";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = 
			"INSERT INTO ACTIVITY" + 
			"(ACT_NO,COUCAT_NO,ACT_CAT,ACT_NAME, ACT_CAROUSEL, ACT_PIC,ACT_CONTENT,ACT_START,ACT_END)VALUES(to_char(sysdate,'yyyymm')||'-'||LPAD(to_char(ACTIVITY_seq.NEXTVAL),4,'0'),?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'))";
	private static final String UPDATE_STMT = 
			"UPDATE ACTIVITY SET Coucat_No=?,ACT_CAT=?,ACT_NAME=?,ACT_START=TO_DATE(?,'yyyy-mm-dd'),ACT_END=TO_DATE(?,'yyyy-mm-dd'),ACT_CAROUSEL=?,ACT_PIC=?,ACT_CONTENT=? WHERE ACT_NO=?";
    private static final String FINDBYDATEBETWEEN = 
			"SELECT * FROM activity WHERE act_start " + 
			"BETWEEN TO_DATE(?,'yyyy-mm-dd')AND " + 
			"TO_DATE(?,'yyyy-mm-dd')" + 
			"AND " + 
			"act_End BETWEEN TO_DATE(?,'yyyy-mm-dd')" + 
			"and TO_DATE(?,'yyyy-mm-dd')";
    private static final String FINDENDED = 
			"SELECT * FROM ACTIVITY WHERE ACT_End < SYSDATE";
    private static final String FINDONGOING = 
			"SELECT * FROM ACTIVITY WHERE ACT_End < SYSDATE";
    private static final String FINDBFSTART = 
			"SELECT * FROM ACTIVITY WHERE ACT_Start > SYSDATE";
	private static final String GETALL = 
			"SELECT * FROM ACTIVITY";
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
			byte[] pic = getPictureByteArray("items/Bing2.jpeg");
			pstmt.setBytes(4, pic);
			byte[] pic2 = getPictureByteArray("items/Bing2.jpeg");
			pstmt.setBytes(5, pic2);
			pstmt.setString(6, activityVO.getAct_Content());
			pstmt.setTimestamp(7, activityVO.getAct_Start());
			pstmt.setTimestamp(8, activityVO.getAct_End());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			pstmt.setString(9, activityVO.getAct_No());
			pstmt.setString(1, activityVO.getCoucat_No());
			pstmt.setString(2, activityVO.getAct_Cat());
			pstmt.setString(3, activityVO.getAct_Name());
			pstmt.setTimestamp(4, activityVO.getAct_Start());
			pstmt.setTimestamp(5, activityVO.getAct_End());
			byte[] pic = getPictureByteArray("items/Bing2.jpeg");
			pstmt.setBytes(6, pic);
			byte[] pic2 = getPictureByteArray("items/Bing2.jpeg");
			pstmt.setBytes(7, pic2);
			pstmt.setString(8, activityVO.getAct_Content());
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public ActivityVO findByDate_between(String act_Start1,String act_Start2,String act_End1,String act_End2) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYDATEBETWEEN);
			pstmt.setString(1, act_Start1);
			pstmt.setString(2, act_Start2);
			pstmt.setString(3, act_End1);
			pstmt.setString(4, act_End2);
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
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activitylist.add(activityVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se);
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
	public List<ActivityVO> findended() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDENDED);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActivityVO activityVO=new ActivityVO();
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activitylist.add(activityVO); // Store the row in the list
			}

			// Handle any SQL errors
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
	public List<ActivityVO> findonGoing() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDONGOING);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActivityVO activityVO=new ActivityVO();
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activitylist.add(activityVO); // Store the row in the list
			}

			// Handle any SQL errors
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
	public List<ActivityVO> findbfStart() {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<ActivityVO> activitylist = new ArrayList<>();

	try {
		con = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Connecting to database successfully! (連線成功！)");
		pstmt = con.prepareStatement(FINDBFSTART);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			ActivityVO activityVO=new ActivityVO();
			activityVO=new ActivityVO();
			activityVO.setAct_No(rs.getString("act_No"));
			activityVO.setCoucat_No(rs.getString("coucat_No"));
			activityVO.setAct_Cat(rs.getString("act_Cat"));
			activityVO.setAct_Name(rs.getString("act_Name"));
			activityVO.setAct_Content(rs.getString("act_Content"));
			activityVO.setAct_Start(rs.getTimestamp("act_Start"));
			activityVO.setAct_End(rs.getTimestamp("act_End"));
			activitylist.add(activityVO); // Store the row in the list
		}

		// Handle any SQL errors
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
	
	
	// 使用byte[]方式
			public static byte[] getPictureByteArray(String path) throws IOException {
				File file = new File(path);
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[fis.available()];
				int i;
				while ((i = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
					//write(byte[] b, int off, int len) 
			        //?指定 byte ??中?偏移量 off ?始的 len ?字??入此 byte ???出流。
				}
				baos.close();
				fis.close();

				return baos.toByteArray();
				//  toByteArray() 獲取數據。
			}
		
}
