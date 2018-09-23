package com.branch.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BranchDAO implements BranchDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA103";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Branch (Branch_NO, Branch_NAME, Branch_CITY, Branch_DIST, Branch_ADDR, Branch_POS, Branch_LAN, Branch_LAT, Branch_TIME, Branch_DEL, Branch_TEL, Branch_TDESK) "
			+ "VALUES(LPAD(Branch_NO_seq.NEXTVAL,4,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE Branch SET "
			+ "BRANCH_NAME=?, Branch_CITY=?, Branch_DIST=?, Branch_ADDR=?, Branch_POS=?, Branch_LAN=?, Branch_LAT=?, Branch_TIME=?, Branch_DEL=?, Branch_TEL=?, Branch_TDESK=? WHERE Branch_NO =?";

	private static final String DELETE_STMT = "DELETE from Branch WHERE Branch_NO =?";

	private static final String GET_ONE_STMT = "SELECT * FROM Branch WHERE Branch_NO =?";

	private static final String GET_ALL_STMT = "SELECT * FROM Branch ORDER BY Branch_NO";

	private static final String GET__BY_CITY_STMT = "SELECT * FROM Branch WHERE Branch_CITY =?";

	@Override
	public int insert(BranchVO BranchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			try {
				pstmt = con.prepareStatement(INSERT_STMT);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			pstmt.setString(1, BranchVO.getBranch_Name());
			pstmt.setString(2, BranchVO.getBranch_City());
			pstmt.setString(3, BranchVO.getBranch_Dist());
			pstmt.setString(4, BranchVO.getBranch_Addr());
			pstmt.setString(5, BranchVO.getBranch_Pos());
			pstmt.setString(6, BranchVO.getBranch_Lan());
			pstmt.setString(7, BranchVO.getBranch_Lat());
			pstmt.setString(8, BranchVO.getBranch_Time());
			pstmt.setDouble(9, BranchVO.getBranch_Del());
			pstmt.setString(10, BranchVO.getBranch_Tel());
			pstmt.setInt(11, BranchVO.getBranch_Tdesk());

			row = pstmt.executeUpdate();
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
		return row;

	}

	@Override
	public int update(BranchVO BranchVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, BranchVO.getBranch_Name());
			pstmt.setString(2, BranchVO.getBranch_City());
			pstmt.setString(3, BranchVO.getBranch_Dist());
			pstmt.setString(4, BranchVO.getBranch_Addr());
			pstmt.setString(5, BranchVO.getBranch_Pos());
			pstmt.setString(6, BranchVO.getBranch_Lan());
			pstmt.setString(7, BranchVO.getBranch_Lat());
			pstmt.setString(8, BranchVO.getBranch_Time());
			pstmt.setDouble(9, BranchVO.getBranch_Del());
			pstmt.setString(10, BranchVO.getBranch_Tel());
			pstmt.setInt(11, BranchVO.getBranch_Tdesk());
			pstmt.setString(12, BranchVO.getBranch_No());
			row = pstmt.executeUpdate();
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
		return row;
	}

	@Override
	public int delete(String Branch_No) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Branch_No);
			row = pstmt.executeUpdate();
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
		return row;
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

	@Override
	public List<BranchVO> findBy_City(String branch_City) {

		List<BranchVO> list = new ArrayList<>();
		BranchVO BranchVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET__BY_CITY_STMT);
			pstmt.setString(1, branch_City);
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

	@Override
	//city 不重複集合
	public Set<String> getCity() {
		Set<String> set = new LinkedHashSet<String>();
		for (BranchVO brVO : getAll()) {
			set.add(brVO.getBranch_City());
		}
		return set;
	}

}
