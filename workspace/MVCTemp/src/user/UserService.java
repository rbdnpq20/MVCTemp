package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {
	
	String url = "jdbc:mysql://localhost:3306/home_work?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<User> select() {
		List<User> list = new ArrayList();
		String sql = "select * from user";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int seq = rs.getInt("seq");	
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String hp = rs.getString("hp");
				Date regdate = rs.getDate("regdate");
				String flag = rs.getString("flag");
				
			User us = new User();
			us.setSeq(seq);
			us.setId(id);
			us.setName(name);
			us.setEmail(email);
			us.setHp(hp);
			us.setRegdate(regdate);
			us.setFlag(flag);
			list.add(us);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public User detail(String name) {
		User us = new User();
		String sql = "select * from user where name=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int seq = rs.getInt("seq");	
				String id = rs.getString("id");
				name = rs.getString("name");
				String email = rs.getString("email");
				String hp = rs.getString("hp");
				Date regdate = rs.getDate("regdate");
				String flag = rs.getString("flag");
				
			us.setSeq(seq);
			us.setId(id);
			us.setName(name);
			us.setEmail(email);
			us.setHp(hp);
			us.setRegdate(regdate);
			us.setFlag(flag);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}

}
