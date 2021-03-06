package com.storedrecord.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredrecordDAO implements StoredrecordDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO STOREDRECORD (stor_NO, MEM_NO,  STOR_DATE, STOR_POINT, DREW_POINT, STOR_STATUS)"
			+ "VALUES( ('B'| |LPAD(storedrecord_NO_seq.NEXTVAL,9,'0')), ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE STOREDRECORD SET MEM_NO=?, STOR_DATE=?, STOR_POINT=?, DREW_POINT=?, STOR_STATUS=? WHERE STOR_NO = ?";
	private static final String FINDBY_stor_NO = "SELECT * FROM STOREDRECORD WHERE stor_NO = ? ";
	private static final String GETALL = "SELECT * FROM STOREDRECORD order by stor_NO";
	private static final String FINDBY_MEM_NO = "SELECT * FROM STOREDRECORD WHERE MEM_NO=?";
	private static final String FINDBY_MON_YEAR = "SELECT * FROM STOREDRECORD WHERE extract(MONTH from STOR_DATE )=? AND extract(YEAR from STOR_DATE )=?";
	private static final String FINDBY_MON_YEAR_MemNo = "SELECT * FROM STOREDRECORD WHERE extract(MONTH from STOR_DATE )=? AND extract(YEAR from STOR_DATE )=? AND MEM_NO=?";
	private static final String DELETE_STMT = "DELETE FROM STOREDRECORD WHERE stor_No = ?";

	@Override
	public String insert(StoredrecordVO STOREDRECORD) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String Next_storno = null;//要return的自增主建
		try {
			con = ds.getConnection();
			
			System.out.println("Connecting to database successfully! (連線成功！)");
			String cols[] = {"STOR_NO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
//			pstmt.setString(1, STOREDRECORD.getStor_No());
			pstmt.setString(1, STOREDRECORD.getMem_No());
			pstmt.setTimestamp(2, STOREDRECORD.getStor_Date());
			pstmt.setInt(3, STOREDRECORD.getStor_Point());
			pstmt.setInt(4, STOREDRECORD.getDrew_Point());
			pstmt.setInt(5, STOREDRECORD.getStor_Status());
			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				Next_storno = rs.getString(1);
				System.out.println("自增主鍵值= " + Next_storno +"(剛新增成功的儲值單號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
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
		return Next_storno;
	}

	
	@Override
	public void update(StoredrecordVO STOREDRECORD) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, STOREDRECORD.getMem_No());
			pstmt.setTimestamp(2, STOREDRECORD.getStor_Date());
			pstmt.setInt(3, STOREDRECORD.getStor_Point());
			pstmt.setInt(4, STOREDRECORD.getDrew_Point());
			STOREDRECORD.setStor_Status(1);//剛狀態設為1:成功
			pstmt.setInt(5, STOREDRECORD.getStor_Status());//1代表儲值狀態成功
			pstmt.setString(6, STOREDRECORD.getStor_No());
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");
			
			MemberDAO memdao = new MemberDAO();
			memdao.update2(STOREDRECORD.getMem_No(), STOREDRECORD.getStor_Point(), con);
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("修改儲值編號" + STOREDRECORD.getStor_No() + "給" + STOREDRECORD.getMem_No()+STOREDRECORD.getStor_Point()
			+ "點");
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public StoredrecordVO findByPrimaryKey(String stor_No) {
		StoredrecordVO STOREDRECORD = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_stor_NO);
			pstmt.setString(1, stor_No);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				STOREDRECORD = new StoredrecordVO();
				STOREDRECORD.setStor_No(rs.getString("stor_No"));
				STOREDRECORD.setMem_No(rs.getString("mem_No"));
				STOREDRECORD.setStor_Date(rs.getTimestamp("STOR_DATE"));
				STOREDRECORD.setDrew_Point(rs.getInt("STOR_POINT"));
				STOREDRECORD.setStor_Point(rs.getInt("DREW_POINT"));
				STOREDRECORD.setStor_Status(rs.getInt("STOR_STATUS"));
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
		return STOREDRECORD;
	}

	@Override
	public List<StoredrecordVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoredrecordVO> STOREDRECORDlist = new ArrayList<>();

		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StoredrecordVO STOREDRECORD = new StoredrecordVO();
				STOREDRECORD = new StoredrecordVO();
				STOREDRECORD.setStor_No(rs.getString("stor_No"));
				STOREDRECORD.setMem_No(rs.getString("mem_No"));
				STOREDRECORD.setStor_Date(rs.getTimestamp("STOR_DATE"));
				STOREDRECORD.setDrew_Point(rs.getInt("STOR_POINT"));
				STOREDRECORD.setStor_Point(rs.getInt("DREW_POINT"));
				STOREDRECORD.setStor_Status(rs.getInt("STOR_STATUS"));
				STOREDRECORDlist.add(STOREDRECORD); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return STOREDRECORDlist;
	}

	@Override
	public List<StoredrecordVO> findByMem_no(String mem_No) {
		StoredrecordVO STOREDRECORD = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoredrecordVO> STOREDRECORDlist = new ArrayList<>();
		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_MEM_NO);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				STOREDRECORD = new StoredrecordVO();
				STOREDRECORD.setStor_No(rs.getString("stor_No"));
				STOREDRECORD.setMem_No(rs.getString("mem_No"));
				STOREDRECORD.setStor_Date(rs.getTimestamp("STOR_DATE"));
				STOREDRECORD.setDrew_Point(rs.getInt("STOR_POINT"));
				STOREDRECORD.setStor_Point(rs.getInt("DREW_POINT"));
				STOREDRECORD.setStor_Status(rs.getInt("STOR_STATUS"));
				STOREDRECORDlist.add(STOREDRECORD);
				System.out.println("===================================");
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
		return STOREDRECORDlist;
	}

	@Override
	public void delete(String stor_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, stor_No);
			pstmt.executeUpdate();
			System.out.println("刪除成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<StoredrecordVO> findByMon_Year(int Mon, int Year) {
		StoredrecordVO storedrecordvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoredrecordVO> STOREDRECORDlist = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FINDBY_MON_YEAR);
			pstmt.setInt(1, Mon);
			pstmt.setInt(2, Year);
			rs = pstmt.executeQuery();
			System.out.println("查完囉!");
			while (rs.next()) {
				storedrecordvo = new StoredrecordVO();
				storedrecordvo.setStor_No(rs.getString("STOR_NO"));
				storedrecordvo.setMem_No(rs.getString("MEM_NO"));
				storedrecordvo.setStor_Date(rs.getTimestamp("STOR_DATE"));
				storedrecordvo.setStor_Point(rs.getInt("STOR_POINT"));
				storedrecordvo.setDrew_Point(rs.getInt("DREW_POINT"));
				storedrecordvo.setStor_Status(rs.getInt("STOR_STATUS"));
				STOREDRECORDlist.add(storedrecordvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return STOREDRECORDlist;
	}

	@Override
	public List<StoredrecordVO> findByMon_Year_MemNo(int Mon, int Year, String mem_No) {
		StoredrecordVO storedrecordvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoredrecordVO> STOREDRECORDlist = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FINDBY_MON_YEAR_MemNo);
			pstmt.setInt(1, Mon);
			pstmt.setInt(2, Year);
			pstmt.setString(3, mem_No);
			rs = pstmt.executeQuery();
			System.out.println("查完囉!");
			while (rs.next()) {
				storedrecordvo = new StoredrecordVO();
				storedrecordvo.setStor_No(rs.getString("STOR_NO"));
				storedrecordvo.setMem_No(rs.getString("MEM_NO"));
				storedrecordvo.setStor_Date(rs.getTimestamp("STOR_DATE"));
				storedrecordvo.setStor_Point(rs.getInt("STOR_POINT"));
				storedrecordvo.setDrew_Point(rs.getInt("DREW_POINT"));
				storedrecordvo.setStor_Status(rs.getInt("STOR_STATUS"));
				STOREDRECORDlist.add(storedrecordvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return STOREDRECORDlist;
	}

}