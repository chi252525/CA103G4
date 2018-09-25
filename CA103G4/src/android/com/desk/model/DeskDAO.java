package android.com.desk.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.branch.model.BranchVO;


public class DeskDAO implements DeskDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"Insert into DESK (DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS) values ('D'||LPAD(to_char(desk_seq.NEXTVAL), 9, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS from DESK order by DEK_NO";
	private static final String GET_ONE_STMT = 
		"select DEK_NO,BRANCH_NO,DEK_ID,DEK_SET,DEK_STATUS from DESK where DEK_NO = ?";
	private static final String UPDATE =
		"UPDATE DESK set BRANCH_NO=?,DEK_ID=?,DEK_SET=?,DEK_STATUS=? where DEK_NO =?";
	private static final String GET_BRANCH_STMT = 
		"SELECT * FROM DESK WHERE Branch_NO =?";
	private static final String UPDATE_STATUS =
		"UPDATE DESK set DEK_STATUS=? where DEK_NO =?";
	private static final String GET_DEKID_STMT = 
		"SELECT * FROM DESK WHERE Branch_NO =?";
	
	
	
	@Override
	public List<DeskVO> getByDekNo(String deck_no) {
		List<DeskVO> list = new ArrayList<DeskVO>();
		DeskVO deskVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_BRANCH_STMT);
			pstmt.setString(1, deck_no);
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void updateDekStatus(String dek_no, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(UPDATE_STATUS);
			pstmt.setInt(1, 1);
			pstmt.setString(2, dek_no);
			pstmt.executeUpdate();

			// Handle any driver errors
	
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
		}
	}

	@Override
	public List<DeskVO> getByBranchNo(String branch_No) {
		List<DeskVO> list = new ArrayList<DeskVO>();
		DeskVO deskVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_BRANCH_STMT);
			pstmt.setString(1, branch_No);
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void insert(DeskVO deskVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, deskVO.getBranch_no());
			pstmt.setString(2, deskVO.getDek_id());
			pstmt.setInt(3, deskVO.getDek_set());
			pstmt.setInt(4, deskVO.getDek_status());
			pstmt.executeUpdate();
			
			System.out.println("新增成功");
			
			
	
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			
			pstmt.setString(1, deskVO.getBranch_no());
			pstmt.setString(2, deskVO.getDek_id());
			pstmt.setInt(3, deskVO.getDek_set());
			pstmt.setInt(4, deskVO.getDek_status());
			pstmt.setString(5, deskVO.getDek_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
	
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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
	
}
	

