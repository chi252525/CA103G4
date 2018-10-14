package android.com.orderinvoice.model;

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

import com.custommeals.model.*;

import android.com.menu.model.*;

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

	private static final String INSERT_STMT = "INSERT INTO orderinvoice (invo_no,order_no,menu_no,menu_nu,custom_no,invo_status) values ('IN'||LPAD(to_char(oredrinvoice_seq.NEXTVAL), 9, '0'), ?, ?, '1', ?, ?)";
	private static final String GET_ALL_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice order by invo_no DESC";
	private static final String GET_ONE_STMT = "SELECT invo_no,order_no,menu_no,custom_no,menu_nu,custom_nu,invo_status FROM orderinvoice where order_no = ?";
	private static final String DELETE = "DELETE FROM orderinvoice where invo_no = ?";
	private static final String UPDATE = "UPDATE orderinvoice set invo_status=? where invo_no = ? and order_no = ?";
	private static final String GET_ONE_FROM_ORDERNO_AND_INVOSTATUS_STMT = "SELECT invo_no,order_no,menu_no,custom_no,invo_status FROM orderinvoice where order_no = ? and invo_status = ?";
	private static final String UPDATE_STATUS = "UPDATE orderinvoice set invo_status='2' where invo_no = ?";
	private static final String GET_ORDERNO_STMT = "SELECT order_no FROM orderinvoice where invo_no = ?";
	
	
	@Override
	public String getOrderNo(String invo_no) {

		String orderNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDERNO_STMT);
			pstmt.setString(1, invo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderNo = rs.getString("order_no");
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
		return orderNo;
	}

	@Override
	public void updateStatus(String invo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

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
	public List<OrderinvoiceVO> findByOrder_no_withMenu(String order_no, Integer invo_status) {
		List<OrderinvoiceVO> list = new ArrayList<OrderinvoiceVO>();
		OrderinvoiceVO orderinvoiceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_FROM_ORDERNO_AND_INVOSTATUS_STMT);

			pstmt.setString(1, order_no);
			pstmt.setInt(2, invo_status);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MenuDAO_interface menudao = new MenuDAO();
				MenuVO menuVO = menudao.findByPrimaryKey(rs.getString("menu_no"));
				orderinvoiceVO = new OrderinvoiceVO();
				orderinvoiceVO.setInvo_no(rs.getString("invo_no"));
				orderinvoiceVO.setOrder_no(rs.getString("order_no"));
				orderinvoiceVO.setMenu_no(rs.getString("menu_no"));
				orderinvoiceVO.setCustom_no(rs.getString("custom_no"));
				orderinvoiceVO.setInvo_status(rs.getInt("invo_status"));
				orderinvoiceVO.setMenuVO(menuVO);
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
	public void insert(OrderinvoiceVO orderinvoiceVO, Connection con) {

		PreparedStatement pstmt = null;

		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderinvoiceVO.getOrder_no());
			pstmt.setString(2, orderinvoiceVO.getMenu_no());
			pstmt.setString(3, orderinvoiceVO.getCustom_no());
			pstmt.setInt(4, orderinvoiceVO.getInvo_status());
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
		}

	}
	
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
				MenuDAO_interface menudao = new MenuDAO();
				MenuVO menuVO = menudao.findByPrimaryKey(rs.getString("menu_no"));
				CustommealsDAO_interface customdao = new CustommealsDAO();
				CustommealsVO customVO = customdao.findByPrimaryKey(rs.getString("custom_no"));
				orderinvoiceVO = new OrderinvoiceVO();
				orderinvoiceVO.setInvo_no(rs.getString("invo_no"));
				orderinvoiceVO.setOrder_no(rs.getString("order_no"));
				orderinvoiceVO.setMenu_no(rs.getString("menu_no"));
				orderinvoiceVO.setCustom_no(rs.getString("custom_no"));
				orderinvoiceVO.setMenu_nu(rs.getString("menu_nu"));
				orderinvoiceVO.setCustom_nu(rs.getString("custom_nu"));
				orderinvoiceVO.setInvo_status(rs.getInt("invo_status"));
				orderinvoiceVO.setMenuVO(menuVO);
				orderinvoiceVO.setCustomVO(customVO);
				
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

}
