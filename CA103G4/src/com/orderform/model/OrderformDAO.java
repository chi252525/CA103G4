package com.orderform.model;

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

import com.delivery.model.DeliveryVO;
import com.orderinvoice.model.*;

public class OrderformDAO implements OrderformDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO orderform (order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus) values ('O'||LPAD(to_char(oredrform_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_MORE_STMT = "SELECT order_no,dek_no,mem_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform where ";
	private static final String GET_NOTOK_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform where order_status= 1 and order_pstatus!= 2";
	private static final String GET_ALL_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform order by order_no Desc";
	private static final String GET_ONE_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform where order_no=? ";
	private static final String DELETE = "DELETE FROM orderform where order_no = ?";
	private static final String UPDATE = "UPDATE orderform set order_status= ?, order_pstatus= ? where order_no= ?";

	@Override
	public void insert(OrderformVO orderformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderformVO.getOrder_status());
			pstmt.setInt(2, orderformVO.getOrder_pstatus());
			pstmt.setString(3, orderformVO.getOrder_no());

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
	public void delete(String order_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

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
	public OrderformVO findByPrimaryKey(String order_no) {

		OrderformVO orderformVO = null;
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
				orderformVO = new OrderformVO();
				orderformVO.setOrder_no(rs.getString("order_no"));
				orderformVO.setDek_no(rs.getString("dek_no"));
				orderformVO.setMem_no(rs.getString("mem_no"));
				orderformVO.setBranch_no(rs.getString("branch_no"));
				orderformVO.setDeliv_no(rs.getString("deliv_no"));
				orderformVO.setOrder_type(rs.getInt("order_type"));
				orderformVO.setOrder_price(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setDeliv_addres(rs.getString("deliv_addres"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));

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
		return orderformVO;
	}

	public List<OrderformVO> getNotOk() {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//		 order_status=? and order_pstatus= ?
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_NOTOK_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderformVO = new OrderformVO();
				orderformVO.setOrder_no(rs.getString("order_no"));
				orderformVO.setDek_no(rs.getString("dek_no"));
				orderformVO.setMem_no(rs.getString("mem_no"));
				orderformVO.setBranch_no(rs.getString("branch_no"));
				orderformVO.setDeliv_no(rs.getString("deliv_no"));
				orderformVO.setOrder_type(rs.getInt("order_type"));
				orderformVO.setOrder_price(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setDeliv_addres(rs.getString("deliv_addres"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));
				list.add(orderformVO); // Store the row in the list
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

	public List<OrderformVO> getMore(String order_no, String delivery_no) {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String od = "order_no= ?";
		String dn = "deliv_no= ?";
		String ad = " and ";
//		String de = "order by order_no Desc";

		try {

			con = ds.getConnection();

			if (order_no != null && delivery_no == null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + od);

				pstmt.setString(1, order_no);

				rs = pstmt.executeQuery();

			} else if (order_no == null && delivery_no != null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + dn);

				pstmt.setString(1, delivery_no);

				rs = pstmt.executeQuery();

			} else if (order_no != null && delivery_no != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + od + ad + dn);

				pstmt.setString(1, order_no);
				pstmt.setString(2, delivery_no);

				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				orderformVO = new OrderformVO();
				orderformVO.setOrder_no(rs.getString("order_no"));
				orderformVO.setDek_no(rs.getString("dek_no"));
				orderformVO.setMem_no(rs.getString("mem_no"));
				orderformVO.setDeliv_no(rs.getString("deliv_no"));
				orderformVO.setOrder_type(rs.getInt("order_type"));
				orderformVO.setOrder_price(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));

				list.add(orderformVO); // Store the row in the list
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
	public List<OrderformVO> getAll() {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				orderformVO.setOrder_price(rs.getInt("order_price"));
				orderformVO.setOrder_status(rs.getInt("order_status"));
				orderformVO.setDeliv_addres(rs.getString("deliv_addres"));
				orderformVO.setOrder_pstatus(rs.getInt("order_pstatus"));

				list.add(orderformVO); // Store the row in the list
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
	public void insertWithInvoice(OrderformVO orderformVO, List<OrderinvoiceVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "ORDER_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

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

			String next_orderno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderno = rs.getString(1);
				System.out.println("有取得自增主鍵。");
			} else {
				System.out.println("未取得自增主鍵。");
			}

			rs.close();

			// 同時新增訂單明細
			OrderinvoiceDAO dao = new OrderinvoiceDAO();
			for (OrderinvoiceVO aOin : list) {
				aOin.setOrder_no(new String(next_orderno));
				dao.insert2(aOin, con);
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public void updateWithDelivery(OrderformVO orderformVO, List<DeliveryVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		

	}

}
