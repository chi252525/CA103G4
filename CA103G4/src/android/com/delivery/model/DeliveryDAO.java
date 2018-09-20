package android.com.delivery.model;

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

	private static final String INSERT_STMT = "INSERT INTO delivery (deliv_no,branch_no,emp_no,deliv_status) values ('D'||'-'||LPAD(to_char(delivery_seq.NEXTVAL), 9, '0'), ?, ?, ?)";
	private static final String GET_MORE_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where ";
	private static final String GET_ALL_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery order by deliv_no DESC";
	private static final String GET_NOTOK_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where deliv_status= 1 order by deliv_no DESC";
	private static final String GET_ONE_STMT = "SELECT deliv_no,branch_no,emp_no,deliv_status FROM delivery where deliv_no= ?";
	private static final String UPDATE = "UPDATE delivery set deliv_status=? where deliv_no = ?";
	private static final String UPDATE2 = "UPDATE delivery set emp_no=?,deliv_status=? where deliv_no = ?";
	// VARCHAR2 (PK not found)

	@Override
	public void insert(DeliveryVO deliveryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, deliveryVO.getBranch_no());
			pstmt.setString(2, deliveryVO.getEmp_no());
			pstmt.setString(3, deliveryVO.getDeliv_status());

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
	public void update(DeliveryVO deliveryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			if (deliveryVO.getDeliv_status() == "1") {
				pstmt = con.prepareStatement(UPDATE2);

				pstmt.setString(1, deliveryVO.getEmp_no());
				pstmt.setString(2, deliveryVO.getDeliv_status());
				pstmt.setString(3, deliveryVO.getDeliv_no());
			} else {
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, deliveryVO.getDeliv_status());
				pstmt.setString(2, deliveryVO.getDeliv_no());
			}

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

			if (deliv_no != null && emp_no == null && deliv_status == null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + dn);

				pstmt.setString(1, deliv_no);

				rs = pstmt.executeQuery();

			} else if (deliv_no == null && emp_no != null && deliv_status == null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + em);

				pstmt.setString(1, emp_no);

				rs = pstmt.executeQuery();

			} else if (deliv_no == null && emp_no == null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + dt);

				pstmt.setString(1, deliv_status);

				rs = pstmt.executeQuery();
			} else if (deliv_no != null && emp_no != null && deliv_status == null) {

				pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + em);

				pstmt.setString(1, deliv_no);
				pstmt.setString(2, emp_no);

				rs = pstmt.executeQuery();
			} else if (deliv_no == null && emp_no != null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + em + ad + dt);

				pstmt.setString(1, emp_no);
				pstmt.setString(2, deliv_status);

				rs = pstmt.executeQuery();
			} else if (deliv_no != null && emp_no == null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + dt);

				pstmt.setString(1, deliv_no);
				pstmt.setString(2, deliv_status);

				rs = pstmt.executeQuery();
			} else if (deliv_no != null && emp_no != null && deliv_status != null) {
				pstmt = con.prepareStatement(GET_MORE_STMT + dn + ad + em + ad + dt);

				pstmt.setString(1, deliv_no);
				pstmt.setString(2, emp_no);
				pstmt.setString(3, deliv_status);

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

}
