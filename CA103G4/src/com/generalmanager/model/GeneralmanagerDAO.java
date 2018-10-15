package com.generalmanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GeneralmanagerDAO implements GeneralmanagerDAO_interface{
	
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
			"INSERT INTO GENERALMANAGER(MGER_NO,MGER_ACNUM,MGER_PSW) VALUES(?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE GENERALMANAGER SET MGER_ACNUM=?,MGER_PSW=? WHERE MGER_NO =?";
	private static final String DELET_STMT =
			"DELETE FROM GENERALMANAGER WHERE  MGER_NO = ?";
	private static final String FINDBYACNUM=
			"SELECT *FROM GENERALMANAGER WHERE MGER_ACNUM=?";
	@Override
	public void insert(GeneralmanagerVO mgerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

	@Override
	public GeneralmanagerVO findByAcnum(String mger_Acnum) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt=null;
		GeneralmanagerVO mgerVO = new GeneralmanagerVO();
		try {
			con=ds.getConnection();
			pstmt= con.prepareStatement(FINDBYACNUM);
			pstmt.setString(1, mger_Acnum);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				mgerVO.setMger_Acnum(rs.getString("mger_Acnum"));
				mgerVO.setMger_No(rs.getString("mger_No"));
				mgerVO.setMger_Psw(rs.getString("mger_Psw"));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
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
		
		return mgerVO;
	}

}
