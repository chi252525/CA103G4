package android.com.employee.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;


public class EmpDAO implements EmpDAO_interface{
	
	
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
		"insert into EMPLOYEE(EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_PHOTO) VALUES ('E'||LPAD(to_char(employee_seq.NEXTVAL), 9, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE order by EMP_NO";
	private static final String GET_ONE_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE where EMP_NO = ?";
	private static final String UPDATE =
		"UPDATE EMPLOYEE set BRANCH_NO=? ,EMP_ACNUM=? ,EMP_PSW=? ,EMP_NAME=? ,EMP_GENDER=? ,EMP_POS=? ,EMP_TEL=? ,EMP_STATUS=? where EMP_NO = ?";
	private static final String FIND_BY_ID_PASWD =
		"SELECT * FROM EMPLOYEE WHERE EMP_ACNUM = ? AND EMP_PSW = ?";
	private static final String FIND_EMPNAME_BY_EMPNO =
		"SELECT EMP_NAME FROM EMPLOYEE WHERE EMP_NO = ?";
	private static final String FIND_IMG_BY_NAME = 
		"SELECT EMP_PHOTO FROM EMPLOYEE WHERE EMP_NAME = ?";
	
	
	@Override
	public byte[] getImageByName(String emp_Name) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMG_BY_NAME);
			pstmt.setString(1, emp_Name);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				picture = rs.getBytes(1);
			}
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
		return picture;
	}
	@Override
	public String findEmpNameByPrimaryKey(String emp_no) {
		String empName = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_EMPNAME_BY_EMPNO);
			
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();
		    rs.next();
		    empName = rs.getString("emp_name");
			
	
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
		return empName;
	}
	@Override
	public EmpVO isEmployee(String emp_Acnum, String emp_Psw) {
		
		EmpVO empVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_ID_PASWD);
			pstmt.setString(1, emp_Acnum);
			pstmt.setString(2, emp_Psw);
			rs = pstmt.executeQuery();
			
		    rs.next();
	    	empVO = new EmpVO();
	    	empVO.setEmp_no(rs.getString("emp_no"));
	    	empVO.setBranch_no(rs.getString("branch_no"));
			empVO.setEmp_acnum(rs.getString("emp_acnum"));
			empVO.setEmp_psw(rs.getString("emp_psw"));
			empVO.setEmp_name(rs.getString("emp_name"));
			empVO.setEmp_gender(rs.getString("emp_gender"));
			empVO.setEmp_pos(rs.getString("emp_pos"));
			empVO.setEmp_tel(rs.getString("emp_tel"));      	
			empVO.setEmp_status(rs.getString("emp_status"));  	
			empVO.setEmp_credate(rs.getDate("emp_credate"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empVO;
	}
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empVO.getBranch_no());
			pstmt.setString(2, empVO.getEmp_acnum());
			pstmt.setString(3, empVO.getEmp_psw());
			pstmt.setString(4, empVO.getEmp_name());
			pstmt.setString(5, empVO.getEmp_gender());
			pstmt.setString(6, empVO.getEmp_pos());
			pstmt.setString(7, empVO.getEmp_tel());      	
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
//	public static byte[] getPictureByteArray(String path) throws IOException{
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

		
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, empVO.getBranch_no());
			pstmt.setString(2, empVO.getEmp_acnum());
			pstmt.setString(3, empVO.getEmp_psw());
			pstmt.setString(4, empVO.getEmp_name());
			pstmt.setString(5, empVO.getEmp_gender());
			pstmt.setString(6, empVO.getEmp_pos());
			pstmt.setString(7, empVO.getEmp_tel()); 
			pstmt.setString(8, empVO.getEmp_status()); 
			pstmt.setString(9, empVO.getEmp_no());
			

			pstmt.executeUpdate();

		
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
	public EmpVO findByPrimaryKey(String emp_no) {
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	empVO = new EmpVO();
		    	empVO.setEmp_no(rs.getString("emp_no"));
		    	empVO.setBranch_no(rs.getString("branch_no"));
				empVO.setEmp_acnum(rs.getString("emp_acnum"));
				empVO.setEmp_psw(rs.getString("emp_psw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_gender(rs.getString("emp_gender"));
				empVO.setEmp_pos(rs.getString("emp_pos"));
				empVO.setEmp_tel(rs.getString("emp_tel"));      	
				empVO.setEmp_status(rs.getString("emp_status"));  	
				empVO.setEmp_credate(rs.getDate("emp_credate"));
				
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
		return empVO;
	}
	
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	empVO = new EmpVO();
		    	empVO.setEmp_no(rs.getString("emp_no"));
		    	empVO.setBranch_no(rs.getString("branch_no"));
				empVO.setEmp_acnum(rs.getString("emp_acnum"));
				empVO.setEmp_psw(rs.getString("emp_psw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_gender(rs.getString("emp_gender"));
				empVO.setEmp_pos(rs.getString("emp_pos"));
				empVO.setEmp_tel(rs.getString("emp_tel"));      	
				empVO.setEmp_status(rs.getString("emp_status"));  	
				empVO.setEmp_credate(rs.getDate("emp_credate"));
				list.add(empVO);
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
