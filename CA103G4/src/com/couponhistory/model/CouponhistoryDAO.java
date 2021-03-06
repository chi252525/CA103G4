package com.couponhistory.model;

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


public class CouponhistoryDAO implements CouponhistoryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO couponhistory (coup_sn,mem_no,coup_state) values (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT coup_sn,coup_state FROM couponhistory order by coup_sn";
	private static final String GET_ONE_STMT = "SELECT coup_sn,coup_state FROM couponhistory where coup_state = ?";
	private static final String DELETE = "DELETE FROM couponhistory where coup_sn = ?";
	private static final String UPDATE = "UPDATE couponhistory set order_no=?, coup_state=? where coup_sn = ?";
	private static final String GET_MEM_COUPON=	"SELECT *  FROM COUPONHISTORY  right JOIN coupon ON coupon.coup_sn= COUPONHISTORY.coup_sn WHERE COUPONHISTORY.mem_no=?";
//	private static final String GET_MEM_COUPON=	"SELECT *  FROM COUPONHISTORY  right JOIN coupon ON coupon.coup_sn= COUPONHISTORY.coup_sn right JOIN coucat ON coucat.coucat_no= COUPON.coucat_no WHERE COUPONHISTORY.mem_no= ?";
	private static final String GET_BY_MEM= "SELECT coup_sn,coup_state FROM COUPONHISTORY where mem_no = ?";

	@Override
	public void insert(CouponhistoryVO couponhistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponhistoryVO.getCoup_sn());
			pstmt.setString(2, couponhistoryVO.getMem_no());
			pstmt.setInt(3, couponhistoryVO.getCoup_state());

			pstmt.executeUpdate();
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
	public void update(CouponhistoryVO couponhistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponhistoryVO.getOrder_no());
			pstmt.setInt(2, couponhistoryVO.getCoup_state());
			pstmt.setString(3, couponhistoryVO.getCoup_sn());

			pstmt.executeUpdate();

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
	public void delete(String coup_sn) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coup_sn);

			pstmt.executeUpdate();

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
	public List<CouponhistoryVO> findByCoup_State(Integer coup_state) {
		List<CouponhistoryVO> list = new ArrayList<CouponhistoryVO>();
		CouponhistoryVO couponhistoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, coup_state);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deliveryVO 也稱為 Domain objects
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_sn"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
				list.add(couponhistoryVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public List<CouponhistoryVO> getAll() {
		List<CouponhistoryVO> list = new ArrayList<CouponhistoryVO>();
		CouponhistoryVO couponhistoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_sn"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
				list.add(couponhistoryVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public List<CouponhistoryVO> getCouponByMem(String mem_No) {
		List<CouponhistoryVO> list = new ArrayList<CouponhistoryVO>();
		CouponhistoryVO couponhistoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_COUPON);

			pstmt.setString(1, mem_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_sn"));
				couponhistoryVO.setMem_no(rs.getString("mem_no"));
				couponhistoryVO.setOrder_no(rs.getString("order_no"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
				list.add(couponhistoryVO);
			}

			// Handle any driver errors
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
		return list;
	}
	
	@Override
	public List<CouponhistoryVO> getByMem(String mem_No) {
		List<CouponhistoryVO> list = new ArrayList<CouponhistoryVO>();
		CouponhistoryVO couponhistoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEM);

			pstmt.setString(1, mem_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_sn"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
				list.add(couponhistoryVO);
			}

			// Handle any driver errors
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
		
		return list;
	}
	
}
