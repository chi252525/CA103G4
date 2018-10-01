package com.activity.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
public class ActivityJDBCDAO implements ActivityDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//新增一個 廣告
	private static final String INSERT_STMT ="INSERT INTO ACTIVITY(" + 
			"ACT_NO,COUCAT_NO,ACT_CAT,ACT_NAME," + 
			"ACT_CAROUSEL,ACT_Pic,ACT_CONTENT,act_PreAddTime,act_PreOffTime,ACT_START,ACT_END,act_Status,act_Views)" + 
			"VALUES(to_char(sysdate,'yyyymm')||'-'||LPAD(to_char(ACTIVITY_seq.NEXTVAL), 4," + 
			"'0')," + 
			"?,?,?,?,?,?,?,?,?,?,'AS1',0)";
	////修改廣告資訊(必須在下架狀態才能修改)
	private static final String UPDATE_STMT = 
			"UPDATE ACTIVITY SET Coucat_No=?,ACT_CAT=?,ACT_NAME=?,ACT_CAROUSEL=?,ACT_PIC=?,ACT_CONTENT=?,act_PreAddTime=?,act_PreOffTime=?" + 
			  "WHERE ACT_NO=?";
	//取得一個廣告活動
	private static final String GET_ONE_STMT = "SELECT * FROM ACTIVITY WHERE ACT_NO=?";
	
	///***更動廣告為馬上上架***(上架/下架也會更新成實際上下架時間)
	private static final String UPDATE_ADONSTAT_STMT="UPDATE ACTIVITY SET act_Status=1,act_PreAddTime=SYSDATE,ACT_START=SYSDATE,act_PreOffTime=? WHERE ACT_NO=?";
	
	//更動廣告為馬上下架
	private static final String UPDATE_ADOFFSTAT_STMT=
			"UPDATE ACTIVITY SET act_Status=0,act_PreOffTime=SYSDATE,ACT_END=SYSDATE WHERE ACT_NO=?";

	///瀏覽數++
	private static final String UPDATE_CLICK_STMT="UPDATE ACTIVITY SET act_Views=act_Views+1 WHERE ACT_NO=?";
	
	///以瀏覽數高低排序
	private static final String FINDHOT_STMT=
			"SELECT * FROM ACTIVITY WHERE act_Status=1 ORDER BY act_Views DESC";
	
	//查詢已上架的最新廣告
	private static final String FINDNEW_STMT=
			"SELECT * FROM ACTIVITY WHERE act_Status=1 ORDER BY ACT_START DESC";

	//以時間區間搜尋廣告活動
	private static final String FINDBYDATEBETWEEN = 
    		"SELECT * FROM activity WHERE act_start BETWEEN ? AND ? AND " + 
    				"act_End BETWEEN ? and ?";
	
	//以種類搜尋廣告活動
	private static final String FINDBYACTCATA ="SELECT * FROM ACTIVITY WHERE ACT_CAT=?";
	
	//以欲上架時間最近的的活動排序取得全部
		private static final String GETALL = 
				"SELECT * FROM ACTIVITY WHERE act_Status=1 ORDER BY act_PreAddTime DESC";
	
	
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
			pstmt.setBytes(5, activityVO.getAct_Pic());
			pstmt.setString(6, activityVO.getAct_Content());
			pstmt.setTimestamp(7, activityVO.getAct_PreAddTime());
			pstmt.setTimestamp(8, activityVO.getAct_PreOffTime());
			pstmt.setTimestamp(9, activityVO.getAct_Start());
			pstmt.setTimestamp(10, activityVO.getAct_End());
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
			pstmt.setBytes(5, activityVO.getAct_Pic());
			pstmt.setString(6, activityVO.getAct_Content());
			pstmt.setTimestamp(7, activityVO.getAct_PreAddTime());
			pstmt.setTimestamp(8, activityVO.getAct_PreOffTime());
			pstmt.setString(9, activityVO.getAct_No());
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
	public List<ActivityVO> findByDate_between(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activitylist = new ArrayList<>();

		try {
			con =DriverManager.getConnection(URL, USER, PASSWORD);
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
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
				activitylist.add(activityVO);
				
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
		return activitylist;
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
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
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
	public List<ActivityVO> findByAct_Cata(String act_Cata) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activitylist = new ArrayList<>();
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
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
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
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
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
	public int updateAct(String act_No, Integer act_Status, ActivityVO activityVO) {
		int count=0;
		Connection con = null ;
		PreparedStatement pstmt= null;
		
		if(act_Status == 1) {
			try {
				
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt=con.prepareStatement(UPDATE_ADONSTAT_STMT);
				
				//假設上架時間大於原有預計下架時間時，把預計下架時間跟實際下架時間清空
				if(System.currentTimeMillis() >= activityVO.getAct_PreOffTime().getTime() ) {
					pstmt.setTimestamp(1,null);
					pstmt.setTimestamp(2,null);
					pstmt.setString(3,act_No);
				}else {
					pstmt.setTimestamp(1,activityVO.getAct_PreOffTime());
					pstmt.setTimestamp(2,null);
					pstmt.setString(3,act_No);
				}
				
				count=pstmt.executeUpdate();
				
			}catch(SQLException se) {
				throw new RuntimeException("資料庫發生錯誤"+se.getMessage());
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				
				if(con != null) {
					try {
					  con.close();
					}catch(Exception e) {
					  e.printStackTrace(System.err);
					}
				}
			}
			
		}else if(act_Status == 0) {		
			try {
				Class.forName(DRIVER);
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt=con.prepareStatement(UPDATE_ADOFFSTAT_STMT);
				
				pstmt.setString(1,act_No);
				
				count=pstmt.executeUpdate();
				
			}catch(ClassNotFoundException ce) {
				throw new RuntimeException("資料庫無法載入驅動"+ce.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("資料庫發生錯誤"+se.getMessage());
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				
				if(con != null) {
					try {
					  con.close();
					}catch(Exception e) {
					  e.printStackTrace(System.err);
					}
				}
			}
			
			
		}
		return count;
	}

	@Override
	public int updateAct_Views(String act_No) {
		int count=0;
		Connection con = null ;
		PreparedStatement pstmt= null;
		
		try {
			
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(UPDATE_CLICK_STMT);
			
			pstmt.setString(1,act_No);	
			
			count=pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("資料庫發生錯誤"+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if(con != null) {
				try {
				  con.close();
				}catch(Exception e) {
				  e.printStackTrace(System.err);
				}
			}
		}
		
		return count;
	}

	@Override
	public List<ActivityVO> findHotAct() {
		Connection con = null ;
		PreparedStatement pstmt= null ;
		ResultSet rs=null;
		List<ActivityVO> hlist= new ArrayList<>();
		ActivityVO activityVO = null;
		
		try {

			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(FINDHOT_STMT);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				activityVO=new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Cat(rs.getString("act_Cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
				hlist.add(activityVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("資料庫發生錯誤"+se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			
		}
		return hlist;
	}

	@Override
	public List<ActivityVO> findNewAct() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		List<ActivityVO> list= new ArrayList();
		ActivityVO activityVO=null;
		try {
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(FINDNEW_STMT);
			pstmt.executeQuery();
			while(rs.next()) {
				activityVO= new ActivityVO();
				activityVO.setAct_No(rs.getString("act_No"));
				activityVO.setAct_Cat(rs.getString("act_cat"));
				activityVO.setAct_Name(rs.getString("act_Name"));
				activityVO.setCoucat_No(rs.getString("coucat_No"));
				activityVO.setAct_Content(rs.getString("act_Content"));
				activityVO.setAct_PreAddTime(rs.getTimestamp("act_PreAddTime"));
				activityVO.setAct_PreOffTime(rs.getTimestamp("act_PreOffTime"));
				activityVO.setAct_Start(rs.getTimestamp("act_Start"));
				activityVO.setAct_End(rs.getTimestamp("act_End"));
				activityVO.setAct_Status(rs.getInt("act_Status"));
				activityVO.setAct_Views(rs.getInt("act_Views"));
				list.add(activityVO);
			}
			
			
		}catch(SQLException se) {
			throw new RuntimeException("資料庫發生錯誤"+se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			
		}
		return list;
		
	}
	

	

	

			
		
}