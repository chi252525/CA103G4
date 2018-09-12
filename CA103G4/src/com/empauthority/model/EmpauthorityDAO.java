package com.empauthority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpauthorityDAO implements EmpauthorityDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "CHIAPAO";
	private static final String PASSWORD = "CHIAPAO";
	private static final String INSERT_STMT=
			"INSERT INTO EMPAUTHORITY(EMP_NO,FEA_NO) VALUES(?,?)";
	private static final String FINDBY_EMPNO=
			"SELECT FEA_NO FROM EMPAUTHORITY WHERE EMP_NO=?";
	
	
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
	public List<EmpauthorityVO> findByEmp(EmployeeVO empVO) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt =null;
		List<EmpauthorityVO> empauthlist = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FINDBY_EMPNO);
			System.out.println("連線成功");
			pstmt.setString(1,empVO.getEmp_No());
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

}
