package android.com.reservation.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.desk.model.*;

import java.sql.*;
//import java.text.Format;
//import java.text.SimpleDateFormat;


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
		"Insert into RESERVATION (RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS) VALUES ('R'||LPAD(to_char(reservation_seq.NEXTVAL), 9, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION order by RES_NO";
	private static final String GET_ONE_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION where RES_NO =?";
	private static final String DELETE = 
		"DELETE FROM RESERVATION where RES_NO = ?";
	private static final String UPDATE =
		"UPDATE RESERVATION set MEM_NO=? ,DEK_NO=? ,RES_TIMEBG=? ,RES_TIMEFN=? ,RES_PEOPLE=? ,RES_STATUS=? where RES_NO = ?";
	
	private static final String GET_DEKNO_STMT = 
			"select * from RESERVATION";
	     
	
	
	@Override
	public List<String> findDekIdWithResTimefn() {
		List<String> list = new ArrayList<>();
		DeskDAO_interface ddao = new DeskDAO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DEKNO_STMT);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	if(rs.getTimestamp("res_timefn").getTime() >= System.currentTimeMillis()) {
		    		String dek_no = rs.getString("dek_no");
					list.add(ddao.findByPrimaryKey(dek_no).getDek_id());
		    	}
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
	public String addWithBranchNo(String branchNo, ResVO resVO, String seatStr) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_dek_no = null;
		String next_res_no = null;
		try {

			con = ds.getConnection();

			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增一筆新的DeskTable資料作為存放座位字串使用
			DeskDAO_interface ddao = new DeskDAO();
			DeskVO deskVO = new DeskVO();
			deskVO.setBranch_no(branchNo);
			deskVO.setDek_id(seatStr);
			deskVO.setDek_set(0);
			deskVO.setDek_status(2);
			next_dek_no = ddao.insert(deskVO,con);
			
			String[] cols = { "res_no" };
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, resVO.getMem_no());
			pstmt.setString(2, next_dek_no);
			pstmt.setTimestamp(3, resVO.getRes_submit());
			pstmt.setTimestamp(4, resVO.getRes_timebg());
			pstmt.setTimestamp(5, resVO.getRes_timefn());
			pstmt.setInt(6, resVO.getRes_people());      	
			pstmt.setInt(7,resVO.getRes_status());
			pstmt.executeUpdate();
			
			// 取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_res_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_res_no + "(剛新增成功的訂位紀錄編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

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
		return next_res_no;
	}

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
			pstmt.setInt(6,resVO.getRes_status());
			
			
			
			pstmt.executeUpdate();
			
			System.out.println("新增成功");
			
//			pstmt.clearParameters();
	
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
	public void delete(String res_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

			pstmt.executeUpdate();

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
	
}


