package com.custommeals.model;

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


import com.ingredientcombination.model.IngredientCombinationDAO;
import com.ingredientcombination.model.IngredientCombinationVO;




public class CustommealsDAO implements CustommealsDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "CA103";
//	private static final String PASSWORD = "123456";
//	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = 
			"INSERT INTO CUSTOMMEALS" 
	                +"(CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE)"
	                +"VALUES(('C'||LPAD(to_char(CUSTOMMEALS_seq.NEXTVAL),10,'0')),?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE CUSTOMMEALS SET MEM_NO=?, CUSTOM_NAME=?, CUSTOM_PRICE=? WHERE CUSTOM_NO=?";
	
	private static final String UPDATE_NAME_STMT = 
			"UPDATE CUSTOMMEALS SET  CUSTOM_NAME=? WHERE CUSTOM_NO=?";
	
	
	private static final String DELETE_STMT =
			"DELETE FROM CUSTOMMEALS WHERE CUSTOM_NO=?";
	private static final String SELECT_ONE_STMT=
			"SELECT CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE FROM CUSTOMMEALS WHERE CUSTOM_NO=?";
	private static final String SELECT_ALL_STMT=
			"SELECT CUSTOM_NO, MEM_NO, CUSTOM_NAME, CUSTOM_PRICE FROM CUSTOMMEALS ORDER BY CUSTOM_NO";

	
	//依會員查他訂過的自訂餐點
	private static final String SELECT_MEAL_BY_MEMBUYED="SELECT * FROM CUSTOMMEALS LEFT JOIN ORDERINVOICE\r\n" + 
			"ON CUSTOMMEALS.CUSTOM_NO=orderinvoice.custom_no " + 
			"WHERE orderinvoice.custom_no IS NOT NULL AND " + 
			"mem_no=?";
	

	
	//依會員查他的所有自訂餐點
	private static final String SELECT_ALLMEAL_BY_MEMNO="SELECT *  FROM CUSTOMMEALS  LEFT JOIN INGREDIENTCOMBINATION" + 
	"ON CUSTOMMEALS.CUSTOM_NO=INGREDIENTCOMBINATION.CUSTOM_NO WHERE CUSTOMMEALS.mem_no=?";
	
	@Override
	public void insert(CustommealsVO custommealsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, custommealsVO.getmem_No());
			pstmt.setString(2, custommealsVO.getcustom_Name());
			pstmt.setInt(3, custommealsVO.getcustom_Price());
//			pstmt.setBytes(4, custommealsVO.getcustom_Photo());
//			System.out.println("custommealsVO.getcustom_Photo()=" + custommealsVO.getcustom_Photo());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void update(CustommealsVO custommealsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, custommealsVO.getmem_No());
			pstmt.setString(2, custommealsVO.getcustom_Name());
			pstmt.setInt(3, custommealsVO.getcustom_Price());
//			pstmt.setBytes(4, custommealsVO.getcustom_Photo());
			pstmt.setString(4, custommealsVO.getcustom_No());
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改 " + rowCount + " 筆資料");
			
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
	public void delete(String Custom_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, Custom_No);
			pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
	public CustommealsVO findByPrimaryKey(String Custom_No) {
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(SELECT_ONE_STMT);
			pstmt.setString(1, Custom_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setcustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setmem_No(rs.getString("MEM_NO"));
				custommealsVO.setcustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setcustom_Price(rs.getInt("CUSTOM_PRICE"));
//				custommealsVO.setcustom_Photo(rs.getBytes("CUSTOM_PHOTO"));

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
		return custommealsVO;
	}
	@Override
	public List<CustommealsVO> getAll() {
		List<CustommealsVO> custommealsVOList = new ArrayList<>();
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setcustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setmem_No(rs.getString("MEM_NO"));
				custommealsVO.setcustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setcustom_Price(rs.getInt("CUSTOM_PRICE"));
//				custommealsVO.setcustom_Photo(rs.getBytes("CUSTOM_PHOTO"));
				custommealsVOList.add(custommealsVO);
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
		return custommealsVOList;
	}
	@Override
	public List<CustommealsVO> getMealByMemBuyed(String mem_No) {
		List<CustommealsVO> custommealsVOList = new ArrayList<>();
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_MEAL_BY_MEMBUYED);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setcustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setmem_No(rs.getString("MEM_NO"));
				custommealsVO.setcustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setcustom_Price(rs.getInt("CUSTOM_PRICE"));
//				custommealsVO.setcustom_Photo(rs.getBytes("CUSTOM_PHOTO"));
				custommealsVOList.add(custommealsVO);
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
		return custommealsVOList;
	}
	@Override
	public List<CustommealsVO> getMealByMem(String mem_No) {
		List<CustommealsVO> custommealsVOList = new ArrayList<>();
		CustommealsVO custommealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALLMEAL_BY_MEMNO);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custommealsVO = new CustommealsVO();
				custommealsVO.setcustom_No(rs.getString("CUSTOM_NO"));
				custommealsVO.setmem_No(rs.getString("MEM_NO"));
				custommealsVO.setcustom_Name(rs.getString("CUSTOM_NAME"));
				custommealsVO.setcustom_Price(rs.getInt("CUSTOM_PRICE"));
//				custommealsVO.setcustom_Photo(rs.getBytes("CUSTOM_PHOTO"));
				custommealsVOList.add(custommealsVO);
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
		return custommealsVOList;
	}
	
	
	@Override
	public String insertWithIngredientCombination(CustommealsVO custommealsVO, List<IngredientCombinationVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String next_custom_No = null;
		
		try {
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增自訂餐點編號
			String cols[] = {"CUSTOM_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, custommealsVO.getmem_No());
			pstmt.setString(2, custommealsVO.getcustom_Name());
			pstmt.setInt(3, custommealsVO.getcustom_Price());
//			pstmt.setBytes(4, custommealsVO.getcustom_Photo());

			
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_custom_No = rs.getString(1);
				System.out.println("自增主鍵值= " + next_custom_No +"(剛新增成功的自訂餐點編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增食材搭配
			IngredientCombinationDAO dao = new IngredientCombinationDAO();
			System.out.println("list.size()-A="+list.size());
			for (IngredientCombinationVO ingredientCombinationVO : list) {
				ingredientCombinationVO.setCustom_No(new String(next_custom_No)) ;
				dao.insert2( ingredientCombinationVO, con);
			}
			
			

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增自訂餐點編號" + next_custom_No + "時,共有食材搭配" + list.size()
					+ "種同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-custommeals");
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
		
		return next_custom_No;
	}
	
	@Override
	public void updateNameOnly( String custom_Name,String custom_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_NAME_STMT);
			
			pstmt.setString(1, custom_Name);
			pstmt.setString(2, custom_No);
			
			int rowCount =pstmt.executeUpdate();
			System.out.println("updateNameOnly 修改 " + rowCount + " 筆資料");
			
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
	
	

}
