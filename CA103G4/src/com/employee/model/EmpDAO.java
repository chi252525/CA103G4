package com.employee.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.empauthority.model.EmpauthorityDAO;
import com.empauthority.model.EmpauthorityVO;
import com.feature.model.FeatureVO;

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
		"insert into EMPLOYEE(EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_PHOTO) VALUES ('E'||LPAD(to_char(employee_seq.NEXTVAL), 9, '0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE order by EMP_NO";
	private static final String GET_ONE_STMT = 
		"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE where EMP_NO = ?";
	private static final String UPDATE =
		"UPDATE EMPLOYEE set BRANCH_NO=? ,EMP_ACNUM=? ,EMP_PSW=? ,EMP_NAME=? ,EMP_GENDER=? ,EMP_POS=? ,EMP_TEL=? ,EMP_STATUS=? ,EMP_PHOTO=? where EMP_NO = ?";
	private static final String GET_ONE_BY_EMPACNUM=
			"select EMP_NO,BRANCH_NO,EMP_ACNUM,EMP_PSW,EMP_NAME,EMP_GENDER,EMP_POS,EMP_TEL,EMP_STATUS,to_char(EMP_CREDATE,'yyyy-mm-dd') EMP_CREDATE,EMP_PHOTO from EMPLOYEE where EMP_ACNUM = ?";
	private static final String GET_ONE_BY_BRANCHNO=
			"select * from employee where branch_no =? order by emp_no";
	
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empVO.getBranch_No());
			pstmt.setString(2, empVO.getEmp_Acnum());
			pstmt.setString(3, empVO.getEmp_Psw());
			pstmt.setString(4, empVO.getEmp_Name());
			pstmt.setString(5, empVO.getEmp_Gender());
			pstmt.setString(6, empVO.getEmp_Pos());
			pstmt.setString(7, empVO.getEmp_Tel());      	
			pstmt.setBytes(8,empVO.getEmp_Photo());
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
			
			
			pstmt.setString(1, empVO.getBranch_No());
			pstmt.setString(2, empVO.getEmp_Acnum());
			pstmt.setString(3, empVO.getEmp_Psw());
			pstmt.setString(4, empVO.getEmp_Name());
			pstmt.setString(5, empVO.getEmp_Gender());
			pstmt.setString(6, empVO.getEmp_Pos());
			pstmt.setString(7, empVO.getEmp_Tel()); 
			pstmt.setString(8, empVO.getEmp_Status()); 
			pstmt.setBytes(9,empVO.getEmp_Photo());
			pstmt.setString(10, empVO.getEmp_No());
			

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
		    	empVO.setEmp_No(rs.getString("emp_no"));
		    	empVO.setBranch_No(rs.getString("branch_no"));
				empVO.setEmp_Acnum(rs.getString("emp_acnum"));
				empVO.setEmp_Psw(rs.getString("emp_psw"));
				empVO.setEmp_Name(rs.getString("emp_name"));
				empVO.setEmp_Gender(rs.getString("emp_gender"));
				empVO.setEmp_Pos(rs.getString("emp_pos"));
				empVO.setEmp_Tel(rs.getString("emp_tel"));      	
				empVO.setEmp_Status(rs.getString("emp_status"));  	
				empVO.setEmp_Credate(rs.getDate("emp_credate"));
				empVO.setEmp_Photo(rs.getBytes("emp_photo"));
				
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
		    	empVO.setEmp_No(rs.getString("emp_no"));
		    	empVO.setBranch_No(rs.getString("branch_no"));
				empVO.setEmp_Acnum(rs.getString("emp_acnum"));
				empVO.setEmp_Psw(rs.getString("emp_psw"));
				empVO.setEmp_Name(rs.getString("emp_name"));
				empVO.setEmp_Gender(rs.getString("emp_gender"));
				empVO.setEmp_Pos(rs.getString("emp_pos"));
				empVO.setEmp_Tel(rs.getString("emp_tel"));      	
				empVO.setEmp_Status(rs.getString("emp_status"));  	
				empVO.setEmp_Credate(rs.getDate("emp_credate"));
				empVO.setEmp_Photo(rs.getBytes("emp_photo"));
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
	@Override
	public void insertWithEmpauthorityVO(EmpVO empVO, List<EmpauthorityVO> empauthorlist) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = ds.getConnection();
			
			//開始新增前須使用交易故將自動commit設為false
			con.setAutoCommit(false);
			
			String[] cols = {"EMP_NO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, empVO.getBranch_No());
			pstmt.setString(2, empVO.getEmp_Acnum());
			pstmt.setString(3, empVO.getEmp_Psw());
			pstmt.setString(4, empVO.getEmp_Name());
			pstmt.setString(5, empVO.getEmp_Gender());
			pstmt.setString(6, empVO.getEmp_Pos());
			pstmt.setString(7, empVO.getEmp_Tel());      	
			pstmt.setBytes(8,empVO.getEmp_Photo());
			pstmt.executeUpdate();
			
			
			System.out.println("員工新增成功，準備開始同時新增員工權限");
			
			//取得自增主鍵值
			String pk_emp_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				pk_emp_no = rs.getString(1);
				System.out.println("自增主鍵值為="+pk_emp_no+"(此為剛剛新增的員工編號)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			
			rs.close();
			
			//現在開始同時新增員工權限
			EmpauthorityDAO dao = new EmpauthorityDAO();
			System.out.println("員工權限empauthorlist.size()="+empauthorlist.size());
			for(EmpauthorityVO empauthorVO : empauthorlist) {
				empauthorVO.setEmp_No(new String(pk_emp_no));
				dao.insert2(empauthorVO, con);
			}
			
			
			//員工權限新增完成後手動確認，並調成自動確認
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增員工編號： " + pk_emp_no + "時,共有員工權限" + empauthorlist.size()
					+ "功能同時被新增");
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public EmpVO findByEmpAcnum(String emp_Acnum) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EMPACNUM);
			
			pstmt.setString(1, emp_Acnum);
			rs = pstmt.executeQuery();
		
		    while(rs.next()) {
		    	
		    	empVO = new EmpVO();
		    	empVO.setEmp_No(rs.getString("emp_no"));
		    	empVO.setBranch_No(rs.getString("branch_no"));
				empVO.setEmp_Acnum(rs.getString("emp_acnum"));
				empVO.setEmp_Psw(rs.getString("emp_psw"));
				empVO.setEmp_Name(rs.getString("emp_name"));
				empVO.setEmp_Gender(rs.getString("emp_gender"));
				empVO.setEmp_Pos(rs.getString("emp_pos"));
				empVO.setEmp_Tel(rs.getString("emp_tel"));      	
				empVO.setEmp_Status(rs.getString("emp_status"));  	
				empVO.setEmp_Credate(rs.getDate("emp_credate"));
				empVO.setEmp_Photo(rs.getBytes("emp_photo"));
				
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
	public List<EmpVO> EmpVOfindByBranchNo(String branch_No) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		List<EmpVO> listemp = new ArrayList();
		ResultSet rs = null;
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_BRANCHNO);
			System.out.println("依分店查詢員工");
			pstmt.setString(1, branch_No);
			 rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				empVO = new EmpVO();
				empVO.setEmp_No(rs.getString("emp_no"));
		    	empVO.setBranch_No(rs.getString("branch_no"));
				empVO.setEmp_Acnum(rs.getString("emp_acnum"));
				empVO.setEmp_Psw(rs.getString("emp_psw"));
				empVO.setEmp_Name(rs.getString("emp_name"));
				empVO.setEmp_Gender(rs.getString("emp_gender"));
				empVO.setEmp_Pos(rs.getString("emp_pos"));
				empVO.setEmp_Tel(rs.getString("emp_tel"));      	
				empVO.setEmp_Status(rs.getString("emp_status"));  	
				empVO.setEmp_Credate(rs.getDate("emp_credate"));
				empVO.setEmp_Photo(rs.getBytes("emp_photo"));
				listemp.add(empVO);
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if ( rs !=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}				
			}
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
				
		return listemp;
	}
}
