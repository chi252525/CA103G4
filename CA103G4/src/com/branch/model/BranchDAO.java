package com.branch.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO implements BranchDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA103";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Branch (Branch_NO, Branch_NAME, Branch_CITY, Branch_DIST, Branch_ADDR, Branch_POS, Branch_LAN, Branch_LAT, Branch_TIME, Branch_DEL, Branch_TEL, Branch_TDESK) "
			+ "VALUES(LPAD(Branch_NO_seq.NEXTVAL,4,'0'), ?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_STMT = "UPDATE Branch SET "
			+ "BRAN_NAME=?,Branch_CITY=?, Branch_DIST=?, Branch_ADDR=?, Branch_POS=?, Branch_LAN=?, Branch_LAT=?, Branch_TIME=?, Branch_DEL=?, Branch_TEL=?, Branch_TDESK=? WHERE Branch_NO =?";

	private static final String DELETE_STMT = "DELETE from Branch WHERE Branch_NO =?";

	private static final String GET_ONE_STMT = "SELECT FROM Branch WHERE Branch_NO =?";

	private static final String GET_ALL_STMT = "SELECT * FROM Branch ORDER BY Branch_NO";

	@Override
	public void insert(BranchVO BranchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			try {
				pstmt = con.prepareStatement(INSERT_STMT);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			pstmt.setString(1, BranchVO.getBranch_No());
			pstmt.setString(2, BranchVO.getBranch_Name());
			pstmt.setString(3, BranchVO.getBranch_City());
			pstmt.setString(4, BranchVO.getBranch_Dist());
			pstmt.setString(5, BranchVO.getBranch_Addr());
			pstmt.setString(6, BranchVO.getBranch_Pos());
			pstmt.setString(7, BranchVO.getBranch_Lan());
			pstmt.setString(8, BranchVO.getBranch_Lat());
			pstmt.setString(9, BranchVO.getBranch_Time());
			pstmt.setDouble(10, BranchVO.getBranch_Del());
			pstmt.setString(11, BranchVO.getBranch_Tel());
			pstmt.setInt(12, BranchVO.getBranch_Tdesk());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(BranchVO BranchVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, BranchVO.getBranch_No());
			pstmt.setString(2, BranchVO.getBranch_Name());
			pstmt.setString(3, BranchVO.getBranch_City());
			pstmt.setString(4, BranchVO.getBranch_Dist());
			pstmt.setString(5, BranchVO.getBranch_Addr());
			pstmt.setString(6, BranchVO.getBranch_Pos());
			pstmt.setString(7, BranchVO.getBranch_Lan());
			pstmt.setString(8, BranchVO.getBranch_Lat());
			pstmt.setString(9, BranchVO.getBranch_Time());
			pstmt.setDouble(10, BranchVO.getBranch_Del());
			pstmt.setString(11, BranchVO.getBranch_Tel());
			pstmt.setInt(12, BranchVO.getBranch_Tdesk());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String Branch_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Branch_No);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public BranchVO findByPrimaryKey(String Branch_No) {
		BranchVO BranchVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Branch_No);
			rs = pstmt.executeQuery();
			rs.next();
			BranchVO = new BranchVO();
			BranchVO.setBranch_No(rs.getString("Branch_NO"));
			BranchVO.setBranch_Name(rs.getString("Branch_NAME"));
			BranchVO.setBranch_City(rs.getString("Branch_City"));
			BranchVO.setBranch_Dist(rs.getString("Branch_Dist"));
			BranchVO.setBranch_Addr(rs.getString("Branch_Addr"));
			BranchVO.setBranch_Pos(rs.getString("Branch_Pos"));
			BranchVO.setBranch_Lan(rs.getString("Branch_Lan"));
			BranchVO.setBranch_Lat(rs.getString("Branch_Lat"));
			BranchVO.setBranch_Time(rs.getString("Branch_Time"));
			BranchVO.setBranch_Del(rs.getDouble("Branch_Del"));
			BranchVO.setBranch_Tel(rs.getString("Branch_Tel"));
			BranchVO.setBranch_Tdesk(rs.getInt("Branch_Tdesk"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return BranchVO;

	}

	@Override
	public List<BranchVO> getAll() {
		List<BranchVO> list = new ArrayList<>();
		BranchVO BranchVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BranchVO = new BranchVO();
				BranchVO.setBranch_No(rs.getString("Branch_NO"));
				BranchVO.setBranch_Name(rs.getString("Branch_NAME"));
				BranchVO.setBranch_City(rs.getString("Branch_City"));
				BranchVO.setBranch_Dist(rs.getString("Branch_Dist"));
				BranchVO.setBranch_Addr(rs.getString("Branch_Addr"));
				BranchVO.setBranch_Pos(rs.getString("Branch_Pos"));
				BranchVO.setBranch_Lan(rs.getString("Branch_Lan"));
				BranchVO.setBranch_Lat(rs.getString("Branch_Lat"));
				BranchVO.setBranch_Time(rs.getString("Branch_Time"));
				BranchVO.setBranch_Del(rs.getDouble("Branch_Del"));
				BranchVO.setBranch_Tel(rs.getString("Branch_Tel"));
				BranchVO.setBranch_Tdesk(rs.getInt("Branch_Tdesk"));
				list.add(BranchVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		BranchDAO dao = new BranchDAO();
		// insert
		BranchVO BranchVO1 = new BranchVO();
		BranchVO1.setBranch_No("0012");
		BranchVO1.setBranch_Name("竹風堂信義2店");
		BranchVO1.setBranch_City("台北");
		BranchVO1.setBranch_Dist("東區");
		BranchVO1.setBranch_Addr("信義店99號");
		BranchVO1.setBranch_Pos("120E 35N");
		BranchVO1.setBranch_Lan("120E");
		BranchVO1.setBranch_Lat("35N");
		BranchVO1.setBranch_Time("竹風堂信義店");
		BranchVO1.setBranch_Del(10.0);
		BranchVO1.setBranch_Tel("竹風堂信義店");
		BranchVO1.setBranch_Tdesk(99);
		dao.insert(BranchVO1);
		// update
		BranchVO BranchVO2 = new BranchVO();
		BranchVO2.setBranch_No("0013");
		BranchVO2.setBranch_Name("竹風堂信義3店");
		dao.update(BranchVO2);
		// delete
		dao.delete("0013");
		// select one
		BranchVO BranchVO3 = new BranchVO();
		BranchVO3 = dao.findByPrimaryKey("0012");
		System.out.println(BranchVO3.getBranch_No());
		System.out.println(BranchVO3.getBranch_Name());
		System.out.println(BranchVO3.getBranch_City());
		System.out.println(BranchVO3.getBranch_Dist());
		System.out.println(BranchVO3.getBranch_Pos());
		System.out.println(BranchVO3.getBranch_Lan());
		System.out.println(BranchVO3.getBranch_Lat());
		System.out.println(BranchVO3.getBranch_Time());
		System.out.println(BranchVO3.getBranch_Del());
		System.out.println(BranchVO3.getBranch_Tel());
		System.out.println(BranchVO3.getBranch_Tdesk());
		System.out.println(BranchVO3.getBranch_Addr());
		// select all
		List<BranchVO> list = dao.getAll();
		for (BranchVO aBranch : list) {
			System.out.print("Branch_No: "+aBranch.getBranch_No());
			System.out.println("Branch_Name: "+aBranch.getBranch_Name());
			System.out.println("Branch_City: "+aBranch.getBranch_City());
			System.out.println("Branch_Dist: "+aBranch.getBranch_Dist());
			System.out.println("Branch_Pos: "+aBranch.getBranch_Pos());
			System.out.println("Branch_Lan: "+aBranch.getBranch_Lan());
			System.out.println("Branch_Lat: "+aBranch.getBranch_Lat());
			System.out.println("Branch_Time:"+aBranch.getBranch_Time());
			System.out.println("Branch_Del: "+aBranch.getBranch_Del());
			System.out.println("Branch_Tel: "+aBranch.getBranch_Tel());
			System.out.println("Branch_Tdesk: "+aBranch.getBranch_Tdesk());
			System.out.println("Branch_Addr: "+aBranch.getBranch_Addr());
		}
	}
}
