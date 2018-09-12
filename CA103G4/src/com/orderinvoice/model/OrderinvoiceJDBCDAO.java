package com.orderinvoice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderinvoiceJDBCDAO implements OrderinvoiceDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Pro";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO orderinvoice (invo_no,order_no,menu_no,custom_no,invo_status) values ('IN'||LPAD(to_char(oredrinvoice_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice order by invo_no";
	private static final String GET_ONE_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice where invo_no = ?";
	private static final String DELETE = "DELETE FROM orderinvoice where invo_no = ?";
	private static final String UPDATE = "UPDATE orderinvoice set invo_status=? where invo_no = ? and order_no = ?";

	@Override
	public void insert(OrderinvoiceVO orderinvoiceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderinvoiceVO.getOrder_no());
			pstmt.setString(2, orderinvoiceVO.getMenu_no());
			pstmt.setString(3, orderinvoiceVO.getCustom_no());
			pstmt.setInt(4, orderinvoiceVO.getInvo_status());

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
	public void update(OrderinvoiceVO orderinvoiceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderinvoiceVO.getInvo_status());
			pstmt.setString(2, orderinvoiceVO.getInvo_no());
			pstmt.setString(3, orderinvoiceVO.getOrder_no());

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
	public void delete(String invo_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, invo_no);

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
	public OrderinvoiceVO findByPrimaryKey(String invo_no) {

		OrderinvoiceVO orderinvoiceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, invo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deliveryVO 也稱為 Domain objects
				orderinvoiceVO = new OrderinvoiceVO();
				orderinvoiceVO.setInvo_no(rs.getString("invo_no"));
				orderinvoiceVO.setOrder_no(rs.getString("order_no"));
				orderinvoiceVO.setMenu_no(rs.getString("menu_no"));
				orderinvoiceVO.setCustom_no(rs.getString("custom_no"));
				orderinvoiceVO.setInvo_status(rs.getInt("invo_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return orderinvoiceVO;
	}

	@Override
	public List<OrderinvoiceVO> getAll() {
		List<OrderinvoiceVO> list = new ArrayList<OrderinvoiceVO>();
		OrderinvoiceVO orderinvoiceVO = null;

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
				
				orderinvoiceVO = new OrderinvoiceVO();
				orderinvoiceVO.setInvo_no(rs.getString("invo_no"));
				orderinvoiceVO.setOrder_no(rs.getString("order_no"));
				orderinvoiceVO.setMenu_no(rs.getString("menu_no"));
				orderinvoiceVO.setCustom_no(rs.getString("custom_no"));
				orderinvoiceVO.setInvo_status(rs.getInt("invo_status"));
				list.add(orderinvoiceVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

}
