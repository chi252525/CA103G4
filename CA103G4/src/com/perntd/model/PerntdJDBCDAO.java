package com.perntd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerntdJDBCDAO implements PerntdDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Test";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO PERNTD VALUES('P'||LPAD(to_char(perntd_seq.NEXTVAL),2,'0'),?,?,?,to_char(sysdate,'yyyy/mm/dd'))";
	private static final String UPDATE_STMT =
			"UPDATE PERNTD SET MEM_NO=?,NT_NO=?,PERNTD_CONT=?,PERNTD_DATE=? WHERE PERNTD_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM PERNTD WHERE PERNTD_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT PERNTD_NO,MEM_NO,NT_NO,PERNTD_CONT,PERNTD_DATE FROM PERNTD WHERE PERNTD_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT PERNTD_NO,MEM_NO,NT_NO,PERNTD_CONT,PERNTD_DATE FROM PERNTD ORDER BY PERNTD_NO";

	@Override
	public void insert(PerntdVO perntdVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, perntdVO.getMem_No());
			pstmt.setString(2, perntdVO.getNt_No());
			pstmt.setString(3, perntdVO.getPerntd_Cont());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(PerntdVO perntdVO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, perntdVO.getMem_No());
			pstmt.setString(2, perntdVO.getNt_No());
			pstmt.setString(3, perntdVO.getPerntd_Cont());
			pstmt.setString(4, perntdVO.getPerntd_Date());
			pstmt.setString(5, perntdVO.getPerntd_No());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void delete(String perntd_No) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, perntd_No);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public PerntdVO findByPrimaryKey(String perntd_No) {
		// TODO Auto-generated method stub
		PerntdVO perntdVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, perntd_No);
			rs = pstmt.executeQuery();
			rs.next();
			perntdVO = new PerntdVO();
			perntdVO.setPerntd_No(rs.getString("PERNTD_NO"));
			perntdVO.setMem_No(rs.getString("MEM_NO"));
			perntdVO.setNt_No(rs.getString("NT_NO"));
			perntdVO.setPerntd_Cont(rs.getString("PERNTD_CONT"));
			perntdVO.setPerntd_Date(rs.getString("PERNTD_DATE"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return perntdVO;
	}

	@Override
	public List<PerntdVO> getAll() {
		// TODO Auto-generated method stub
		PerntdVO perntdVO = null;
		List<PerntdVO> list = new ArrayList<>();;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				perntdVO = new PerntdVO();
				perntdVO.setPerntd_No(rs.getString("PERNTD_NO"));
				perntdVO.setMem_No(rs.getString("MEM_NO"));
				perntdVO.setNt_No(rs.getString("NT_NO"));
				perntdVO.setPerntd_Cont(rs.getString("PERNTD_CONT"));
				perntdVO.setPerntd_Date(rs.getString("PERNTD_DATE"));
				list.add(perntdVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		PerntdJDBCDAO dao = new PerntdJDBCDAO();
		//insert
		PerntdVO perntdVO1 = new PerntdVO();
		perntdVO1.setMem_No("M000001");
		perntdVO1.setNt_No("NT01");
		perntdVO1.setPerntd_Cont("這是一個新通知，這是一個新通知，這是一個新通知，這是一個新通知，這是一個新通知");
		dao.insert(perntdVO1);
		//update
		PerntdVO perntdVO2 = new PerntdVO();
		perntdVO2.setPerntd_No("P06");
		perntdVO2.setMem_No("M000003");
		perntdVO2.setNt_No("NT01");
		perntdVO2.setPerntd_Cont("通知更新了，通知更新了，通知更新了，通知更新了，通知更新了，通知更新了，通知更新了");
		perntdVO2.setPerntd_Date("2018/01/01");
		dao.update(perntdVO2);
		//delete
		dao.delete("P07");
		//select one
		PerntdVO perntdVO3 = new PerntdVO();
		perntdVO3 = dao.findByPrimaryKey("P01");
		System.out.print(perntdVO3.getPerntd_No()+" ");
		System.out.print(perntdVO3.getMem_No()+" ");
		System.out.print(perntdVO3.getNt_No()+" ");
		System.out.print(perntdVO3.getPerntd_Cont()+" ");
		System.out.println(perntdVO3.getPerntd_Date()+" ");
		//select all
		List<PerntdVO> list = new ArrayList<>();
		list = dao.getAll();
		for(PerntdVO perntdVO : list) {
			System.out.print(perntdVO.getPerntd_No()+" ");
			System.out.print(perntdVO.getMem_No()+" ");
			System.out.print(perntdVO.getNt_No()+" ");
			System.out.print(perntdVO.getPerntd_Cont()+" ");
			System.out.println(perntdVO.getPerntd_Date()+" ");
		}
	}

}