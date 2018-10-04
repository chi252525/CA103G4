package com.delivery.model;

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

import com.orderform.model.OrderformService;
import com.orderform.model.OrderformVO;

public class DeliveryDAO implements DeliveryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO delivery (deliv_no,branch_no,emp_no,deliv_status) values ('D'||'-'||LPAD(to_char(delivery_seq.NEXTVAL), 9, '0'), ?, null, 1)";
	private static final String GET_MORE_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where ";
	private static final String GET_ALL_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery order by deliv_no DESC";
	private static final String GET_NOTOK_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where deliv_status= 1 order by deliv_no DESC";
	private static final String GET_ONE_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where deliv_no= ?";
	private static final String UPDATE = "UPDATE delivery set emp_no=?,deliv_status=? where deliv_no = ?";
	// VARCHAR2 (PK not found)
	private static final String GETOUT = "SELECT emp_no FROM delivery where deliv_status= 2";

	@Override
	public void insert(DeliveryVO deliveryVO, List<OrderformVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "DELIV_NO" };

			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, deliveryVO.getBranch_no());

			pstmt.executeUpdate();

			String next_delivno = null;
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				next_delivno = rs.getString(1);
				System.out.println("有取得自增主鍵。");
			} else {
				System.out.println("為取得自增主鍵");
			}

			rs.close();

			// 新增外送派送單後開始更新訂單的外送派送單編號
			OrderformService Svc = new OrderformService();
			for (OrderformVO uOrd : list) {
				uOrd.setDeliv_no(new String(next_delivno));
				Svc.upOrdDel(uOrd, con);
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
	public void update(DeliveryVO deliveryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, deliveryVO.getEmp_no());
			pstmt.setString(2, deliveryVO.getDeliv_status());
			pstmt.setString(3, deliveryVO.getDeliv_no());

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

	public List<DeliveryVO> getByThreeKey(String deliv_no, String emp_no, String deliv_status) {
		List<DeliveryVO> list = new ArrayList<DeliveryVO>();
		DeliveryVO deliveryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dn = "deliv_no= ?";
		String em = "emp_no= ?";
		String dt = "deliv_status= ?";
		String ad = " and ";
		String od = " order by deliv_no DESC";

		if (deliv_no.trim().length() == 0) {
			deliv_no = null;
		}
		if (emp_no.trim().length() == 0) {
			emp_no = null;
		}
		if (deliv_status.trim().length() == 0) {
			deliv_status = null;
		}

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MORE_STMT);

			if (deliv_no != null) {
				
				if ( emp_no == null && deliv_status == null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + dn);

				pstmt.setString(1, deliv_no);

				rs = pstmt.executeQuery();
				} else if (emp_no != null && deliv_status == null) {
					
					pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + em + od);

					pstmt.setString(1, deliv_no);
					pstmt.setString(2, emp_no);

					rs = pstmt.executeQuery();
				} else if(emp_no == null && deliv_status != null) {
					pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + dt + od);

					pstmt.setString(1, deliv_no);
					pstmt.setString(2, deliv_status);

					rs = pstmt.executeQuery();
				} else if (emp_no != null && deliv_status != null) {
					pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + em + ad + dt + od);

					pstmt.setString(1, deliv_no);
					pstmt.setString(2, emp_no);
					pstmt.setString(3, deliv_status);

					rs = pstmt.executeQuery();
				}
				
			} else if (deliv_no == null && emp_no != null && deliv_status == null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + em + od);

				pstmt.setString(1, emp_no);

				rs = pstmt.executeQuery();

			} else if (deliv_no == null && emp_no == null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + dt + od);

				pstmt.setString(1, deliv_status);

				rs = pstmt.executeQuery();
			} else if (deliv_no == null && emp_no != null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + em + ad + dt + od);

				pstmt.setString(1, emp_no);
				pstmt.setString(2, deliv_status);

				rs = pstmt.executeQuery();
			} else {
				pstmt = con.prepareStatement(GET_ALL_STMT);

				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				deliveryVO = new DeliveryVO();
				deliveryVO.setDeliv_no(rs.getString("deliv_no"));
				deliveryVO.setBranch_no(rs.getString("branch_no"));
				deliveryVO.setEmp_no(rs.getString("emp_no"));
				deliveryVO.setDeliv_status(rs.getString("deliv_status"));
				list.add(deliveryVO); // Store the row in the list
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

	public List<DeliveryVO> getByStatus() {
		List<DeliveryVO> list = new ArrayList<DeliveryVO>();
		DeliveryVO deliveryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NOTOK_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				deliveryVO = new DeliveryVO();
				deliveryVO.setDeliv_no(rs.getString("deliv_no"));
				deliveryVO.setBranch_no(rs.getString("branch_no"));
				deliveryVO.setEmp_no(rs.getString("emp_no"));
				deliveryVO.setDeliv_status(rs.getString("deliv_status"));
				list.add(deliveryVO); // Store the row in the list
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
	public List<DeliveryVO> getAll() {
		List<DeliveryVO> list = new ArrayList<DeliveryVO>();
		DeliveryVO deliveryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				deliveryVO = new DeliveryVO();
				deliveryVO.setDeliv_no(rs.getString("deliv_no"));
				deliveryVO.setBranch_no(rs.getString("branch_no"));
				deliveryVO.setEmp_no(rs.getString("emp_no"));
				deliveryVO.setDeliv_status(rs.getString("deliv_status"));
				list.add(deliveryVO); // Store the row in the list
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
	public DeliveryVO findByPrimaryKey(String deliv_no) { // 以外送單主鍵尋找

		DeliveryVO deliveryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, deliv_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				deliveryVO = new DeliveryVO();
				deliveryVO.setDeliv_no(rs.getString("deliv_no"));
				deliveryVO.setBranch_no(rs.getString("branch_no"));
				deliveryVO.setEmp_no(rs.getString("emp_no"));
				deliveryVO.setDeliv_status(rs.getString("deliv_status"));
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
		return deliveryVO;
	}
	
	@Override
	public List<String> getOut() {
		List<String> list = new ArrayList<String>();
		DeliveryVO deliveryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETOUT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("emp_no")); // Store the row in the list
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
