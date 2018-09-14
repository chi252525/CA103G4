package com.reservation.model;

import java.util.*;
import java.sql.*;
//import java.text.Format;
//import java.text.SimpleDateFormat;


public class ResJDBCDAO implements ResDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "yes";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"Insert into RESERVATION (RES_NO,MEM_NO,DEK_NO,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS) VALUES ('R'||LPAD(to_char(reservation_seq.NEXTVAL), 9, '0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION order by RES_NO";
	private static final String GET_ONE_STMT = 
		"select RES_NO,MEM_NO,DEK_NO,RES_SUBMIT,RES_TIMEBG,RES_TIMEFN,RES_PEOPLE,RES_STATUS from RESERVATION where RES_NO =?";
	private static final String DELETE = 
		"DELETE FROM RESERVATION where RES_NO = ?";
	private static final String UPDATE =
		"UPDATE RESERVATION set MEM_NO=? ,DEK_NO=? ,RES_TIMEBG=? ,RES_TIMEFN=? ,RES_PEOPLE=? ,RES_STATUS=? where RES_NO = ?";
	     
	@Override
	public void insert(ResVO resVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, resVO.getMem_no());
			pstmt.setString(2, resVO.getDek_no());
			pstmt.setTimestamp(3, resVO.getRes_timebg());
			pstmt.setTimestamp(4, resVO.getRes_timefn());
			pstmt.setInt(5, resVO.getRes_people());
			pstmt.setInt(6, resVO.getRes_status()); 
			pstmt.setString(7, resVO.getRes_no());
			
			

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
	public void delete(String res_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

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
	public ResVO findByPrimaryKey(String res_no) {
		
		ResVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	public static void main(String[] args){
		ResJDBCDAO dao = new ResJDBCDAO();
//		Format sfm1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//�s�W
		ResVO resVO1 = new ResVO();
		resVO1.setMem_no("M000001");
		resVO1.setDek_no("D000000001");
		resVO1.setRes_timebg(java.sql.Timestamp.valueOf("2018-09-11 13:55"+":00"));
		resVO1.setRes_timefn(java.sql.Timestamp.valueOf("2018-09-11 15:55"+":00"));
		resVO1.setRes_people(50);
		resVO1.setRes_status(1);
		dao.insert(resVO1);
		
		//�ק�
		ResVO resVO2 = new ResVO();
		resVO2.setMem_no("M000002");
		resVO2.setDek_no("D000000002");
		resVO2.setRes_timebg(java.sql.Timestamp.valueOf("2018-10-11 13:55"+":00"));
		resVO2.setRes_timefn(java.sql.Timestamp.valueOf("2018-09-11 13:55"+":00"));
		resVO2.setRes_people(60);
		resVO2.setRes_status(2);
		resVO2.setRes_no("R000000001");
		dao.update(resVO2);	
		System.out.println("SUCCESS");
		
		// �d��
		ResVO resVO3 = dao.findByPrimaryKey("R000000001");
		System.out.println(resVO3.getRes_no()+ ",");
		System.out.println(resVO3.getMem_no()+ ",");
		System.out.println(resVO3.getDek_no()+ ",");
		System.out.println(resVO3.getRes_submit()+ ",");
		System.out.println(resVO3.getRes_timebg()+ ",");
		System.out.println(resVO3.getRes_timefn()+ ",");
		System.out.println(resVO3.getRes_people()+ ",");
		System.out.println(resVO3.getRes_status());
		System.out.println("---------------------");
				
		//�d�ߥ���
		List<ResVO> list = dao.getAll();
		for(ResVO aEmp : list) {
		System.out.println(aEmp.getRes_no()+ ",");
		System.out.println(aEmp.getMem_no()+ ",");
		System.out.println(aEmp.getDek_no()+ ",");
		System.out.println(aEmp.getRes_submit()+ ",");
		System.out.println(aEmp.getRes_timebg()+ ",");
		System.out.println(aEmp.getRes_timefn()+ ",");
		System.out.println(aEmp.getRes_people()+ ",");
		System.out.println(aEmp.getRes_status()+ ",");
		
		}
	}
	
}

