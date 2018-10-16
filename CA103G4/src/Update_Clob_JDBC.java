import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Update_Clob_JDBC {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA103";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	private static final String UPDATE_POST_CONT = "update POST set POST_CONT = ? where POST_NO = ?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public void updatePic(String post_no,Connection con,String filenametxt) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(UPDATE_POST_CONT);
			System.out.println("post_no"+post_no);
			Reader reader = getLongStringStream(filenametxt);

			System.out.println("OK");
			pstmt.setCharacterStream(1, reader);
			pstmt.setString(2, post_no);

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException | IOException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		
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
	
	public void execute() {
		File path = new File("C:/CA103G4_img/post/post_clob");
		File[] list = path.listFiles();
		System.out.println("list.size()"+list.length);
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(File file : list) {

			try {

				String filename = file.getName().substring(0, 6);
				System.out.println("filename"+filename);
				
				if(filename==null) {
					continue;
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String importdate_string  = dateFormat.format(new Date());
				System.out.println("importdate_string"+importdate_string);
				String post_no= importdate_string+"-"+filename;
				String filenametxt= "C:/CA103G4_img/post/post_clob/"+filename+".txt";
				updatePic(post_no,con,filenametxt);
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 使用資料流
		public static Reader getLongStringStream(String path) throws IOException {
			return new FileReader(path);

		}
	
	public static void main(String[] args) {
		Update_Clob_JDBC obj = new Update_Clob_JDBC();
		obj.execute();
	}
}

