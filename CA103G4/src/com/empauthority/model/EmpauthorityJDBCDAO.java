package com.empauthority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.model.EmpVO;

public class EmpauthorityJDBCDAO implements EmpauthorityDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "testG4";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT=
			"INSERT INTO EMPAUTHORITY(EMP_NO,FEA_NO) VALUES(?,?)";
	private static final String FINDBY_EMPNO=
			"SELECT FEA_NO FROM EMPAUTHORITY WHERE EMP_NO=?";
	private static final String DELETEBY_EMPNO=
			"DELETE FROM EMPAUTHORITY WHERE EMP_NO=?";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(EmpauthorityVO empauthorVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println("成功連線");
			pstmt.setString(1, empauthorVO.getEmp_No());
			pstmt.setString(2, empauthorVO.getFea_No());
			int rowCount = pstmt.executeUpdate();
			System.out.println("設定員工功能"+rowCount+"個成功");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(EmpauthorityVO empauthorVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmpauthorityVO> oneEmpFealist(String emp_No) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt =null;
		List<EmpauthorityVO> empauthlist = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FINDBY_EMPNO);
			System.out.println("連線成功");
			pstmt.setString(1,emp_No);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpauthorityVO empauthorVO = new EmpauthorityVO();
				empauthorVO.setFea_No(rs.getString(1));
				empauthlist.add(empauthorVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return empauthlist;
	}

	@Override
	public void insert2(EmpauthorityVO empauthorVO, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try {

			pstmt=con.prepareStatement(INSERT_STMT);
			System.out.println("上一個過來的連線成功");
			System.out.println(empauthorVO.getEmp_No());
			System.out.println(empauthorVO.getFea_No());
			
			pstmt.setString(1, empauthorVO.getEmp_No());
			System.out.println("pstmt放入empauthorVO.getEmp_No()");
			
			pstmt.setString(2, empauthorVO.getFea_No());
			System.out.println("pstmt放入empauthorVO.getFea_No()");
			
			pstmt.executeUpdate();

			System.out.println("新增權限OK");
			
			
		} catch (SQLException se) {
			try {
				System.err.print("Transaction is being ");
				System.err.println("rolled back-由-empauthority");
				con.rollback();
				
			}catch(SQLException se2){
				throw new RuntimeException("A database error occured. " 
						+ se.getMessage());
				
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
			
		}
		
		
	}

	@Override
	public void delete(String emp_No) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		PreparedStatement pstmt= null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(DELETEBY_EMPNO);
			pstmt.setString(1, emp_No);
			int rowCount = pstmt.executeUpdate();
			System.out.println("已刪除"+rowCount+"筆資料");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
		
	}

	@Override
	public void delete2(String emp_No, List<EmpauthorityVO> empauthorlist) {
		// TODO Auto-generated method stub
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETEBY_EMPNO);
			pstmt.setString(1, emp_No);
			int rowCount = pstmt.executeUpdate();
			System.out.println("已刪除"+rowCount+"筆資料");
			
			//現在開始新增員工權限
			EmpauthorityJDBCDAO dao = new EmpauthorityJDBCDAO();

			for(EmpauthorityVO empauthorVO : empauthorlist) {
				empauthorVO.setEmp_No(emp_No);
				dao.insert2(empauthorVO, con);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
