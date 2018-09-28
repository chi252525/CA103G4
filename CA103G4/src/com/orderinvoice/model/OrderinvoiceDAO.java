package com.orderinvoice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderinvoiceDAO implements OrderinvoiceDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Pro";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO orderinvoice (invo_no,order_no,menu_no,custom_no,invo_status) values ('IN'||LPAD(to_char(oredrinvoice_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?)";
	private static final String INSERT_STMT2 = "INSERT INTO orderinvoice (invo_no,order_no,menu_no,custom_no,invo_status) values ('IN'||LPAD(to_char(oredrinvoice_seq.NEXTVAL), 9, '0'), ?, ?, ?, 1)";
	private static final String GET_ALL_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice order by invo_no DESC";
	private static final String GET_ONE_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice where order_no = ?";
	private static final String DELETE = "DELETE FROM orderinvoice where invo_no = ?";
	private static final String UPDATE = "UPDATE orderinvoice set invo_status=? where invo_no = ? and order_no = ?";

	@Override
	public void insert(OrderinvoiceVO orderinvoiceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderinvoiceVO.getOrder_no());
			pstmt.setString(2, orderinvoiceVO.getMenu_no());
			pstmt.setString(3, orderinvoiceVO.getCustom_no());
			pstmt.setInt(4, orderinvoiceVO.getInvo_status());

			pstmt.executeUpdate();
		}catch (SQLException se) {
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

	}

	@Override
	public void update(OrderinvoiceVO orderinvoiceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderinvoiceVO.getInvo_status());
			pstmt.setString(2, orderinvoiceVO.getInvo_no());
			pstmt.setString(3, orderinvoiceVO.getOrder_no());

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
	public void delete(String invo_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, invo_no);

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
	public List<OrderinvoiceVO> findByOrder_no(String order_no) {
		List<OrderinvoiceVO> list = new ArrayList<OrderinvoiceVO>();
		OrderinvoiceVO orderinvoiceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deliveryVO 也稱為 Domain objects
				orderinvoiceVO = new OrderinvoiceVO();
				orderinvoiceVO.setInvo_no(rs.getString("invo_no"));
				orderinvoiceVO.setOrder_no(rs.getString("order_no"));
				orderinvoiceVO.setMenu_no(rs.getString("menu_no"));
				orderinvoiceVO.setCustom_no(rs.getString("custom_no"));
				orderinvoiceVO.setInvo_status(rs.getInt("invo_status"));
				list.add(orderinvoiceVO);
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
	public List<OrderinvoiceVO> getAll() {
		List<OrderinvoiceVO> list = new ArrayList<OrderinvoiceVO>();
		OrderinvoiceVO orderinvoiceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
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
	public void insert2(OrderinvoiceVO orderinvoiceVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {

     		pstmt = con.prepareStatement(INSERT_STMT2);
     		
     		if (orderinvoiceVO.getCustom_no() == null) {
				pstmt.setString(1, orderinvoiceVO.getOrder_no());
				pstmt.setString(2, orderinvoiceVO.getMenu_no());
				pstmt.setString(3, null);
     		} else if (orderinvoiceVO.getMenu_no() == null){
     			pstmt.setString(1, orderinvoiceVO.getOrder_no());
    			pstmt.setString(2, null);
    			pstmt.setString(3, orderinvoiceVO.getCustom_no());   			
     		}
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
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
		}
		
	}

}
