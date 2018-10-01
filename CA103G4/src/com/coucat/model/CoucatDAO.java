package com.coucat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coupon.model.CouponDAO;


public class CoucatDAO implements CoucatDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_COUCAT_PIC="SELECT COUCAT_PIC FROM COUCAT WHERE COUCAT_NO=?";
	
	private static final String INSERT_STMT = 
			"INSERT INTO  COUCAT(COUCAT_NO,COUCAT_Name,COUCAT_CATA,COUCAT_VALUE,COUCAT_DISCOUNT,COUCAT_FREEP,COUCAT_VALID,COUCAT_INVALID,COUCAT_AMO,COUCAT_PIC,COUCAT_STATUS)VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(COUCAT_seq.NEXTVAL), 6, '0')," + 
			"?,?,?,?,?,?,?,?,?,'CS1')";

	private static final String UPDATE_STMT = 
			"UPDATE COUCAT SET COUCAT_Name=?,COUCAT_CATA=?,COUCAT_VALUE=?,COUCAT_DISCOUNT=?,COUCAT_FREEP=?," + 
			"COUCAT_VALID=?," + 
			"COUCAT_INVALID=?,COUCAT_PIC=?" + 
			"WHERE COUCAT_NO=?";
	private static final String GETALL = "SELECT * FROM COUCAT";
	private static final String FIND_BY_CATA = 
			"SELECT * FROM COUCAT WHERE COUCAT_CATA=?";
	private static final String  GET_ONE_COUCAT="SELECT * FROM COUCAT WHERE COUCAT_NO=?";
	
	
	@Override
	public void insert(CoucatVO coucatVO) {{
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_coucat_No = null;
		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully!");

			con.setAutoCommit(false);
		
			//insert coucat record
			int cols[] = {1};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, coucatVO.getCoucat_Name());
			pstmt.setString(2, coucatVO.getCoucat_Cata());
			pstmt.setInt(3, coucatVO.getCoucat_Value());
			pstmt.setDouble(4, coucatVO.getCoucat_Discount());
			pstmt.setInt(5, coucatVO.getCoucat_Freep());
			pstmt.setTimestamp(6, coucatVO.getCoucat_Valid());
			pstmt.setTimestamp(7, coucatVO.getCoucat_Invalid());
			pstmt.setInt(8, coucatVO.getCoucat_Amo());
			pstmt.setBytes(9, coucatVO.getCoucat_Pic());
			pstmt.executeUpdate();
			// getGeneratedKeys
						ResultSet rs = pstmt.getGeneratedKeys();
						if (rs.next()) {
							next_coucat_No = rs.getString(1);
							System.out.println("自增主鍵值 = " + next_coucat_No + "(剛新增成功的優惠卷類別編號)");
						} else {
							System.out.println("未取得自增主鍵值");
						}
						
			// insert coupon record at the same time
						CouponDAO dao= new CouponDAO();
						dao.insertbyGenaratedKeys(con,next_coucat_No,coucatVO.getCoucat_Amo());
						con.commit();
						con.setAutoCommit(true);
						System.out.println("新增訂單編號 " + next_coucat_No + " 時，優惠卷序號同時被新增完畢");
						rs.close();
			// Handle any SQL errors
		} catch (SQLException  se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally {
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

	}
	@Override
	public void update(CoucatVO coucatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully!");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, coucatVO.getCoucat_Name());
			pstmt.setString(2, coucatVO.getCoucat_Cata());
			pstmt.setInt(3, coucatVO.getCoucat_Value());
			pstmt.setDouble(4, coucatVO.getCoucat_Discount());
			pstmt.setInt(5, coucatVO.getCoucat_Freep());
			pstmt.setTimestamp(6, coucatVO.getCoucat_Valid());
			pstmt.setTimestamp(7, coucatVO.getCoucat_Invalid());
			pstmt.setBytes(8, coucatVO.getCoucat_Pic());
			int rowCount=pstmt.executeUpdate();
			System.out.println("修改" + rowCount + "筆資料(序號部分沒有被修改)");

			// Handle any SQL errors
		} catch (SQLException se) {
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
	public List<CoucatVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CoucatVO> coucatlist = new ArrayList<>();

		try {
			con = ds.getConnection();
			System.out.println("Connecting to database successfully!");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CoucatVO coucat=new CoucatVO();
				coucat=new CoucatVO();
				coucat.setCoucat_No(rs.getString("Coucat_No"));
				coucat.setCoucat_Name(rs.getString("Coucat_Name"));
				coucat.setCoucat_Cata(rs.getString("Coucat_Cata"));
				coucat.setCoucat_Value(rs.getInt("Coucat_Value"));
				coucat.setCoucat_Discount(rs.getDouble("Coucat_Discount"));
				coucat.setCoucat_Freep(rs.getInt("Coucat_Freep"));
				coucat.setCoucat_Valid(rs.getTimestamp("Coucat_Valid"));
				coucat.setCoucat_Invalid(rs.getTimestamp("Coucat_Invalid"));
				coucat.setCoucat_Pic(rs.getBytes("Coucat_Pic"));
				coucat.setCoucat_Amo(rs.getInt("Coucat_Amo"));
				coucatlist.add(coucat); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return coucatlist;
	}
	@Override
	public List<CoucatVO> findByCata(String coucat_Cata) {
		Connection con=null;
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		CoucatVO coucatVO =null;
		List<CoucatVO> list =new ArrayList<>();
		try {
			con =  ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(FIND_BY_CATA);
			pstmt.setString(1,coucat_Cata);
			 rs =pstmt.executeQuery();
			while(rs.next()) {
				coucatVO=new CoucatVO();
				coucatVO.setCoucat_No(rs.getString("Coucat_No"));
				coucatVO.setCoucat_Name(rs.getString("Coucat_Name"));
				coucatVO.setCoucat_Cata(rs.getString("Coucat_Cata"));
				coucatVO.setCoucat_Value(rs.getInt("Coucat_Value"));
				coucatVO.setCoucat_Discount(rs.getDouble("Coucat_Discount"));
				coucatVO.setCoucat_Freep(rs.getInt("Coucat_Freep"));
				coucatVO.setCoucat_Valid(rs.getTimestamp("Coucat_Valid"));
				coucatVO.setCoucat_Invalid(rs.getTimestamp("Coucat_Invalid"));
				coucatVO.setCoucat_Pic(rs.getBytes("Coucat_Pic"));
				coucatVO.setCoucat_Amo(rs.getInt("Coucat_Amo"));
				list.add(coucatVO);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try{
					rs.close();	
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try{
					pstmt.close();	
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try{
					con.close();	
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	@Override
	public byte[] getPic(String coucat_No) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] pic = null;
		
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_COUCAT_PIC);
		pstmt.setString(1, coucat_No);
		rs = pstmt.executeQuery();
		rs.next();
		pic = rs.getBytes(1);

		// Handle any driver errors
	}catch(SQLException se)
	{
		throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
	}finally
	{
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

	}return pic;

	}
	@Override
	public CoucatVO getOneCoucat(String coucat_No) {

		CoucatVO coucatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			coucatVO = new CoucatVO();
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COUCAT);
			pstmt.setString(1, coucat_No);
			rs = pstmt.executeQuery();
			rs.next();
			coucatVO.setCoucat_No(rs.getString("Coucat_No"));
			coucatVO.setCoucat_Name(rs.getString("Coucat_Name"));
			coucatVO.setCoucat_Cata(rs.getString("Coucat_Cata"));
			coucatVO.setCoucat_Value(rs.getInt("Coucat_Value"));
			coucatVO.setCoucat_Discount(rs.getDouble("Coucat_Discount"));
			coucatVO.setCoucat_Freep(rs.getInt("Coucat_Freep"));
			coucatVO.setCoucat_Valid(rs.getTimestamp("Coucat_Valid"));
			coucatVO.setCoucat_Invalid(rs.getTimestamp("Coucat_Invalid"));
			coucatVO.setCoucat_Pic(rs.getBytes("Coucat_Pic"));
			coucatVO.setCoucat_Amo(rs.getInt("Coucat_Amo"));

			// Handle any driver errors
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
		return coucatVO;
	}
	

			
				

	
	  
	
	
	
	
}
