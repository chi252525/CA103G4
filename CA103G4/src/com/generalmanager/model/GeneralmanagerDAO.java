package com.generalmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GeneralmanagerDAO implements GeneralmanagerDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "CHIAPAO";
	private static final String PASSWORD = "CHIAPAO";
	
	private static final String INSERT_STMT =
			"INSERT INTO GENERALMANAGER(MGER_NO,MGER_ACNUM,MGER_PSW) VALUES(?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE GENERALMANAGER SET MGER_ACNUM=?,MGER_PSW=? WHERE MGER_NO =?";
	private static final String DELET_STMT =
			"DELETE FROM GENERALMANAGER WHERE  MGER_NO = ?";
	static {
		try {
		Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	@Override
	public void insert(GeneralmanagerVO mgerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("連線成功");	
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, mgerVO.getMger_No());
			pstmt.setString(2, mgerVO.getMger_Acnum());
			pstmt.setString(3, mgerVO.getMger_Psw());
			int rowCount = pstmt.executeUpdate();
			System.out.println("新增"+rowCount+" 筆資料");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(con != null) {
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
	public void update(GeneralmanagerVO mgerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, mgerVO.getMger_Acnum());
			pstmt.setString(2, mgerVO.getMger_Psw());			
			pstmt.setString(3, mgerVO.getMger_No());			
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改 "+ rowCount + " 筆資料");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
			
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
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
	public void delete(String mger_No) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELET_STMT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public GeneralmanagerVO findByPrimaryKey(String mger_No) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeneralmanagerVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
