package com.post.model;

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


public class PostDAO implements PostDAO_interface{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "raman";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT=
			"INSERT INTO POST(POST_NO,MEM_NO,CUSTOM_NO,POST_CONT," + 
			"POST_EVA,POST_PHOTO,POST_TIME)" + 
			"VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(POST_seq.NEXTVAL), 6, '0')," + 
			"?,?,?,?,?,?)";	
	private static final String UPDATE_STMT = 
			"UPDATE POST SET MEM_NO=?,CUSTOM_NO=?,POST_CONT=?,POST_EVA=?,POST_PHOTO=?,POST_TIME=? WHERE POST_NO=?";
	private static final String DELETE_STMT = "DELETE FROM POST WHERE POST_NO = ?";
	private static final String FINDBYMEMNO="SELECT * FROM POST WHERE MEM_NO=?";
	private static final String FINDBYCUSTOMNO="SELECT * FROM POST WHERE CUSTOM_NO=?";
	private static final String GETALL = "SELECT * FROM POST";
	
	@Override
	public void insert(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, postVO.getMem_No());
			pstmt.setString(2, postVO.getCustom_No());
			pstmt.setString(3, postVO.getPost_Cont());
			pstmt.setInt(4, postVO.getPost_Eva());
			byte[] pic2 = getPictureByteArray("items/Bing3.jpeg");
			pstmt.setBytes(5, pic2);
			pstmt.setTimestamp(6, postVO.getPost_Time());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException se) {
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
	public void update(PostVO postVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, postVO.getMem_No());
			pstmt.setString(2, postVO.getCustom_No());
			pstmt.setString(3, postVO.getPost_Cont());
			pstmt.setInt(4, postVO.getPost_Eva());
			byte[] pic = getPictureByteArray("items/Bing3.jpeg");
			pstmt.setBytes(5, pic);
			pstmt.setTimestamp(6, postVO.getPost_Time());
			pstmt.setString(7, postVO.getPost_No());
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");

			// Handle any SQL errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException se) {
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
	public void delete(String post_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, post_No);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
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
	public PostVO findbyMem_No(String mem_No) {
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYMEMNO);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				postVO=new PostVO();
				postVO.setPost_No(rs.getString("post_No"));
				postVO.setCustom_No(rs.getString("custom_No"));
				postVO.setPost_Cont(rs.getString("post_Cont"));
				postVO.setPost_Eva(rs.getInt("post_Eva"));
				//getByte
				postVO.setPost_Time(rs.getTimestamp("post_Time"));
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
		return postVO;
	}

	@Override
	public PostVO findbyCustom_No(String custom_No) {
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYCUSTOMNO);
			pstmt.setString(1, custom_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				postVO=new PostVO();
				postVO.setPost_No(rs.getString("post_No"));
				postVO.setMem_No(rs.getString("mem_No"));
				postVO.setPost_Cont(rs.getString("post_Cont"));
				postVO.setPost_Eva(rs.getInt("post_Eva"));
				//getByte
				postVO.setPost_Time(rs.getTimestamp("post_Time"));
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
		return postVO;
	}

	@Override
	public List<PostVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> activitylist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostVO postVO=new PostVO();
				postVO.setPost_No(rs.getString("post_No"));
				postVO.setMem_No(rs.getString("mem_No"));
				postVO.setCustom_No(rs.getString("custom_No"));
				postVO.setPost_Cont(rs.getString("post_Cont"));
				postVO.setPost_Eva(rs.getInt("post_Eva"));
				postVO.setPost_Photo(rs.getBytes("post_Photo"));
				postVO.setPost_Time(rs.getTimestamp("post_Time"));
				activitylist.add(postVO); 
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
		return activitylist;
	}
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			//write(byte[] b, int off, int len) 
	        //?指定 byte ??中?偏移量 off ?始的 len ?字??入此 byte ???出流。
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
		//  toByteArray() 獲取數據。
	}


}
