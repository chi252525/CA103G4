package com.couponhistory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.delivery.model.DeliveryVO;

public class CouponhistoryJDBCDAO implements CouponhistoryDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Pro";
	String passwd = "123456";

//	private static final String INSERT_STMT = "INSERT INTO couponhistory (coup_sn,mem_no,order_no,coup_state) values (?, ?, ?, ?)";
	private static final String INSERT_STMT = "INSERT INTO couponhistory (coup_sn,mem_no,order_no,coup_state) values (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT coup_sn,mem_no,order_no,coup_state FROM couponhistory order by coup_sn";
	private static final String GET_ONE_STMT = "SELECT coup_sn,mem_no,order_no,coup_state FROM couponhistory where coup_sn = ?";
	private static final String DELETE = "DELETE FROM couponhistory where coup_sn = ?";
	private static final String UPDATE = "UPDATE couponhistory set mem_no=?, order_no=?, coup_state=? where coup_sn = ?";

	@Override
	public void insert(CouponhistoryVO couponhistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponhistoryVO.getMem_no());
			pstmt.setString(2, couponhistoryVO.getOrder_no());
			pstmt.setInt(3, couponhistoryVO.getCoup_state());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(CouponhistoryVO couponhistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponhistoryVO.getOrder_no());
			pstmt.setString(2, couponhistoryVO.getMem_no());
			pstmt.setInt(3, couponhistoryVO.getCoup_state());
			pstmt.setString(4, couponhistoryVO.getCoup_sn());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coup_sn);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public CouponhistoryVO findByPrimaryKey(String coup_sn) {

		CouponhistoryVO couponhistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coup_sn);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deliveryVO 也稱為 Domain objects
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_no"));
				couponhistoryVO.setMem_no(rs.getString("mem_no"));
				couponhistoryVO.setOrder_no(rs.getString("order_no"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return couponhistoryVO;
	}

	@Override
	public List<CouponhistoryVO> getAll() {
		List<CouponhistoryVO> list = new ArrayList<CouponhistoryVO>();
		CouponhistoryVO couponhistoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				couponhistoryVO = new CouponhistoryVO();
				couponhistoryVO.setCoup_sn(rs.getString("coup_no"));
				couponhistoryVO.setMem_no(rs.getString("mem_no"));
				couponhistoryVO.setOrder_no(rs.getString("order_no"));
				couponhistoryVO.setCoup_state(rs.getInt("coup_state"));
				list.add(couponhistoryVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return list;
	}

}