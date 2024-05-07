package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Notice;

public class NoticeService {
	
	private final String dbDriver = "org.postgresql.Driver";
	private final String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
	private final String dbUser = "hukabo";
	private final String dbPassword = "hukabo";
	
	public List<Notice> getList(int offset) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM notice ORDER BY regdate DESC LIMIT 10 OFFSET ?";
		List<Notice> list = new ArrayList<>();
		
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, offset);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			String content = rs.getString("content");
			Date regDate = rs.getDate("regDate");
			int hit = rs.getInt("hit");
			
			Notice notice = new Notice(id, title, writerId, regDate, content, hit);
			
			list.add(notice);
		}
		
		return list;
	}
	
	public String insertNotice(Notice notice) throws SQLException {
		
		String sql = "INSERT INTO notice(id, title, writer_id, content, regDate, hit) "
				+ "VALUES(?,?,?,?,?,?)";
		
		Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, notice.getId());
		st.setString(2, notice.getTitle());
		st.setString(3, notice.getWriterId());
		st.setString(4, notice.getContent());
		st.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
		st.setInt(6, notice.getHit());
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result > 0 ? "등록되었습니다." : "등록에 실패하였습니다.";
	}
	
	public String updateNotice(Notice notice) throws SQLException {
		
		String sql = "UPDATE notice SET title=?, content=?, regDate=?";
		
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result > 0 ? "수정되었습니다." : "수정에 실패하였습니다.";
	}
	
	public String deleteNotice(int id) throws SQLException {
		
		String sql = "DELETE FROM notice WHERE id=?";
		
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		
		return result > 0 ? "삭제되었습니다." : "삭제되지 않았습니다.";
	}
}
