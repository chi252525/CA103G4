package android.com.orderform.model;

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
import android.com.orderinvoice.model.*;
import android.com.coupon.model.*;
import android.com.couponhistory.model.*;
import android.com.desk.model.*;
import android.com.member.model.*;
import android.com.menu.model.*;

public class OrderformDAO implements OrderformDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
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
	private static final String INSERT_ORDERTYPE0_STMT = "INSERT INTO orderform(order_no,dek_no,branch_no,order_type,order_price,order_status,order_pstatus) values ('O'||LPAD(to_char(oredrform_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_FROM_TYPEANDSTATUS_STMT = "SELECT order_no,dek_no,mem_no,branch_no,deliv_no,order_type,order_price,order_status,deliv_addres,order_pstatus FROM orderform where order_type=? and order_status=? order by dek_no";
	private static final String GET_ONE_FROM_DEKNOANDSTATUS_STMT = "SELECT * FROM orderform where dek_no=? and order_status=?";
	private static final String GET_ALL_FROM_DELIVNO_STMT = "SELECT * FROM orderform where deliv_no=? ";
	private static final String UPDATE_ORDER_STATUS = "UPDATE orderform set order_status= ? where order_no= ?";
	
	
	
	
	@Override
	public void updateOrderStatus(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_STATUS);

			pstmt.setInt(1, 2);
			pstmt.setString(2, order_no);

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
	public List<OrderformVO> findByDeliveryNo(String delivery_no) {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FROM_DELIVNO_STMT);
			pstmt.setString(1, delivery_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderformVO = new OrderformVO();
				OrderinvoiceDAO_interface oidao = new OrderinvoiceDAO();
				List<OrderinvoiceVO> orderList = oidao.findByOrder_no(rs.getString("order_no"));
					
				MemberDAO_interface mdao = new MemberDAO();
				MemberVO memVO = mdao.findByPrimaryKey(rs.getString("mem_no"));
				
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
				orderformVO.setOrderList2(orderList);
				orderformVO.setMemVO(memVO);
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
	public OrderformVO findByDekNoAndOrderStatus(String dek_no, Integer order_status) {
		OrderformVO orderformVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FROM_DEKNOANDSTATUS_STMT);

			pstmt.setString(1, dek_no);
			pstmt.setInt(2, order_status);

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

	@Override
	public List<OrderformVO> findByOrderTypeAndStatus(Integer order_type, Integer order_status) {
		List<OrderformVO> list = new ArrayList<OrderformVO>();
		OrderformVO orderformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FROM_TYPEANDSTATUS_STMT);
			pstmt.setInt(1, order_type);
			pstmt.setInt(2, order_status);
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

	@Override
	public String addWithOrderItem(OrderformVO orderformVO, List<OrderInvoiceVO> orderList, String coupSn) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_order_no = null;
		try {

			con = ds.getConnection();

			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增訂單
			String[] cols = { "order_no" };
			pstmt = con.prepareStatement(INSERT_ORDERTYPE0_STMT,cols);
			pstmt.setString(1, orderformVO.getDek_no());
			pstmt.setString(2, orderformVO.getBranch_no());
			pstmt.setInt(3, orderformVO.getOrder_type());
			pstmt.setInt(4, orderformVO.getOrder_price());
			pstmt.setInt(5, orderformVO.getOrder_status());
			pstmt.setInt(6, orderformVO.getOrder_pstatus());
			pstmt.executeUpdate();
			// 取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_no + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細內容
			OrderinvoiceDAO_interface oidao = new OrderinvoiceDAO();
			OrderinvoiceVO oi = new OrderinvoiceVO();
			System.out.println("orderList.size()-A= "
					+ orderList.size());
			for (OrderInvoiceVO orderInvoiceVO : orderList) {
				oi.setOrder_no(next_order_no);
				oi.setMenu_no(orderInvoiceVO.getMenu_No());
//				oi.setCustom_no(orderInvoiceVO.getCustom_No());
				oi.setInvo_status(1);
				oidao.insert(oi,con);
			}
			// 更新桌位狀態
			DeskDAO_interface ddao= new DeskDAO();
			ddao.updateDekStatus(orderformVO.getDek_no(),con);
			// 有使用優惠卷則更新個人優惠卷持有紀錄
			if(!coupSn.isEmpty()) {
				CouponhistoryDAO_interface chdao = new CouponhistoryDAO();
				chdao.updateOrderNoAndCoupState(coupSn,"CP0",con);
			}

			// 2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("orderList.size()-B= "
					+ orderList.size());
			System.out.println("新增訂單編號" + next_order_no + "時，共有明細"
					+ orderList.size() + "筆同時被新增");

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-OrderItem");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
		return next_order_no;
	}
	
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
		}  catch (SQLException se) {
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

}
