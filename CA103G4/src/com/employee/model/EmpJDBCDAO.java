package com.employee.model;

import java.util.*;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;


public class EmpJDBCDAO implements EmpDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "yes";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"insert into EMPLOYEE(EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_PHOTO) VALUES ('E'||LPAD(to_char(employee_seq.NEXTVAL), 9, '0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE order by EMP_NO";
	private static final String GET_ONE_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE where EMP_NO = ?";
	private static final String UPDATE =
		"UPDATE EMPLOYEE set BRANCH_NO=? ,EMP_ACNUM=? ,EMP_PSW=? ,EMP_NAME=? ,EMP_GENDER=? ,EMP_POS=? ,EMP_TEL=? ,EMP_STATUS=? ,EMP_PHOTO=? where EMP_NO = ?";
	
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empVO.getBranch_no());
			pstmt.setString(2, empVO.getEmp_acnum());
			pstmt.setString(3, empVO.getEmp_psw());
			pstmt.setString(4, empVO.getEmp_name());
			pstmt.setString(5, empVO.getEmp_gender());
			pstmt.setString(6, empVO.getEmp_pos());
			pstmt.setString(7, empVO.getEmp_tel());      	
			pstmt.setBytes(8,empVO.getEmp_photo());
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
	public static byte[] getPictureByteArray(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, empVO.getBranch_no());
			pstmt.setString(2, empVO.getEmp_acnum());
			pstmt.setString(3, empVO.getEmp_psw());
			pstmt.setString(4, empVO.getEmp_name());
			pstmt.setString(5, empVO.getEmp_gender());
			pstmt.setString(6, empVO.getEmp_pos());
			pstmt.setString(7, empVO.getEmp_tel()); 
			pstmt.setString(8, empVO.getEmp_status()); 
			pstmt.setBytes(9,empVO.getEmp_photo());
			pstmt.setString(10, empVO.getEmp_no());
			

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
	public EmpVO findByPrimaryKey(String emp_no) {
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				empVO.setEmp_photo(rs.getBytes("emp_photo"));
				
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				empVO.setEmp_photo(rs.getBytes("emp_photo"));
				list.add(empVO);
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
	
	
	public static void main(String[] args) throws IOException {
		EmpJDBCDAO dao = new EmpJDBCDAO();
		
//		java.util.Date du = new java.util.Date();
//		long long1 = du.getTime();
//		java.sql.Date ds1 = new java.sql.Date (long1);
		
		//新增
		EmpVO empVO1 = new EmpVO();
		empVO1.setBranch_no("0001");
		empVO1.setEmp_acnum("65987");
		empVO1.setEmp_psw("95753");
		empVO1.setEmp_name("帥帥");
		empVO1.setEmp_gender("G1");
		empVO1.setEmp_pos("工讀生");
		empVO1.setEmp_tel("0976100100");
		empVO1.setEmp_photo(getPictureByteArray("img/000.jpg"));
		dao.insert(empVO1);
		
		//修改
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmp_no("E000000008");
		empVO2.setBranch_no("0002");
		empVO2.setEmp_acnum("6598700");
		empVO2.setEmp_psw("9575300");
		empVO2.setEmp_name("藍藍");
		empVO2.setEmp_gender("G2");
		empVO2.setEmp_pos("經理");
		empVO2.setEmp_tel("0976100100");
		empVO2.setEmp_status("E2");
		empVO2.setEmp_photo(getPictureByteArray("img/001.png"));
		dao.update(empVO2);	
		
		// 查詢
		EmpVO empVO3 = dao.findByPrimaryKey("E000000001");
		System.out.println(empVO3.getEmp_no()+ ",");
		System.out.println(empVO3.getBranch_no()+ ",");
		System.out.println(empVO3.getEmp_acnum()+ ",");
		System.out.println(empVO3.getEmp_psw()+ ",");
		System.out.println(empVO3.getEmp_name()+ ",");
		System.out.println(empVO3.getEmp_gender()+ ",");
		System.out.println(empVO3.getEmp_pos()+ ",");
		System.out.println(empVO3.getEmp_tel()+ ",");
		System.out.println(empVO3.getEmp_status()+ ",");
		System.out.println(empVO3.getEmp_credate()+ ",");
		System.out.println(empVO3.getEmp_photo());
		System.out.println("---------------------");
				
		//查詢
		List<EmpVO> list = dao.getAll();
		for(EmpVO aEmp : list) {
			System.out.println(aEmp.getEmp_no()+ ",");
			System.out.println(aEmp.getBranch_no()+ ",");
			System.out.println(aEmp.getEmp_acnum()+ ",");
			System.out.println(aEmp.getEmp_psw()+ ",");
			System.out.println(aEmp.getEmp_name()+ ",");
			System.out.println(aEmp.getEmp_gender()+ ",");
			System.out.println(aEmp.getEmp_pos()+ ",");
			System.out.println(aEmp.getEmp_tel()+ ",");
			System.out.println(aEmp.getEmp_status()+ ",");
			System.out.println(aEmp.getEmp_credate()+ ",");
			System.out.println(aEmp.getEmp_photo());
		
		}
	}
	
}