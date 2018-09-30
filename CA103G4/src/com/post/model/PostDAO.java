package com.post.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.reply_msg.model.ReplyVO;

public class PostDAO implements PostDAO_interface{
	

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 新增一個貼文預設顯示
	private static final String INSERT_STMT=
			"INSERT INTO POST(POST_NO,MEM_NO,CUSTOM_NO,POST_CONT," + 
			"POST_EVA,POST_PHOTO,POST_TIME,POST_STATUS)" + 
			"VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(POST_seq.NEXTVAL), 6, '0')," + 
			"?,?,?,?,?,?,'PS1')";	
	// 修改貼文
	private static final String UPDATE_STMT = 
			"UPDATE POST SET MEM_NO=?,CUSTOM_NO=?,POST_CONT=?,POST_EVA=?,POST_PHOTO=?,POST_TIME=? WHERE POST_NO=?";
	
	// 修改貼文狀態
		private static final String UPDATE_POST_STATUS_STMT = 
				"UPDATE POST SET POST_STATUS='PS0' WHERE POST_NO=?";
	
	// 刪除貼文
	private static final String DELETE_STMT = "DELETE FROM POST WHERE POST_NO = ?";
	
	//刪除貼文的所有留言
	private static final String DELETE_MSG_FROMONE_STMT = "DELETE FROM RPLY_MSG WHERE POST_NO = ?";
	
	// 一個會員的貼文
	private static final String FINDBYMEMNO="SELECT * FROM POST WHERE MEM_NO=?";
	
	// 一個餐點的貼文(not good)
	private static final String FINDBYCUSTOMNO="SELECT * FROM POST WHERE CUSTOM_NO=?";
	
	// 所有貼文
	private static final String GETALL = "SELECT * FROM POST WHERE POST_STATUS='PS1' order by POST_TIME DESC";
	
	// 取得單一貼文
	private static final String GET_ONE_POST = "SELECT * FROM POST WHERE POST_NO=?";
	
	// 依據年月查貼文
	private static final String FINDBY_YEAR_AND_MON="SELECT * FROM post WHERE EXTRACT(YEAR FROM post_time )=?" + 
			"and EXTRACT(MONTH FROM post_time )=?";
	
	// 貼文瀏覽數++
	private static final String UPDATE_POST_VIEWS_STMT = "UPDATE POST SET POST_VIEWS = POST_VIEWS + 1 WHERE POST_NO = ?";
	
	//取得一個貼文的所有留言
	private static final String GET_ONE_POST_ALLRPLYS =
	"SELECT RPLY_NO,MEM_NO,POST_NO,RPLY_CONT,RPLY_TIME FROM rply_msg where POST_NO =?  order by RPLY_NO desc";
	

	// 取得所有貼文，根據瀏覽次數由多到少排列
	private static final String GET_ALL_BY_HOT_STMT = 
					"SELECT * FROM POST WHERE POST_STATUS='PS1' ORDER BY POST_VIEWS DESC";
	
	// 取得4個發文日期最新的貼文
	private static final String GET_ALL_BY_NEW_FOUR_STMT = "SELECT * FROM (SELECT * FROM POST ORDER BY POST_TIME DESC )	WHERE POST_STATUS='PS1' AND ROWNUM <=4";
	
	// 根據內容搜尋，根據發文時間由新到舊排列	
	private static final String GET_ALL_BY_KEYWORD_ORDER_BY_VIEWS =
	"SELECT * FROM POST WHERE REGEXP_LIKE (POST_CONT, ?) AND POST_STATUS='PS1' ORDER BY POST_TIME DESC";
	
	
	@Override
	public void insert(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
//			System.out.println("insert Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, postVO.getMem_No());
			pstmt.setString(2, postVO.getCustom_No());
			pstmt.setString(3, postVO.getPost_Cont());
			pstmt.setInt(4, postVO.getPost_Eva());
			pstmt.setBytes(5, postVO.getPost_Photo());
			pstmt.setTimestamp(6, postVO.getPost_Time());
			int rowCount =pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");
			// TODO Auto-generated catch block
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
			con = ds.getConnection();
//			System.out.println("update Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, postVO.getMem_No());
			pstmt.setString(2, postVO.getCustom_No());
			pstmt.setString(3, postVO.getPost_Cont());
			pstmt.setInt(4, postVO.getPost_Eva());
			pstmt.setBytes(5, postVO.getPost_Photo());
			pstmt.setTimestamp(6, postVO.getPost_Time());
			pstmt.setString(7, postVO.getPost_No());
			int rowCount =pstmt.executeUpdate();
			System.out.println("修改" + rowCount + " 筆資料");
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
		int updateCount_MSGS = 0;
		try {
			System.out.println("進到post delete");
			
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_MSG_FROMONE_STMT);
			pstmt.setString(1,post_No);
			updateCount_MSGS = pstmt.executeUpdate();
			// 再刪除貼文
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, post_No);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除貼文編號" + post_No + "時,共有留言" + updateCount_MSGS
					+ "則同時被刪除");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<PostVO> findbyMem_No(String mem_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> postlist = new ArrayList<>();
		try {
			con = ds.getConnection();
//			System.out.println("findbyMem_No Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYMEMNO);
			pstmt.setString(1, mem_No);
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
				postVO.setPost_Views(rs.getInt("post_Views"));
				postlist.add(postVO); 
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
		return postlist;
	}

	@Override
	public List<PostVO> findbyCustom_No(String custom_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> postlist = new ArrayList<>();

		try {
			con = ds.getConnection();
//			System.out.println("findbyCustom_No Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBYCUSTOMNO);
			pstmt.setString(1, custom_No);
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
				postVO.setPost_Views(rs.getInt("post_Views"));
				postlist.add(postVO); 
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
		return postlist;
	}

	@Override
	public List<PostVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> postlist = new ArrayList<>();

		try {
			con = ds.getConnection();
//			System.out.println("getAll Connecting to database successfully! (連線成功！)");
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
				postVO.setPost_Views(rs.getInt("post_Views"));
				postlist.add(postVO); 
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
		return postlist;
	}

	@Override
	public PostVO findByPrimaryKey(String post_No) {
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			System.out.println("findByPrimaryKey Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_POST);
			pstmt.setString(1, post_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPost_No(rs.getString("post_No"));
				postVO.setMem_No(rs.getString("mem_No"));
				postVO.setCustom_No(rs.getString("custom_No"));
				postVO.setPost_Cont(rs.getString("post_Cont"));
				postVO.setPost_Eva(rs.getInt("post_Eva"));
				postVO.setPost_Photo(rs.getBytes("post_Photo"));
				postVO.setPost_Time(rs.getTimestamp("post_Time"));
				postVO.setPost_Views(rs.getInt("post_Views"));
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
	public List<PostVO> findbyYearandMonth(String year, String month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> postlist = new ArrayList<>();
		try {
			con = ds.getConnection();
//			System.out.println("findbyYearandMonth Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(FINDBY_YEAR_AND_MON);
			pstmt.setString(1, year);
			pstmt.setString(2, month);
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
				postVO.setPost_Views(rs.getInt("post_Views"));
				postlist.add(postVO); 
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
		return postlist;
	}

	@Override
	public int updateViews(String post_No) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POST_VIEWS_STMT);
			pstmt.setString(1, post_No);

			updateCount = pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}

	@Override
	public Set<ReplyVO> getOnePost_AllRplys(String post_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<ReplyVO> set = new LinkedHashSet<ReplyVO>();
		ReplyVO rplyVO = null;

		try {
			con = ds.getConnection();
//			System.out.println("getOnePost_AllRplys Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_POST_ALLRPLYS);
			pstmt.setString(1, post_No);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rplyVO=new ReplyVO();
				rplyVO.setRply_No(rs.getString("rply_No"));
				rplyVO.setMem_No(rs.getString("mem_No"));
				rplyVO.setPost_No(rs.getString("post_No"));
				rplyVO.setRply_Cont(rs.getString("rply_Cont"));
				rplyVO.setRply_Time(rs.getTimestamp("rply_Time"));
				set.add(rplyVO); 
			}
			// Handle any SQL errors
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
					return set;
	}

	@Override
	public List<PostVO> getAllByHot() {
		List<PostVO> postlist = new ArrayList<PostVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_HOT_STMT);
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
				postVO.setPost_Views(rs.getInt("post_Views"));
				postlist.add(postVO); 
			}
		}  catch (SQLException se) {
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
		return postlist;
	}

	@Override
	public List<PostVO> getAllByNewFour() {
		List<PostVO> postlist = new ArrayList<PostVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_NEW_FOUR_STMT);
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
				postlist.add(postVO); 
			}
		}  catch (SQLException se) {
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
		return postlist;
	}

	@Override
	public List<PostVO> getAllByKeywordOrderByViews(String keyword) {
		List<PostVO> postlist = new ArrayList<PostVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_KEYWORD_ORDER_BY_VIEWS);
			pstmt.setString(1,  keyword);
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
				postlist.add(postVO); 
			}
		}  catch (SQLException se) {
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
		return postlist;
	}

	@Override
	public int updatePostStatus(String post_No) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POST_STATUS_STMT);
			pstmt.setString(1, post_No);

			updateCount = pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}
	
}
