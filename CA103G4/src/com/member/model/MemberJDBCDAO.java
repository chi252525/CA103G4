package com.member.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class MemberJDBCDAO implements MemberDAO_interface{
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CHIAPAO";
	private static final String PASSWORD = "CHIAPAO";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String INSERT_STMT=
			"INSERT INTO MEMBER(MEM_NO , MEM_ID , MEM_PW , MEM_NAME , MEM_GENDER , MEM_BIR , MEM_MAIL , MEM_PHONE , MEM_RECEIVER , MEM_REPNO, MEM_RECOUNTY , MEM_RETOWN ,MEM_READDR , MEM_CARDNUM , MEM_CARDDUE , MEM_PHOTO) " + 
			"VALUES('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_STMT=
			"UPDATE MEMBER SET MEM_PW=? , MEM_NAME=? , MEM_GENDER=? , MEM_BIR = to_date(?,'yyyy-mm-dd') , MEM_MAIL=? , MEM_PHONE=? , MEM_RECEIVER=? , MEM_REPNO=?, MEM_RECOUNTY =?, MEM_RETOWN=? ,MEM_READDR=? , MEM_CARDNUM=? , MEM_CARDDUE=? , MEM_BONUS=? , MEM_PHOTO=? WHERE MEM_ID =?";
	public static final String CHANGESTATUS_STMT=
			"UPDATE MEMBER SET MEM_STATUS=? WHERE MEM_ID = ?";
	public static final String GETALL=
			"SELECT * FROM MEMBER";
	public static final String GET_ONE_MEMBER="SELECT * FROM MEMBER WHERE MEM_NO=?";
	public static final String GET_ONE_BY_ID="SELECT * FROM MEMBER WHERE MEM_ID=?";
	public static final String COMPARE_ID = "SELECT MEM_ID FROM MEMBER WHERE MEM_ID LIKE ?";


	@Override
	public void insert(MemberVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memVO.getMem_Id());
			pstmt.setString(2, memVO.getMem_Pw());
			pstmt.setString(3, memVO.getMem_Name());
			pstmt.setString(4, memVO.getMem_Gender());
			pstmt.setDate(5, memVO.getMem_Bir());
			pstmt.setString(6, memVO.getMem_Mail());
			pstmt.setString(7, memVO.getMem_Phone());
			pstmt.setString(8, memVO.getMem_Receiver());
			pstmt.setString(9, memVO.getMem_Repno());
			pstmt.setString(10, memVO.getMem_Recounty());
			pstmt.setString(11, memVO.getMem_Retown());
			pstmt.setString(12, memVO.getMem_Readdr());
			pstmt.setString(13, memVO.getMem_Cardnum());
			pstmt.setString(14, memVO.getMem_Carddue());
			pstmt.setBytes(15,memVO.getMem_Photo());
			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 "+rowCount+" 會員資料");
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
	public void update(MemberVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			System.out.println("連線成功");
			pstmt.setString(1, memVO.getMem_Pw());
			pstmt.setString(2, memVO.getMem_Name());
			pstmt.setString(3, memVO.getMem_Gender());
			pstmt.setDate(4, memVO.getMem_Bir());
			pstmt.setString(5, memVO.getMem_Mail());
			pstmt.setString(6, memVO.getMem_Phone());
			pstmt.setString(7, memVO.getMem_Receiver());
			pstmt.setString(8, memVO.getMem_Repno());
			pstmt.setString(9, memVO.getMem_Recounty());
			pstmt.setString(10, memVO.getMem_Retown());
			pstmt.setString(11, memVO.getMem_Readdr());
			pstmt.setString(12, memVO.getMem_Cardnum());
			pstmt.setString(13, memVO.getMem_Carddue());
			pstmt.setInt(14, memVO.getMem_Bonus());
//			byte[] pic = getPictureByte("img/test2.jpg");
//			pstmt.setBytes(15, pic);
			pstmt.setBytes(15,memVO.getMem_Photo());
			pstmt.setString(16, memVO.getMem_Id());
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改 "+rowCount+" 筆資料");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
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
		
		
	}

	@Override
	public void changeStatus(MemberVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(CHANGESTATUS_STMT);
			System.out.println("連線成功");
			pstmt.setString(1, memVO.getMem_Status());
			pstmt.setString(2, memVO.getMem_Id());
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改狀態"+rowCount+"筆");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		
	}

	@Override
	public List<MemberVO> getAll() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemberVO> memberlist = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GETALL);
			System.out.println("連線成功");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO memVO = new MemberVO();
				memVO.setMem_No(rs.getString("mem_No"));
				memVO.setMem_Name(rs.getString("mem_Name"));
				memVO.setMem_Phone(rs.getString("mem_Phone"));
				memVO.setMem_Mail(rs.getString("mem_Mail"));
				memberlist.add(memVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
				
		return memberlist;
	}
	
	public byte[] getPictureByte(String path) throws IOException {
			
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[fis.available()];
			int i;
			while ((i = fis.read(buffer))!= -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();
		return baos.toByteArray();
		
	}

	@Override
	public MemberVO findByPrimaryKey(String mem_No) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			pstmt = con.prepareStatement(GET_ONE_MEMBER);
			pstmt.setString(1, mem_No);
			rs = pstmt.executeQuery();
  
			while (rs.next()) {
				memVO = new MemberVO();
				memVO.setMem_Id(rs.getString("mem_Id"));
				memVO.setMem_Pw(rs.getString("mem_Pw"));
				memVO.setMem_Name(rs.getString("mem_Name"));
				memVO.setMem_Gender(rs.getString("mem_Gender"));
				memVO.setMem_Bir(rs.getDate("mem_Bir"));
				memVO.setMem_Mail(rs.getString("mem_Mail"));
				memVO.setMem_Phone(rs.getString("mem_Phone"));
				memVO.setMem_Receiver(rs.getString("mem_Receiver"));
				memVO.setMem_Repno(rs.getString("mem_Repno"));
				memVO.setMem_Recounty(rs.getString("mem_Recounty"));
				memVO.setMem_Retown(rs.getString("mem_Retown"));
				memVO.setMem_Readdr(rs.getString("mem_Readdr"));
				memVO.setMem_Cardnum(rs.getString("mem_Cardnum"));
				memVO.setMem_Carddue(rs.getString("mem_Carddue"));
				memVO.setMem_Bonus(rs.getInt("mem_Bonus"));
				memVO.setMem_Credate(rs.getDate("mem_Credate"));
				memVO.setMem_Status(rs.getString("mem_Status"));
				memVO.setMem_Photo(rs.getBytes("mem_photo"));
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
		return memVO;
	}

	@Override
	public MemberVO findById(String mem_Id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memVO = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_ID);
			
			pstmt.setString(1, mem_Id);
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {

				memVO = new MemberVO();
				
				memVO.setMem_No(rs.getString("mem_No"));
				memVO.setMem_Id(rs.getString("mem_Id"));
				memVO.setMem_Pw(rs.getString("mem_Pw"));
				memVO.setMem_Name(rs.getString("mem_Name"));
				memVO.setMem_Gender(rs.getString("mem_Gender"));
				memVO.setMem_Bir(rs.getDate("mem_Bir"));
				memVO.setMem_Mail(rs.getString("mem_Mail"));
				memVO.setMem_Phone(rs.getString("mem_Phone"));
				memVO.setMem_Receiver(rs.getString("mem_Receiver"));
				memVO.setMem_Repno(rs.getString("mem_Repno"));
				memVO.setMem_Recounty(rs.getString("mem_Recounty"));
				memVO.setMem_Retown(rs.getString("mem_Retown"));
				memVO.setMem_Readdr(rs.getString("mem_Readdr"));
				memVO.setMem_Cardnum(rs.getString("mem_Cardnum"));
				memVO.setMem_Carddue(rs.getString("mem_Carddue"));
				memVO.setMem_Bonus(rs.getInt("mem_Bonus"));
				memVO.setMem_Credate(rs.getDate("mem_Credate"));
				memVO.setMem_Status(rs.getString("mem_Status"));
				memVO.setMem_Photo(rs.getBytes("mem_photo"));				
												
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			memVO = null;
						
		}	
		return memVO;
	}

//	@Override
//	public MemberVO compareMemId(String mem_Id) {
//		// TODO Auto-generated method stub
//		
//		Connection con = null;
//		PreparedStatement pstmt =null;
//		MemberVO memVO = null;
//		
//		try {
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("連線成功");
//			pstmt = con.prepareStatement(COMPARE_ID);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				memVO = new MemberVO();
//				memVO.setMem_Id(rs.getString("mem_Id"));
//			}
//						
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//			
//		return memVO;
//	}
	
	
}
