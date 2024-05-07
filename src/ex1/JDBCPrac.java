package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCPrac {

	public static void main(String[] args) {
		
		String dbDriver = "org.postgresql.Driver";
		
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
//		String sql = "INSERT INTO notice (title, writer_id, content, files) VALUES (?,?,?,?);";
//		String sql = "UPDATE notice SET title=?, content=?, files=? WHERE id=?";
		String sql = "DELETE FROM notice WHERE id=?";
		
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//			Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
//			ResultSet rs = st.executeQuery(sql);
			 
			int id = 1;
			String title = "제목";
			String writer_id = "hukabo";
			String content = "블라블라";
			String files = "";
			
//			st.setString(1, title);
//			st.setString(2, content);
//			st.setString(3, files);
//			st.setInt(4, id);
			
			st.setInt(1, id);
			
			int result = st.executeUpdate();
			
			System.out.println(result);
			
//			rs.close();
			st.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
