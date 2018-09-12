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

public class MemberDAO implements MemberDAO_interface{
	
	public static final String DRIVER ="oracle.jdbc.driver.OracleDriver";
	public static final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER="CHIAPAO";
	public static final String PASSWORD="CHIAPAO";
	public static final String INSERT_STMT=
			"INSERT INTO MEMBER(MEM_NO , MEM_ID , MEM_PW , MEM_NAME , MEM_GENDER , MEM_BIR , MEM_MAIL , MEM_PHONE , MEM_RECEIVER , MEM_REPNO, MEM_RECOUNTY , MEM_RETOWN ,MEM_READDR , MEM_CARDNUM , MEM_CARDDUE , MEM_BONUS,MEM_PHOTO) " + 
			"VALUES('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_STMT=
			"UPDATE MEMBER SET MEM_PW=? , MEM_NAME=? , MEM_GENDER=? , MEM_BIR = to_date(?,'yyyy-mm-dd') , MEM_MAIL=? , MEM_PHONE=? , MEM_RECEIVER=? , MEM_REPNO=?, MEM_RECOUNTY =?, MEM_RETOWN=? ,MEM_READDR=? , MEM_CARDNUM=? , MEM_CARDDUE=? , MEM_BONUS=? , MEM_PHOTO=? WHERE MEM_ID =?";
	public static final String CHANGESTATUS_STMT=
			"UPDATE MEMBER SET MEM_STATUS=? WHERE MEM_ID = ?";
	public static final String GETALL=
			"SELECT * FROM MEMBER";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			pstmt.setDate(5, java.sql.Date.valueOf(memVO.getMem_Bir()));
			pstmt.setString(6, memVO.getMem_Mail());
			pstmt.setString(7, memVO.getMem_Phone());
			pstmt.setString(8, memVO.getMem_Receiver());
			pstmt.setString(9, memVO.getMem_Repno());
			pstmt.setString(10, memVO.getMem_Recounty());
			pstmt.setString(11, memVO.getMem_Retown());
			pstmt.setString(12, memVO.getMem_Readdr());
			pstmt.setString(13, memVO.getMem_Cardnum());
			pstmt.setString(14, memVO.getMem_Carddue());
			pstmt.setInt(15, memVO.getMem_Bonus());
			byte[] pic = getPictureByte("img/test1.jpeg");
			pstmt.setBytes(16,pic);
			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 "+rowCount+" 會員資料");
		} catch (SQLException | IOException e) {
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
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			System.out.println("連線成功");
			pstmt.setString(1, memVO.getMem_Pw());
			pstmt.setString(2, memVO.getMem_Name());
			pstmt.setString(3, memVO.getMem_Gender());
			pstmt.setString(4, memVO.getMem_Bir());
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
			byte[] pic = getPictureByte("img/test2.jpg");
			pstmt.setBytes(15, pic);
			pstmt.setString(16, memVO.getMem_Id());
			int rowCount = pstmt.executeUpdate();
			System.out.println("修改 "+rowCount+" 筆資料");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	public List<MemberVO> getall() {
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
	
	
}
