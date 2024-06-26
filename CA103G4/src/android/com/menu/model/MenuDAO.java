package android.com.menu.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuDAO implements MenuDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	private static final String INSERT_STMT=		
			"INSERT INTO MENU (MENU_NO,MENU_ID,MENU_TYPE,MENU_PRICE,MENU_INTRO,MENU_STATUS)"
					+"VALUES(('M'||LPAD(to_char(MENU_seq.NEXTVAL),3,'0')),?,?,?,?,?)";	
	private static final String UPDATE_STMT =
			"UPDATE MENU SET MENU_ID=?, MENU_TYPE=?, MENU_PRICE=?, MENU_INTRO=?, MENU_STATUS=? WHERE MENU_NO=?";
	private static final String DELETE_STMT =
			"DELETE FROM MENU WHERE MENU_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT MENU_NO, MENU_ID, MENU_TYPE, MENU_PRICE, MENU_INTRO, MENU_STATUS FROM MENU WHERE MENU_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT MENU_NO, MENU_ID, MENU_TYPE, MENU_PRICE, MENU_INTRO, MENU_STATUS FROM MENU ORDER BY MENU_NO";
	private static final String FIND_IMG = "SELECT MENU_PHOTO FROM MENU WHERE MENU_NO = ?";
	
	
	
	@Override
	public byte[] getImage(String menu_No) {
		
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMG);
			pstmt.setString(1, menu_No);
			
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
	public void insert(MenuVO menuVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuVO.getMenu_Id());
			pstmt.setString(2, menuVO.getMenu_Type());
			pstmt.setInt(3, menuVO.getMenu_Price());
			pstmt.setString(4, menuVO.getMenu_Intro());
			pstmt.setInt(5, menuVO.getMenu_Status());
			int rowCount =pstmt.executeUpdate();
			
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
	public void update(MenuVO menuVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, menuVO.getMenu_Id());
			pstmt.setString(2, menuVO.getMenu_Type());
			pstmt.setInt(3, menuVO.getMenu_Price());
			pstmt.setString(4, menuVO.getMenu_Intro());
			pstmt.setInt(5, menuVO.getMenu_Status());
			pstmt.setString(6, menuVO.getMenu_No());
			
			int rowCount =pstmt.executeUpdate();
			
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
	public void delete(String Menu_No) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setString(1, Menu_No);
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MenuVO findByPrimaryKey(String Menu_No) {
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, Menu_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_No(rs.getString("MENU_NO"));
				menuVO.setMenu_Id(rs.getString("MENU_ID"));
				menuVO.setMenu_Type(rs.getString("MENU_TYPE"));
				menuVO.setMenu_Price(rs.getInt("MENU_PRICE"));
				menuVO.setMenu_Intro(rs.getString("MENU_INTRO"));
				menuVO.setMenu_Status(rs.getInt("MENU_STATUS"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return menuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> menuVOList = new ArrayList<>();
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_No(rs.getString("MENU_NO"));
				menuVO.setMenu_Id(rs.getString("MENU_ID"));
				menuVO.setMenu_Type(rs.getString("MENU_TYPE"));
				menuVO.setMenu_Price(rs.getInt("MENU_PRICE"));
				menuVO.setMenu_Intro(rs.getString("MENU_INTRO"));
				menuVO.setMenu_Status(rs.getInt("MENU_STATUS"));	
				
				menuVOList.add(menuVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " 
					+ se.getMessage());
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
		return menuVOList;
	}
	
	private byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
		//  toByteArray() 獲取數據。
		}
}
