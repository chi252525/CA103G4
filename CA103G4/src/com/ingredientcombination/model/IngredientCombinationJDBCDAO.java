package com.ingredientcombination.model;

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


public class IngredientCombinationJDBCDAO implements IngredientCombinationDAO_interface{


	
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String USER = "CA103";
		private static final String PASSWORD = "123456";
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String INSERT_STMT=		
				"INSERT INTO INGREDIENTCOMBINATION" 
		        		+"(CUSTOM_NO, INGDT_ID) VALUES(?,?)";
		
		private static final String UPDATE_STMT =
				"UPDATE INGREDIENTCOMBINATION SET INGDT_ID=? WHERE CUSTOM_NO=?";
		private static final String DELETE_STMT =
				"DELETE FROM INGREDIENTCOMBINATION WHERE CUSTOM_NO=?";
		private static final String SELECT_ONE_STMT=
				"SELECT CUSTOM_NO, INGDT_ID FROM INGREDIENTCOMBINATION WHERE CUSTOM_NO=?";
		private static final String SELECT_ALL_STMT=
				"SELECT CUSTOM_NO, INGDT_ID FROM INGREDIENTCOMBINATION ORDER BY CUSTOM_NO";
		@Override
		public void insert(IngredientCombinationVO ingredientCombinationVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, ingredientCombinationVO.getCustom_No());
				pstmt.setString(2, ingredientCombinationVO.getIngdt_Id());
				
				int rowCount =pstmt.executeUpdate();
				System.out.println("新增 " + rowCount + " 筆資料");
				
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
		public void update(IngredientCombinationVO ingredientCombinationVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(UPDATE_STMT);
				pstmt.setString(1, ingredientCombinationVO.getIngdt_Id());
				pstmt.setString(2, ingredientCombinationVO.getCustom_No());
				
				
				int rowCount =pstmt.executeUpdate();
				System.out.println("修改" + rowCount + " 筆資料");
				
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
//				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setString(1, Custom_No);
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
		public IngredientCombinationVO findByPrimaryKey(String Custom_No) {
			IngredientCombinationVO ingredientCombinationVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(SELECT_ONE_STMT);
				pstmt.setString(1, Custom_No);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ingredientCombinationVO = new IngredientCombinationVO();
					ingredientCombinationVO.setCustom_No(rs.getString("Custom_No"));
					ingredientCombinationVO.setIngdt_Id(rs.getString("Ingdt_Id"));
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
			return ingredientCombinationVO;
		}
		@Override
		public List<IngredientCombinationVO> getAll() {
			List<IngredientCombinationVO> ingredientCombinationVOList = new ArrayList<>();
			IngredientCombinationVO ingredientCombinationVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(SELECT_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ingredientCombinationVO = new IngredientCombinationVO();
					ingredientCombinationVO.setCustom_No(rs.getString("CUSTOM_NO"));
					ingredientCombinationVO.setIngdt_Id(rs.getString("INGDT_ID"));
					ingredientCombinationVOList.add(ingredientCombinationVO);
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
			return ingredientCombinationVOList;
		}
		public void insert2(IngredientCombinationVO ingredientCombinationVO, Connection con) {
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connecting to database successfully! (連線成功！)");
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, ingredientCombinationVO.getCustom_No());
				pstmt.setString(2, ingredientCombinationVO.getIngdt_Id());
				
				int rowCount =pstmt.executeUpdate();
				System.out.println("新增 " + rowCount + " 筆食材搭配");
				
			} catch (SQLException se) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ingredientCombination");
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

		
	
	
	
}
