package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPrac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbDriver = "org.postgresql.Driver";
		
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String sql = "SELECT * FROM notice";
		
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				String title = rs.getString("title");
				System.out.println(title);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
