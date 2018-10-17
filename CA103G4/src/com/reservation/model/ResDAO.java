package com.reservation.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;



public class ResDAO implements ResDAO_interface{
	
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
		"Insert into RESERVATION (RES_NO,MEM_NO,DEK_NO,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE) VALUES ('R'||LPAD(to_char(reservation_seq.NEXTVAL), 9, '0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION order by RES_NO";
	private static final String GET_ONE_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION where RES_NO =?";
	private static final String GET_ONE_BGTIME = 
			"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION where RES_TIMEBG =? order by RES_NO";
	private static final String DELETE = 
		"DELETE FROM RESERVATION where RES_NO = ?";
	private static final String UPDATE =
		"UPDATE RESERVATION set MEM_NO=? ,DEK_NO=? ,RES_TIMEBG=? ,RES_TIMEFN=? ,RES_PEOPLE=? ,RES_STATUS=? where RES_NO = ?";
	     
	@Override
	public void insert(ResVO resVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, resVO.getMem_no());
			pstmt.setString(2, resVO.getDek_no());
			pstmt.setTimestamp(3, resVO.getRes_timebg());
			pstmt.setTimestamp(4, resVO.getRes_timefn());
			pstmt.setInt(5, resVO.getRes_people());      	
			
			
			
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, resVO.getMem_no());
			pstmt.setString(2, resVO.getDek_no());
			pstmt.setTimestamp(3, resVO.getRes_timebg());
			pstmt.setTimestamp(4, resVO.getRes_timefn());
			pstmt.setInt(5, resVO.getRes_people());
			pstmt.setInt(6, resVO.getRes_status()); 
			pstmt.setString(7, resVO.getRes_no());
			
			

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public void delete(String res_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
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
	public ResVO findByPrimaryKey(String res_no) {
		
		ResVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, res_no);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	resVO = new ResVO();
		    	resVO.setRes_no(rs.getString("res_no"));
		    	resVO.setMem_no(rs.getString("mem_no"));
		    	resVO.setDek_no(rs.getString("dek_no"));
		    	resVO.setRes_submit(rs.getTimestamp("res_submit"));
		    	resVO.setRes_timebg(rs.getTimestamp("res_timebg"));
		    	resVO.setRes_timefn(rs.getTimestamp("res_timefn"));
				resVO.setRes_people(rs.getInt("res_people"));
				resVO.setRes_status(rs.getInt("res_status"));
			
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
		return resVO;
	}
	
	@Override
	public List<ResVO> getAll() {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO resVO = null;	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	resVO = new ResVO();
		    	resVO.setRes_no(rs.getString("res_no"));
		    	resVO.setMem_no(rs.getString("mem_no"));
		    	resVO.setDek_no(rs.getString("dek_no"));
		    	resVO.setRes_submit(rs.getTimestamp("res_submit"));
		    	resVO.setRes_timebg(rs.getTimestamp("res_timebg"));
		    	resVO.setRes_timefn(rs.getTimestamp("res_timefn"));
				resVO.setRes_people(rs.getInt("res_people"));
				resVO.setRes_status(rs.getInt("res_status"));
				list.add(resVO);
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

	@Override
	public void insert2(ResVO resVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {		
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println("從上一個過來的連線:成功");
			pstmt.setString(1, resVO.getMem_no());
			pstmt.setString(2, resVO.getDek_no());
			pstmt.setTimestamp(3, resVO.getRes_timebg());
			pstmt.setTimestamp(4, resVO.getRes_timefn());
			pstmt.setInt(5, resVO.getRes_people());      				
			pstmt.executeUpdate();
			
			System.out.println("新增成功");
			

	
		}catch(SQLException se) {
			
			try {
				System.err.println("Transaction is begin");
				System.err.println("rolled back from ResDAO Insert2");
				con.rollback();
			} catch (SQLException e) {
				throw new RuntimeException("A datsbase error occured."
						+ se.getMessage());

			}
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
		}
		
	}
	
	@Override
	public List<ResVO> getAllByBGNO(String res_timebg) {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO resVO = null;	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BGTIME);			
			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(res_timebg));
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	resVO = new ResVO();
		    	resVO.setRes_no(rs.getString("res_no"));
		    	resVO.setMem_no(rs.getString("mem_no"));
		    	resVO.setDek_no(rs.getString("dek_no"));
		    	resVO.setRes_submit(rs.getTimestamp("res_submit"));
		    	resVO.setRes_timebg(rs.getTimestamp("res_timebg"));
		    	resVO.setRes_timefn(rs.getTimestamp("res_timefn"));
				resVO.setRes_people(rs.getInt("res_people"));
				resVO.setRes_status(rs.getInt("res_status"));
				list.add(resVO);
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


