package com.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CouponJDBCDAO implements CouponDAO_interface {
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String USER = "CA103";
		private static final String PASSWORD = "123456";
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String INSERT_STMT = 
				"INSERT INTO  COUPON (COUP_SN,COUCAT_NO,COUP_STATUS)"
				+ "VALUES('M'||'-'||LPAD(to_char(COUPON_seq.NEXTVAL), 11, '0'),?,'CP1')";
		private static final String UPDATESTATUS_MEMGET="UPDATE COUPON SET COUP_STATUS='CP0' WHERE COUP_SN=?";
		private static final String FINDBYCOUCAT_NO = 
				"SELECT * FROM COUPON WHERE COUCAT_NO=?";
		private static final String FINDBY_PRIMARY_KEY="SELECT * FROM COUPON WHERE COUP_SN=?";
		private static final String FINDBYCOUCAT_NO_NOT_SENDED = 
				"SELECT * FROM COUPON WHERE COUCAT_NO=? AND COUCAT_STATUS=?";
		private static final String FINDCOUCAT_BY_COUPSN = "SELECT * FROM COUPON WHERE COUP_SN=? ";
		static {
			try {
				Class.forName(DRIVER);
			}catch(ClassNotFoundException ce) {
				ce.printStackTrace();
			}
			}
		@Override
		public void insert(CouponVO couponVO, Integer coucat_Amo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				for(int i=0;i<coucat_Amo;i++) {
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, couponVO.getCoucat_No());
				int rowCount =pstmt.executeUpdate();
				System.out.println("新增 " + rowCount + " 筆資料");
		
				}
				
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
		public void updateStatus(CouponVO couponVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(UPDATESTATUS_MEMGET);
				pstmt.setString(1, couponVO.getCoup_Sn());
				int rowCount=pstmt.executeUpdate();
				System.out.println("修改" + rowCount + " 筆資料");
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
		public List<CouponVO> findByCoucatNo(String coucat_No) {
			CouponVO couponVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CouponVO> list =new ArrayList();
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(FINDBYCOUCAT_NO);
				pstmt.setString(1, coucat_No);
				rs = pstmt.executeQuery();
	  
				while (rs.next()) {
					couponVO=new CouponVO();
					couponVO.setCoup_Sn(rs.getString("coup_Sn"));
					couponVO.setCoucat_No(rs.getString("coucat_No"));
					couponVO.setCoup_Status(rs.getString("coup_Status"));
					list.add(couponVO);
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
			return list;
		}



		@Override
		public void insertbyGenaratedKeys(Connection con,String coucat_No,Integer coucat_Amo) {
			PreparedStatement pstmt = null;
			
			try {
				for(int i=0;i<coucat_Amo;i++) {
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, coucat_No);
				int rowCount =pstmt.executeUpdate();
				System.out.println("新增 " + rowCount + " 筆資料");
				
				}
				// Handle any SQL errors
			} catch (SQLException se) {
				try {
					// rollback
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
//						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}			
		}



		@Override
		public CouponVO getOneCoupon(String coup_Sn) {
			CouponVO couponVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(FINDBY_PRIMARY_KEY);
		
				pstmt.setString(1, coup_Sn);
				rs = pstmt.executeQuery();
	  
				while (rs.next()) {
					couponVO = new CouponVO();
					couponVO.setCoup_Sn(rs.getString("coup_Sn"));
					couponVO.setCoucat_No(rs.getString("coucat_No"));
					couponVO.setCoup_Status(rs.getString("coup_Status"));
					
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
			return couponVO;
		}

		
		@Override
		public List<CouponVO> findByCoucatNo_CP0(String coucat_No, String coup_Status) {
			CouponVO couponVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CouponVO> list =new ArrayList();
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(FINDBYCOUCAT_NO_NOT_SENDED);
				pstmt.setString(1, coucat_No);
				pstmt.setString(1, coup_Status);
				rs = pstmt.executeQuery();
	  
				while (rs.next()) {
					couponVO=new CouponVO();
					couponVO.setCoup_Sn(rs.getString("coup_Sn"));
					couponVO.setCoucat_No(rs.getString("coucat_No"));
					couponVO.setCoup_Status(rs.getString("coup_Status"));
					list.add(couponVO);
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
			return list;
		}
		
		@Override
		public CouponVO findCoucatByCoupSn(String coup_Sn) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CouponVO couponVO=null;
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(FINDCOUCAT_BY_COUPSN);
				pstmt.setString(1, coup_Sn);
				rs = pstmt.executeQuery();
	  
				while (rs.next()) {
					couponVO=new CouponVO();
					couponVO.setCoup_Sn(rs.getString("coup_Sn"));
					couponVO.setCoucat_No(rs.getString("coucat_No"));
					couponVO.setCoup_Status(rs.getString("coup_Status"));
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
			return couponVO;
		}
		
		
}
