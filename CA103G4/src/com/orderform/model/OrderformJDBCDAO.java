package com.orderform.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderformJDBCDAO implements OrderformDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Pro";
	String passwd = "123456";

//	private static final String INSERT_STMT = "INSERT INTO orderform (order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus) values ('O'||LPAD(to_char(oredrform_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT = "INSERT INTO orderform (order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus) values ('0'||LPAD(to_char(oredrform_seq.NEXTVAL), 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform order by order_no";
	private static final String GET_ONE_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform where ";  //修改  加上order by 欄位 DESC
	private static final String DELETE = "DELETE FROM orderform where order_no = ?";
	private static final String UPDATE = "UPDATE orderform set order_status= ?, order_pstatus= ? where order_no= ?";

	@Override
	public void insert(OrderformVO orderformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderformVO.getDek_no());
			pstmt.setString(2, orderformVO.getMem_no());
			pstmt.setString(3, orderformVO.getBranch_no());
			pstmt.setString(4, orderformVO.getDeliv_no());
			pstmt.setInt(5, orderformVO.getOrder_type());
			pstmt.setInt(6, orderformVO.getOrder_price());
			pstmt.setInt(7, orderformVO.getOrder_status());
			pstmt.setString(8, orderformVO.getDeliv_addres());
			pstmt.setInt(9, orderformVO.getOrder_pstatus());

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
	public void update(OrderformVO orderformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, orderformVO.getOrder_status());
			pstmt.setInt(2, orderformVO.getOrder_pstatus());
			pstmt.setString(3, orderformVO.getOrder_no());

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
	public void delete(String order_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

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
	public OrderformVO findByTwoKey(int order_status, int order_pstatus) {

		OrderformVO orderformVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String os ="order_status= ?";
		String op ="order_pstatus= ?";
		String ad =" and ";

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			for(int i1=0; i1<4; i1++) {
				System.out.println(i1);
			}
//			ORDER_STATUS=1or2，搜尋ORDER_PSTATUS=1or3
			if ((order_status != 3) && ( order_pstatus != 2)) {
			
			pstmt = con.prepareStatement(GET_ONE_STMT + os + ad + op);

			pstmt.setInt(1, order_status);
			pstmt.setInt(2, order_pstatus);

			rs = pstmt.executeQuery();
			
			} else if(order_status == 1) {
				
			} else if((order_pstatus == 1)||(order_pstatus == 2)||(order_pstatus == 3)||(order_pstatus == 4)) {
				
			}
			
			
			
			
			
			
			
			
			
			while (rs.next()) {
				// deliveryVO 也稱為 Domain objects
				orderformVO = new OrderformVO();
				orderformVO.setOrder_no(rs.getString("order_no"));
				orderformVO.setDek_no(rs.getString("dek_no"));
				orderformVO.setMem_no(rs.getString("mem_no"));
				orderformVO.setBranch_no(rs.getString("branch_no"));
				orderformVO.setDeliv_no(rs.getString("deliv_no"));
				orderformVO.setOrder_type(rs.getInt("order_type"));
				orderformVO.setOrder_type(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setDeliv_addres(rs.getString("deliv_addres"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));

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
		return orderformVO;
	}

	@Override
	public List<OrderformVO> getAll() {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

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
				orderformVO = new OrderformVO();
				orderformVO.setOrder_no(rs.getString("order_no"));
				orderformVO.setDek_no(rs.getString("dek_no"));
				orderformVO.setMem_no(rs.getString("mem_no"));
				orderformVO.setBranch_no(rs.getString("branch_no"));
				orderformVO.setDeliv_no(rs.getString("deliv_no"));
				orderformVO.setOrder_type(rs.getInt("order_type"));
				orderformVO.setOrder_type(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setDeliv_addres(rs.getString("deliv_addres"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));

				list.add(orderformVO); // Store the row in the list
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
