package com.desk.model;

import java.util.*;

import com.reservation.model.ResVO;

import java.sql.*;


public class DeskJDBCDAO implements DeskDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "yes";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"Insert into DESK (DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS) values ('D'||LPAD(to_char(desk_seq.NEXTVAL), 9, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS from DESK order by DEK_NO";
	private static final String GET_ONE_STMT = 
		"select DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS from DESK where DEK_NO = ?";
	private static final String UPDATE =
		"UPDATE DESK set BRANCH_NO=?,DEK_ID=?,DEK_SET=?,DEK_STATUS=? where DEK_NO =?";
	
	@Override
	public void insert(DeskVO deskVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, deskVO.getBranch_no());
			pstmt.setString(2, deskVO.getDek_id());
			pstmt.setInt(3, deskVO.getDek_set());
			pstmt.setInt(4, deskVO.getDek_status());
			pstmt.executeUpdate();
			
			System.out.println("新增成功");
			
			pstmt.clearParameters();
	
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(DeskVO deskVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			
			pstmt.setString(1, deskVO.getBranch_no());
			pstmt.setString(2, deskVO.getDek_id());
			pstmt.setInt(3, deskVO.getDek_set());
			pstmt.setInt(4, deskVO.getDek_status());
			pstmt.setString(5, deskVO.getDek_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public DeskVO findByPrimaryKey(String dek_no) {
		
		DeskVO deskVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, dek_no);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	deskVO = new DeskVO();				
				deskVO.setDek_no(rs.getString("dek_no"));
				deskVO.setBranch_no(rs.getString("branch_no"));
				deskVO.setDek_id(rs.getString("dek_id"));
				deskVO.setDek_set(rs.getInt("dek_set"));
				deskVO.setDek_status(rs.getInt("dek_status"));
				
		    }
	
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return deskVO;
	}
	
	@Override
	public List<DeskVO> getAll() {
		List<DeskVO> list = new ArrayList<DeskVO>();
		DeskVO deskVO = null;	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	deskVO = new DeskVO();				
				deskVO.setDek_no(rs.getString("dek_no"));
				deskVO.setBranch_no(rs.getString("branch_no"));
				deskVO.setDek_id(rs.getString("dek_id"));
				deskVO.setDek_set(rs.getInt("dek_set"));
				deskVO.setDek_status(rs.getInt("dek_status"));
				list.add(deskVO);
		    }
	
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		DeskJDBCDAO dao = new DeskJDBCDAO();
		
		
		//新增
		DeskVO deskVO1 = new DeskVO();
		deskVO1.setBranch_no("0002");
		deskVO1.setDek_id("G2");
		deskVO1.setDek_set(2);
		deskVO1.setDek_status(2);
		dao.insert(deskVO1);
		
		//修改
		DeskVO deskVO2 = new DeskVO();
		deskVO2.setDek_no("D000000001");
		deskVO2.setBranch_no("0001");
		deskVO2.setDek_id("G2");
		deskVO2.setDek_set(2);
		deskVO2.setDek_status(2);
		dao.update(deskVO2);	
		
		// 查詢
		DeskVO deskVO3 = dao.findByPrimaryKey("D000000001");
		System.out.println(deskVO3.getDek_no()+ ",");
		System.out.println(deskVO3.getBranch_no()+ ",");
		System.out.println(deskVO3.getDek_id()+ ",");
		System.out.println(deskVO3.getDek_set()+ ",");
		System.out.println(deskVO3.getDek_status()+ ",");
		System.out.println("---------------------");
				
		//查詢
		List<DeskVO> list = dao.getAll();
		for(DeskVO aEmp : list) {
		System.out.println(aEmp.getDek_no()+ ",");
		System.out.println(aEmp.getBranch_no()+ ",");
		System.out.println(aEmp.getDek_id()+ ",");
		System.out.println(aEmp.getDek_set()+ ",");
		System.out.println(aEmp.getDek_status()+ ",");
		System.out.println("---------------------");
		}
	}

	@Override
	public void insertAutoGK(DeskVO deskVO, ResVO resVO) {
		// TODO Auto-generated method stub
		
	}
	
}
	

